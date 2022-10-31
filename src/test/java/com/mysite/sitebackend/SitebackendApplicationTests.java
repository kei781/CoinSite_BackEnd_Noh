package com.mysite.sitebackend;


import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@SpringBootTest
class SitebackendApplicationTests {

	LocalDate now = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	String formatedNow = now.format(formatter);

	@Test
	void contextLoads() {
		for(int i = 1; i<=10; i++){

		}
	}
}
