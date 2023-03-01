package com.example.movieservice.service;

import com.example.movieservice.domain.DTOs.MovieDetails;
import com.example.movieservice.domain.DTOs.MovieModel;
import com.example.movieservice.domain.Entities.Movie;
import com.example.movieservice.domain.Repository.MovieRepository;
import com.example.movieservice.service.mappers.MovieMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public List<MovieDetails> getAllMovies() throws Exception {
        log.info("START: Get All movies");
        List<Movie> moviesList = movieRepository.findAll();
        List<MovieDetails> movieDetails = Collections.emptyList();
        try {
            if (moviesList != null) {
                movieDetails = moviesList.stream()
                        .map(movieMapper::movieToMovieDetails)
                        .collect(Collectors.toList());
            }
        } catch (Exception ex) {
            log.error("An error occurred while getting all movies", ex);
            throw new Exception("Failed to get all movies. Please try again later.", ex);
        }
        log.info("END: Get All movies");
        return movieDetails;
    }

    public MovieDetails saveMovie(MovieModel movieModel) throws Exception {
        log.info("START: Save movies");
        try {
            Movie movieDb = movieMapper.MovieModelToMovieDB(movieModel);
            Movie savedMovie = movieRepository.save(movieDb);
            MovieDetails result = movieMapper.movieToMovieDetails(savedMovie);
            log.info("END: Save movies");
            return result;
        } catch (Exception ex) {
            log.error("An error occurred while saving the movie", ex);
            throw new Exception("Failed to save the movie. Please try again later.", ex);
        }
    }

    public List<MovieDetails> getMovieByPreferences(String preferences) throws Exception {
        log.info("START: Find Movies by User Preferences");
        try {
            List<MovieDetails> result = new ArrayList<>();
            if (preferences != null) {
                List<Movie> movieList = movieRepository.findByPreferences(preferences);
                if (movieList != null) {
                    result = movieMapper.movieToMovieDetails(movieList);
                } else {
                    log.error("Movie list returned by repository is null");
                    throw new Exception("Failed to find movies by user preferences. Please try again later.");
                }
            } else {
                log.error("Preferences parameter is null");
                throw new IllegalArgumentException("Preferences cannot be null");
            }
            log.info("END: Find Movies by User Preferences");
            return result;
        } catch (Exception ex) {
            log.error("An error occurred while finding movies by user preferences", ex);
            throw new Exception("Failed to find movies by user preferences. Please try again later.", ex);
        }
    }

}
