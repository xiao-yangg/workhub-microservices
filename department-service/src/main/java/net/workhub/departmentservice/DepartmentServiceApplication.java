package net.workhub.departmentservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title="Department Services REST APIs",
				description="Department Services REST APIs Documentation",
				version="v1.0",
				contact=@Contact(
						name="Bob",
						email="example@gmail.com",
						url="https://www.example.com"
				),
				license=@License(
						name="Apache 2.0",
						url="https://www.example.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description="Department-Service Doc",
				url="https://www.example.com"
		)
)

@SpringBootApplication
public class DepartmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

}
