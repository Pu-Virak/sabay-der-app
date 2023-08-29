package com.dvr.sbd.sabay_der_app.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(contact = @Contact(name = "Det Virak", email = "detvirak5@gmail.com", url = "#"), description = "Version 1 of Sabay Der API", title = "Coffee Shop V1", version = "2.0", license = @License(name = "@SBD", url = "#"), termsOfService = "Terms of service"), servers = {
        @Server(description = "Local ENV", url = "http://localhost:8080"),
        @Server(description = "PROD ENV", url = "#")
}

        , security = {

                @SecurityRequirement(name = "bearerAuth")
        }

)
@SecurityScheme(name = "Sabay Der", description = "Authorization for Sabay Der Application", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
public class APIDocConfig {

}
