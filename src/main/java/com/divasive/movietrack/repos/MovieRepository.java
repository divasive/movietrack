package com.divasive.movietrack.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.divasive.movietrack.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
