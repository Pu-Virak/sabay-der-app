package com.dvr.sbd.sabay_der_app.constant.query;

public class PlaceQueryScript {
    public static final String retrievePlaceInfoByID = """
                SELECT
                        id 					AS 		"id"
                    , name_en 				AS 		"nameEn"
                    , name_kh 				AS 		"nameKh"
                    , slide_show 			AS 		"slideShow"
                    , desc_en 				AS 		"descEn"
                    , desc_kh 				AS 		"descKh"
                    , map_url 				AS 		"mapURL"
                    , created_by 			AS 		"createdBy"
                    , updated_by 			AS 		"updatedBy"
                    , created_date_time 	AS 		"createdDateTime"
                    , updated_date_time 	AS 		"updatedDateTime"
                    , pro_id 				AS 		"provinceID"
                FROM place
                WHERE id = #{id}
            """;
}
