package com.example.movieservice.domain.Repository;

import com.example.movieservice.domain.Entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select m from Movie m where m.genre like %?1% order by m.releaseYear DESC")
    List<Movie> findByPreferences(String preferences);
}
