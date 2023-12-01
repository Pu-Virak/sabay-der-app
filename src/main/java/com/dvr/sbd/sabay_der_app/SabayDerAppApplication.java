package com.dvr.sbd.sabay_der_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.dvr.sbd.sabay_der_app.config.FileStorageConfig;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageConfig.class
})
public class SabayDerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SabayDerAppApplication.class, args);
	}

}
