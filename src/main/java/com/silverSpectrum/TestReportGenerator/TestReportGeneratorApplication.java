package com.silverSpectrum.TestReportGenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.silverSpectrum.TestReportGenerator")
public class TestReportGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestReportGeneratorApplication.class, args);
	}

}
