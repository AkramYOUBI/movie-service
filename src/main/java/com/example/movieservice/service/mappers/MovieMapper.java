package com.example.movieservice.service.mappers;


import com.example.movieservice.domain.DTOs.MovieDetails;
import com.example.movieservice.domain.DTOs.MovieModel;
import com.example.movieservice.domain.Entities.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper{


    MovieDetails movieToMovieDetails(Movie movie);
    List<MovieDetails> movieToMovieDetails(List<Movie> movie);

    Movie MovieModelToMovieDB(MovieModel movieModel);
}
