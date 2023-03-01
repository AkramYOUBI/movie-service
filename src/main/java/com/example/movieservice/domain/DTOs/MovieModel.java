package com.example.movieservice.domain.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieModel {

    private String title;
    private String genre;
    private int releaseYear;
    private String director;
}
