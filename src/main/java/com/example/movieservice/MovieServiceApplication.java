package com.example.movieservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Test program implements a SpringBoot application that
 * manage a movie.
 *
 * @author  YOUBI Akram
 * @since   2023-03
 */
@SpringBootApplication
@EnableSwagger2
public class MovieServiceApplication {

    /**
     * The main SpringBoot application
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(MovieServiceApplication.class, args);
    }

}
