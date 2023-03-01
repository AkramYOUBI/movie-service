package com.example.movieservice.service;


import com.example.movieservice.domain.DTOs.MovieDetails;
import com.example.movieservice.domain.DTOs.MovieModel;
import com.example.movieservice.domain.Entities.Movie;
import com.example.movieservice.domain.Repository.MovieRepository;
import com.example.movieservice.service.mappers.MovieMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private MovieMapper movieMapper;
    @InjectMocks
    private MovieService movieService;

    /**
     * Create Movie Unit Test
     *
     * @throws Exception
     */
    @Test
    void createMovieTest() throws Exception {
        MovieDetails movieDetails = MovieDetails.builder()
                .id(1L)
                .title("The Godfather")
                .genre("Drama")
                .releaseYear(1972)
                .director("Francis Ford Coppola").build();

        MovieModel movieModel = MovieModel.builder()
                .title("The Godfather")
                .genre("Drama")
                .releaseYear(1972)
                .director("Francis Ford Coppola").build();

        Movie movie = Movie.builder()
                .id(1L)
                .title("The Godfather")
                .genre("Drama")
                .releaseYear(1972)
                .director("Francis Ford Coppola").build();

        when(movieRepository.save(any(Movie.class))).thenReturn(movie);
        when(movieMapper.MovieModelToMovieDB(movieModel)).thenReturn(movie);
        when(movieMapper.movieToMovieDetails(movie)).thenReturn(movieDetails);

        MovieDetails result = movieService.saveMovie(movieModel);

        Assertions.assertEquals(result.getId(), movie.getId());
        Assertions.assertEquals(result.getTitle(), movie.getTitle());
        Assertions.assertEquals(result.getDirector(), movie.getDirector());
    }

    /**
     * Get Movie by Preference Unit Test
     *
     * @throws Exception
     */
    @Test
    void getMovieByPreferenceTest() throws Exception {
        String preferences = "action";
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(1L, "The Godfather", "Drama", 1972, "Francis Ford Coppola"));
        movieList.add(new Movie(2L, "The Shawshank Redemption", "Drama", 1994, "Frank Darabont"));


        List<MovieDetails> expected = new ArrayList<>();
        expected.add(new MovieDetails(1L, "The Godfather", "Drama", 1972, "Francis Ford Coppola"));
        expected.add(new MovieDetails(2L, "The Shawshank Redemption", "Drama", 1994, "Frank Darabont"));

        when(movieRepository.findByPreferences(preferences)).thenReturn(movieList);
        when(movieMapper.movieToMovieDetails(movieList)).thenReturn(expected);

        List<MovieDetails> result = movieService.getMovieByPreferences(preferences);

        Assertions.assertEquals(movieList.size(), result.size());
    }


    /**
     * Get All Movies Unit Test
     *
     * @throws Exception
     */
    @Test
    void getAllMoviesTest() throws Exception {

        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(1L, "The Godfather", "Drama", 1972, "Francis Ford Coppola"));
        movieList.add(new Movie(2L, "The Shawshank Redemption", "Drama", 1994, "Frank Darabont"));

        List<MovieDetails> expected = new ArrayList<>();
        expected.add(new MovieDetails(1L, "The Godfather", "Drama", 1972, "Francis Ford Coppola"));
        expected.add(new MovieDetails(2L, "The Shawshank Redemption", "Drama", 1994, "Frank Darabont"));

        when(movieRepository.findAll()).thenReturn(movieList);
        when(movieMapper.movieToMovieDetails(movieList)).thenReturn(expected);

        List<MovieDetails> result = movieService.getAllMovies();

        Assertions.assertEquals(movieList.size(), result.size());
    }
}
