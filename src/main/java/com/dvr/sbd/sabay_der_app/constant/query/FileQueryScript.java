package com.dvr.sbd.sabay_der_app.constant.query;

public class FileQueryScript {
  public static final String registerFileUpload = """
      INSERT INTO file(file_name, file_dynamic_uri, file_type, file_size, file_location, file_data)
      values(#{f.fileName}, #{f.fileDynamicURI}, #{f.fileType}, #{f.fileSize}, '', #{f.fileData})
      """;
  public static final String retrieveFileUploadByID = """
      SELECT
            F.FILE_NAME AS "fileName"
          , F.FILE_DYNAMIC_URI AS "fileDynamicURI"
          , F.FILE_TYPE AS "fileType"
          , F.FILE_SIZE AS "fileSize"
          , F.FILE_LOCATION AS "fileLocation"
          , F.FILE_DATA AS "fileData"
      FROM FILE F
      WHERE F.ID = #{id}
      """;
  public static final String retrieveFileUploadByUniqueName = """
      SELECT
            F.FILE_NAME AS "fileName"
          , F.FILE_DYNAMIC_URI AS "fileDynamicURI"
          , F.FILE_TYPE AS "fileType"
          , F.FILE_SIZE AS "fileSize"
          , F.FILE_LOCATION AS "fileLocation"
          , F.FILE_DATA AS "fileData"
      FROM FILE F
      WHERE F.FILE_NAME = #{fileName}
      """;

}
