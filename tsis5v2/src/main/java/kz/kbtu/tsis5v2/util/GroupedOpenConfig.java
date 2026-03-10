package kz.kbtu.tsis5v2.util;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GroupedOpenConfig {

    @Bean
    public GroupedOpenApi teachersApi() {
        return GroupedOpenApi.builder()
                .group("Teachers")
                .pathsToMatch("/api/v1/teachers/**")
                .build();
    }

    @Bean
    public GroupedOpenApi subjectsApi() {
        return GroupedOpenApi.builder()
                .group("Subjects")
                .pathsToMatch("/api/v1/subjects/**")
                .build();
    }

    @Bean
    public GroupedOpenApi studentsApi() {
        return GroupedOpenApi.builder()
                .group("Students")
                .pathsToMatch("/api/v1/students/**")
                .build();
    }

    @Bean
    public GroupedOpenApi enrollmentsApi() {
        return GroupedOpenApi.builder()
                .group("Enrollments")
                .pathsToMatch("/api/v1/enrollments/**")
                .build();
    }

}
