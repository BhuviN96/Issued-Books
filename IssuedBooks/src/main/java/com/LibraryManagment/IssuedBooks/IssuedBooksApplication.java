package com.LibraryManagment.IssuedBooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class IssuedBooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssuedBooksApplication.class, args);
	}

}
