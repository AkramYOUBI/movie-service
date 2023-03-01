package com.example.movieservice.controller;

import com.example.movieservice.domain.DTOs.MovieDetails;
import com.example.movieservice.domain.DTOs.MovieModel;
import com.example.movieservice.domain.Entities.Movie;
import com.example.movieservice.service.MovieService;
import com.example.movieservice.web.MovieController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest {

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;


    /**
     * Create Movie Unit Test
     *
     * @throws Exception
     */
    @Test
    void saveMovie() throws Exception {

        MovieModel movieModel = MovieModel.builder()
                .title("The Godfather")
                .genre("Drama")
                .releaseYear(1972)
                .director("Francis Ford Coppola").build();
        MovieDetails movieDetails = MovieDetails.builder()
                .id(1L)
                .title("The Godfather")
                .genre("Drama")
                .releaseYear(1972)
                .director("Francis Ford Coppola").build();

        when(movieService.saveMovie(any(MovieModel.class))).thenReturn(movieDetails);
        MovieDetails controllerResponse = movieController.saveMovie(movieModel);

        //assertion
        assertEquals(controllerResponse.getId(), movieDetails.getId());
        assertEquals(controllerResponse.getTitle(), movieDetails.getTitle());

    }


    /**
     * Get Movie By Preferences Unit Test
     *
     * @throws Exception
     */
    @Test
    public void testGetMovieByPreferences() throws Exception {

        String preferences = "action";
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(1L, "The Godfather", "Drama", 1972, "Francis Ford Coppola"));
        movieList.add(new Movie(2L, "The Shawshank Redemption", "Drama", 1994, "Frank Darabont"));


        List<MovieDetails> expected = new ArrayList<>();
        expected.add(new MovieDetails(1L, "The Godfather", "Drama", 1972, "Francis Ford Coppola"));
        expected.add(new MovieDetails(2L, "The Shawshank Redemption", "Drama", 1994, "Frank Darabont"));

        when(movieService.getMovieByPreferences(preferences)).thenReturn(expected);

        List<MovieDetails> result = movieService.getMovieByPreferences(preferences);

        // assertion
        assertEquals(expected, result);
    }

    /**
     * Get All Movies Unit Test
     *
     * @throws Exception
     */
    @Test
    public void testGetAllMovies() throws Exception {
        // arrange
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(1L, "The Godfather", "Drama", 1972, "Francis Ford Coppola"));
        movieList.add(new Movie(2L, "The Shawshank Redemption", "Drama", 1994, "Frank Darabont"));

        List<MovieDetails> expected = new ArrayList<>();
        expected.add(new MovieDetails(1L, "The Godfather", "Drama", 1972, "Francis Ford Coppola"));
        expected.add(new MovieDetails(2L, "The Shawshank Redemption", "Drama", 1994, "Frank Darabont"));

        when(movieService.getAllMovies()).thenReturn(expected);

        // act
        List<MovieDetails> result = movieService.getAllMovies();

        // assertion
        assertEquals(expected, result);
    }


}
