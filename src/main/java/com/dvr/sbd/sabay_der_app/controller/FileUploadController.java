package com.dvr.sbd.sabay_der_app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dvr.sbd.sabay_der_app.exception.SBDBadRequestException;
import com.dvr.sbd.sabay_der_app.exception.SBDBaseException;
import com.dvr.sbd.sabay_der_app.exception.SBDNotFoundException;
import com.dvr.sbd.sabay_der_app.model.res.FileUploadRes;
import com.dvr.sbd.sabay_der_app.service.impl.FileUploadService;
import com.dvr.sbd.sabay_der_app.utils.com.FileStorageUtil;
import com.dvr.sbd.sabay_der_app.utils.res.BaseRes;
import com.dvr.sbd.sabay_der_app.utils.res.BaseStatusRes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/file")
@Slf4j
@AllArgsConstructor
public class FileUploadController {

    private final FileUploadService fileStorageService;

    @Operation(operationId = "single-file", summary = "uploadFile", description = "Upload file to server")
    @PostMapping(value = "/single-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws SBDBaseException {
        try {
            FileUploadRes fRes = this.upload(file);
            LinkedHashMap<String, Object> payload = new LinkedHashMap<>();
            payload.put("fileURI", fRes.getFileDynamicURI());
            payload.put("fileSize", fRes.getFileSize());
            payload.put("fileType", fRes.getFileType());

            BaseRes<LinkedHashMap<String, Object>> resData = new BaseRes<>();
            resData.setStatus(
                    new BaseStatusRes<>("Upload file to server successfully.", HttpStatus.CREATED.value(), true));
            resData.setPayload(payload);
            return new ResponseEntity<BaseRes<LinkedHashMap<String, Object>>>(resData, HttpStatus.CREATED);
        } catch (Exception ex) {
            log.error("Upload file to server error with unknow error", ex);
            throw new SBDBadRequestException("Upload file to server failed.");
        }
    }

    @Operation(operationId = "multiple-files", summary = "uploadMultipleFiles", description = "Upload multiple files", parameters = {

    })
    @PostMapping(value = "/multiple-files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) throws SBDBaseException {
        try {
            List<FileUploadRes> fUploadListing = Arrays.stream(files)
                    .map(this::upload)
                    .collect(Collectors.toList());
            List<LinkedHashMap<String, Object>> payload = new ArrayList<>();
            for (FileUploadRes fRes : fUploadListing) {
                LinkedHashMap<String, Object> data = new LinkedHashMap<>();
                data.put("fileURI", fRes.getFileDynamicURI());
                data.put("fileSize", fRes.getFileSize());
                data.put("fileType", fRes.getFileType());
                payload.add(data);
            }
            BaseRes<List<LinkedHashMap<String, Object>>> resData = new BaseRes<>();
            resData.setStatus(
                    new BaseStatusRes<>("Upload files to server successfully.", HttpStatus.CREATED.value(), true));
            resData.setPayload(payload);
            return new ResponseEntity<BaseRes<List<LinkedHashMap<String, Object>>>>(resData, HttpStatus.CREATED);
        } catch (Exception ex) {
            log.error("Upload file to server error with unknow error", ex);
            throw new SBDBadRequestException("Upload file to server failed.");
        }
    }

    private FileUploadRes upload(MultipartFile file) {
        FileUploadRes fRes = new FileUploadRes();
        try {
            fRes = fileStorageService.uploadFileToServer(file);
        } catch (Exception ex) {
            log.error(">>> Cannot upload file to server due to: ", ex);
        }
        return fRes;
    }

    // @GetMapping("/{fileName:.+}")
    // public ResponseEntity<Resource> downloadFile(@PathVariable String fileName,
    // HttpServletRequest request)
    // throws SBDBaseException {
    // // Load file as Resource
    // Resource resource = null;
    // resource = fileStorageService.loadFileAsResource(fileName);

    // // Try to determine file's content type
    // String contentType = null;
    // try {
    // contentType =
    // request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    // } catch (IOException ex) {
    // log.error("--->>> Could not determine file type due to:", ex);
    // }

    // // Fallback to the default content type if type could not be determined
    // if (contentType == null) {
    // contentType = "application/octet-stream";
    // }

    // return ResponseEntity.ok()
    // .contentType(MediaType.parseMediaType(contentType))
    // .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
    // resource.getFilename() + "\"")
    // .body(resource);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFData(long id) throws SBDBaseException {
        byte[] dtOut = null;
        FileUploadRes fRes = new FileUploadRes();
        try {
            fRes = fileStorageService.retrieveFileFromServerByID(id);
            if (fRes != null) {
                dtOut = fRes.getFileData();
            } else {
                throw new SBDNotFoundException("File was not found.");
            }
        } catch (SBDNotFoundException nx) {
            throw new SBDNotFoundException(nx.getMessage());
        } catch (SBDBaseException sdx) {
            log.error("Retrieve file from server error with unknow error", sdx);
            throw new SBDBadRequestException("Retrieve file from server error.");
        }
        dtOut = FileStorageUtil.decompressFile(dtOut);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(fRes.getFileType())).body(
                dtOut);
    }

    @GetMapping
    public ResponseEntity<?> getFData(@RequestParam(required = true, name = "fileName") String fName)
            throws SBDBaseException {
        byte[] dtOut = null;
        FileUploadRes fRes = new FileUploadRes();
        try {
            fRes = fileStorageService.retrieveFileFromServerByUniqueName(fName);
            if (fRes != null) {
                dtOut = fRes.getFileData();
            } else {
                throw new SBDNotFoundException("File was not found.");
            }
        } catch (SBDNotFoundException nx) {
            throw new SBDNotFoundException(nx.getMessage());
        } catch (SBDBaseException sdx) {
            log.error("Retrieve file from server error with unknow error", sdx);
            throw new SBDBadRequestException("Retrieve file from server error.");
        }
        dtOut = FileStorageUtil.decompressFile(dtOut);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(fRes.getFileType())).body(
                dtOut);
    }

}
