package com.snip.urlshortner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class UrlshortnerApplication {

	public static void main(String[] args) {

		SpringApplication.run(UrlshortnerApplication.class, args);
	}

}
