package com.divasive.movietrack.utils;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.divasive.movietrack.repos.MovieRepository;
import com.divasive.movietrack.domain.Movie;
import com.divasive.movietrack.exceptions.MovieNotFoundException;

@Configuration
@Slf4j
public class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(MovieRepository repository) {
    return args -> {
      log.info("Preloading " + repository.save(new Movie("The Imitation Game", "Drama", "2014", "PG-13")));
      log.info("Preloading " + repository.save(new Movie("A Beautiful Mind", "Drama", "2001", "PG-13")));
    };
  }
}
