package com.dvr.sbd.sabay_der_app.utils.com;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import lombok.extern.slf4j.Slf4j;

/**
 * -- File Storage Util --
 * Init date: 2023-11-30
 * 
 * @author DET VIRAK
 */
@Slf4j
public class FileStorageUtil {
    /**
     * -- Compress File Upload --
     * 
     * @param ipData
     * @return
     */
    public static byte[] compressFile(byte[] ipData) {
        // Init data to deflater class
        Deflater df = new Deflater();
        df.setLevel(Deflater.DEFAULT_COMPRESSION);
        df.setInput(ipData);
        df.finish();

        ByteArrayOutputStream bo = new ByteArrayOutputStream(ipData.length);
        byte[] tmp = new byte[4 * 1024];
        while (!df.finished()) {
            int size = df.deflate(tmp);
            bo.write(tmp, 0, size);
        }
        try {
            bo.close();
        } catch (Exception ex) {
            log.error(">>> Cannot close ByteArrayOutputStream(): ", ex);
        }
        return bo.toByteArray();
    }

    /**
     * -- Decompress File Upload --
     * 
     * @param ipData
     * @return
     */
    public static byte[] decompressFile(byte[] ipData) {
        Inflater inf = new Inflater();
        inf.setInput(ipData);

        ByteArrayOutputStream bo = new ByteArrayOutputStream(ipData.length);
        byte[] tmp = new byte[4 * 1024];
        try {
            while (!inf.finished()) {
                int count = inf.inflate(tmp);
                bo.write(tmp, 0, count);
            }
            bo.close();
        } catch (Exception ex) {
            log.error(">>> Cannot close ByteArrayOutputStream(): ", ex);
        }
        return bo.toByteArray();
    }
}
