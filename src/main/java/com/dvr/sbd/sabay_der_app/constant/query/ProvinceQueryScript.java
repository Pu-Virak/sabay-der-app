package com.dvr.sbd.sabay_der_app.constant.query;

public class ProvinceQueryScript {

  public static final String retrieveListProvinceInfo = """
      SELECT
          ID 					          AS 		"provinceID"
        , NAMEEN 				        AS 		"nameEn"
        , NAMEKH 				        AS 		"nameKh"
        , PROFILE 				      AS 		"profile"
        , CREATED_BY 			      AS 		"createdBy"
        , UPDATED_BY 			      AS 		"updatedBy"
        , CREATED_DATE_TIME	    AS 		"createdDateTime"
        , UPDATED_DATE_TIME     AS 		"updatedDateTime"
      FROM PROVINCE
      ORDER BY ID ASC
      LIMIT #{size}
      OFFSET (#{page} - 1) * #{size}

      """;

  public static final String retrieveProvinceInfoByID = """
      SELECT
          ID 					          AS 		"provinceID"
        , NAMEEN 				        AS 		"nameEn"
        , NAMEKH 				        AS 		"nameKh"
        , PROFILE 				      AS 		"profile"
        , CREATED_BY 			      AS 		"createdBy"
        , UPDATED_BY 			      AS 		"updatedBy"
        , CREATED_DATE_TIME	    AS 		"createdDateTime"
        , UPDATED_DATE_TIME     AS 		"updatedDateTime"
      FROM PROVINCE
      WHERE id = #{id}
      """;

  public static final String retrieveProvinceInfoByUniqueNameEn = """
      SELECT
          ID 					          AS 		"provinceID"
        , NAMEEN 				        AS 		"nameEn"
        , NAMEKH 				        AS 		"nameKh"
        , PROFILE 				      AS 		"profile"
        , CREATED_BY 			      AS 		"createdBy"
        , UPDATED_BY 			      AS 		"updatedBy"
        , CREATED_DATE_TIME	    AS 		"createdDateTime"
        , UPDATED_DATE_TIME     AS 		"updatedDateTime"
      FROM PROVINCE
      WHERE NAMEEN = #{nameEn}
      """;

  public static final String retrieveProvinceInfoByUniqueNameKh = """
      SELECT
          ID 					          AS 		"provinceID"
        , NAMEEN 				        AS 		"nameEn"
        , NAMEKH 				        AS 		"nameKh"
        , PROFILE 				      AS 		"profile"
        , CREATED_BY 			      AS 		"createdBy"
        , UPDATED_BY 			      AS 		"updatedBy"
        , CREATED_DATE_TIME	    AS 		"createdDateTime"
        , UPDATED_DATE_TIME     AS 		"updatedDateTime"
      FROM PROVINCE
      WHERE NAMEKH = #{nameKh}
      """;

  public static final String retrieveProvinceInfoByIDForUpdate = """
      SELECT
          ID 					          AS 		"provinceID"
        , NAMEEN 				        AS 		"nameEn"
        , NAMEKH 				        AS 		"nameKh"
        , PROFILE 				      AS 		"profile"
        , CREATED_BY 			      AS 		"createdBy"
        , UPDATED_BY 			      AS 		"updatedBy"
        , CREATED_DATE_TIME	    AS 		"createdDateTime"
        , UPDATED_DATE_TIME     AS 		"updatedDateTime"
      FROM PROVINCE
      WHERE id = #{id}
      FOR UPDATE
      """;

  public static final String updateProvinceInfoByID = """
      UPDATE
        PROVINCE
      SET
          NAMEEN = COALESCE(#{provinceReq.nameEn}, NAMEEN)
        , NAMEKH = COALESCE(#{provinceReq.nameKh}, NAMEKH)
        , PROFILE = COALESCE(#{provinceReq.profile}, PROFILE)
        , CREATED_BY = COALESCE(#{provinceReq.createdBy}, CREATED_BY)
        , UPDATED_BY = COALESCE(#{provinceReq.updatedBy}, UPDATED_BY)
        , CREATED_DATE_TIME = COALESCE(#{provinceReq.createdDateTime}, CREATED_DATE_TIME)
        , UPDATED_DATE_TIME = CURRENT_TIMESTAMP
      WHERE ID = #{id}
      """;

  public static final String registerProvinceInfo = """
      INSERT INTO PROVINCE(NAMEEN , NAMEKH , CREATED_BY , UPDATED_BY , CREATED_DATE_TIME , UPDATED_DATE_TIME )
      VALUES(#{provinceReq.nameEn}, #{provinceReq.nameKh}, #{provinceReq.createdBy}, #{provinceReq.updatedBy}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
      """;

}
