package com.dvr.sbd.sabay_der_app.constant.query;

public class UserQueryScript {
    public static final String retrieveUserInfoByID = """
                SELECT
                                          id					AS 		"id"
                                        , user_id				AS 		"userID"
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
}
