package com.myhomebe;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class MyHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyHomeApplication.class, args);
	}
}
	//
	// @Bean
  // ApplicationRunner init(ProjectService projectService) {
  //   return args -> {
  //     Stream.of("Project 1", "Project 2", "Project 3").forEach(name -> {
  //       Project project = new Project();
  //       project.setName(name);
  //       projectService.saveProject(project);
  //     });
  //   };
  // }
//
// }
