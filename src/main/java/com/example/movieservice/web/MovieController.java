package com.example.movieservice.web;

import com.example.movieservice.domain.DTOs.MovieDetails;
import com.example.movieservice.domain.DTOs.MovieModel;
import com.example.movieservice.domain.Entities.Movie;
import com.example.movieservice.service.MovieService;
import com.example.movieservice.service.mappers.MovieMapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Rest Controller for the Movie
 */

@RestController
@RequestMapping("api")
@Validated
public class MovieController {

    /**
     * Movie Service
     */
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Get all movies
     * @return List of MovieDetails
     */
    @GetMapping("/movies")
    @ResponseStatus(value = HttpStatus.OK)
    public List<MovieDetails> getAllMovies() throws Exception {
        return movieService.getAllMovies();
    }

    /**
     * Movie creation API
     * @param movieModel
     * @return the saved movie
     */
    @PostMapping("/movies/save")
    @ResponseStatus(value = HttpStatus.CREATED)
    public MovieDetails saveMovie(@RequestBody MovieModel movieModel) throws Exception {
        return movieService.saveMovie(movieModel);
    }

    /**
     * Get all movies by user preferences
     * @param preferences
     * @return List of movies
     */
    @GetMapping("/recommendations")
    @ResponseStatus(value = HttpStatus.OK)
    public List<MovieDetails> getMovieByPreferences(@RequestParam String preferences) throws Exception {
        return movieService.getMovieByPreferences(preferences);
    }
}
