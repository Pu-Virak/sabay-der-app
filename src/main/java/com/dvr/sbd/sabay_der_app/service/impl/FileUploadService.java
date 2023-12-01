package com.dvr.sbd.sabay_der_app.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dvr.sbd.sabay_der_app.config.FileStorageConfig;
import com.dvr.sbd.sabay_der_app.exception.FileStorageException;
import com.dvr.sbd.sabay_der_app.exception.SBDBaseException;
import com.dvr.sbd.sabay_der_app.exception.SBDNotFoundException;
import com.dvr.sbd.sabay_der_app.model.res.FileUploadRes;
import com.dvr.sbd.sabay_der_app.repo.FileUploadRepo;
import com.dvr.sbd.sabay_der_app.utils.com.FileStorageUtil;

@Service
public class FileUploadService {

    private final Path fileStorageLocation;
    @Autowired
    private FileUploadRepo fRepo;

    @Autowired
    public FileUploadService(FileStorageConfig fConfig) {
        this.fileStorageLocation = Paths.get(fConfig.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
                    ex);
        }
    }

    /**
     * -- Upload File to Server --
     * 
     * @param file
     * @return
     */
    public FileUploadRes uploadFileToServer(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            String newFileName = UUID.randomUUID().toString().concat("___").concat(fileName);
            String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/v1/file")
                    .queryParam("fileName", URLEncoder.encode(newFileName, "UTF-8"))
                    .toUriString();
            // Copy file to the target location
            Path targetLocation = this.fileStorageLocation.resolve(newFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            // Init file upload data
            FileUploadRes fRes = new FileUploadRes();
            fRes.setFileName(newFileName);// file.getOriginalFilename()
            fRes.setFileDynamicURI(fileUrl);
            fRes.setFileSize(file.getSize());
            fRes.setFileType(file.getContentType());
            fRes.setFileData(FileStorageUtil.compressFile(file.getBytes()));
            boolean res = false;
            if (fRes != null) {
                res = fRepo.registerFileUpload(fRes);
            }
            return res ? fRes : null;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    /**
     * -- Download File from Server --
     * 
     * @param fileName
     * @return
     */
    public Resource downloadFileFromServer(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new SBDNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new SBDNotFoundException("File not found " + fileName, ex);
        }
    }

    /**
     * -- Retrieve File from Server --
     * 
     * @param ipFPath
     * @return
     * @throws SBDBaseException
     */
    public FileUploadRes retrieveFileFromServerByID(long ipData) throws SBDBaseException {
        try {
            FileUploadRes fRes = new FileUploadRes();
            fRes = fRepo.retrieveFileUploadByID(ipData);
            return fRes != null ? fRes : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * -- Retrieve File from Server --
     * 
     * @param ipData
     * @return
     * @throws SBDBaseException
     */
    public FileUploadRes retrieveFileFromServerByUniqueName(String ipData) throws SBDBaseException {
        try {
            FileUploadRes fRes = new FileUploadRes();
            fRes = fRepo.retrieveFileUploadByUniqueName(ipData);
            return fRes != null ? fRes : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
