package com.mainclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


@SpringBootApplication
@EnableConfigServer
public class CopyFilesConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(CopyFilesConfigApplication.class, args);
	}

}
