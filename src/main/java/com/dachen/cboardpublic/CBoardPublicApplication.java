package com.dachen.cboardpublic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.dachen")
public class CBoardPublicApplication {
	public static void main(String[] args) {
		SpringApplication.run(CBoardPublicApplication.class, args);
	}
}
