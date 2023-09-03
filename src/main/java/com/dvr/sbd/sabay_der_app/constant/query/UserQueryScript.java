package com.dvr.sbd.sabay_der_app.constant.query;

public class UserQueryScript {
  public static final String retrieveListUserInfo = """
          SELECT
                                    id					AS 		"id"
                                  , username				AS 		"username"
                                  , "password"			AS 		"password"
                                  , gender				AS 		"gender"
                                  , date_of_birth			AS 		"dateOfBirth"
                                  , profile				AS 		"profile"
                                  , email					AS 		"email"
                                  , address				AS 		"address"
                                  , status				AS 		"status"
                                  , created_date_time		AS 		"createdDateTime"
                                  , updated_date_time		AS 		"updatedDateTime"
          FROM "user"
          ORDER BY ID ASC
          LIMIT #{size}
          OFFSET (#{page} - 1) * #{size}
      """;
  public static final String retrieveUserInfoByID = """
          SELECT
                                    id					AS 		"id"
                                  , username				AS 		"username"
                                  , "password"			AS 		"password"
                                  , gender				AS 		"gender"
                                  , date_of_birth			AS 		"dateOfBirth"
                                  , profile				AS 		"profile"
                                  , email					AS 		"email"
                                  , address				AS 		"address"
                                  , status				AS 		"status"
                                  , created_date_time		AS 		"createdDateTime"
                                  , updated_date_time		AS 		"updatedDateTime"
          FROM "user"
          WHERE id = #{id}
      """;
  public static final String retrieveUserInfoByIDForUpdate = """
          SELECT
                                    id					AS 		"id"
                                  , username				AS 		"username"
                                  , "password"			AS 		"password"
                                  , gender				AS 		"gender"
                                  , date_of_birth			AS 		"dateOfBirth"
                                  , profile				AS 		"profile"
                                  , email					AS 		"email"
                                  , address				AS 		"address"
                                  , status				AS 		"status"
                                  , created_date_time		AS 		"createdDateTime"
                                  , updated_date_time		AS 		"updatedDateTime"
          FROM "user"
          WHERE id = #{id}
          FOR UPDATE
      """;
  public static final String retrieveUserInfoByUsername = """
          SELECT
                                    id					AS 		"id"
                                  , username				AS 		"username"
                                  , "password"			AS 		"password"
                                  , gender				AS 		"gender"
                                  , date_of_birth			AS 		"dateOfBirth"
                                  , profile				AS 		"profile"
                                  , email					AS 		"email"
                                  , address				AS 		"address"
                                  , status				AS 		"status"
                                  , created_date_time		AS 		"createdDateTime"
                                  , updated_date_time		AS 		"updatedDateTime"
          FROM "user"
          WHERE username = #{username}
      """;
  public static final String registerUserInfo = """
      INSERT INTO "user"(username, PASSWORD, GENDER, DATE_OF_BIRTH, PROFILE, EMAIL, ADDRESS, STATUS, CREATED_DATE_TIME, UPDATED_DATE_TIME)
      VALUES(#{userReq.username}, #{userReq.password}, #{userReq.gender}, #{userReq.dateOfBirth}, #{userReq.profile}, #{userReq.email}, #{userReq.address}, #{userReq.status}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
      """;
  public static final String updateUserInfoByID = """
      UPDATE
        "user"
      SET
          username = COALESCE(#{userReq.username}, username)
        , PASSWORD = COALESCE(#{userReq.password}, PASSWORD)
        , GENDER = COALESCE(#{userReq.gender}, GENDER)
        , DATE_OF_BIRTH = COALESCE(#{userReq.dateOfBirth}, DATE_OF_BIRTH)
        , PROFILE = COALESCE(#{userReq.profile}, PROFILE)
        , EMAIL = COALESCE(#{userReq.email}, EMAIL)
        , ADDRESS = COALESCE(#{userReq.address}, ADDRESS)
        , STATUS = COALESCE(#{userReq.status}, STATUS)
        , CREATED_DATE_TIME = COALESCE(#{userReq.createdDateTime}, CREATED_DATE_TIME)
        , UPDATED_DATE_TIME = CURRENT_TIMESTAMP
      WHERE ID = #{id}
      """;
  public static final String deleteUserInfoByID = """
      DELETE FROM "user" WHERE ID = #{id}
      """;
}
