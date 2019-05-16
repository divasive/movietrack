package com.divasive.movietrack.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.divasive.movietrack.repos.MovieRepository;
import com.divasive.movietrack.domain.Movie;
import com.divasive.movietrack.exceptions.MovieNotFoundException;
import com.divasive.movietrack.utils.DateUtil;

@RestController
public class MovieController {

  private final MovieRepository repository;

  MovieController(MovieRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/api/movie/list")
  List<Movie> all() {
    return repository.findAll();
  }

  @PostMapping("/api/movie")
  Movie newMovie(@RequestBody Movie newMovie) {
    return repository.save(newMovie);
  }

  @GetMapping("/api/movie/{id}")
  Movie findById(@PathVariable Long id) {

      return repository.findById(id)
    .orElseThrow(() -> new MovieNotFoundException(id));
  }

  @PutMapping("/api/movie/{id}")
  Movie replaceMovie(@RequestBody Movie newMovie, @PathVariable Long id) {

    return repository.findById(id)
      .map(movie -> {
        movie.setName(newMovie.getName());
        movie.setGenre(newMovie.getGenre());
	movie.setRelease(newMovie.getRelease());
	movie.setRating(newMovie.getRating());
        return repository.save(movie);
      })
      .orElseGet(() -> {
        newMovie.setId(id);
        return repository.save(newMovie);
      });
  }

  @DeleteMapping("/api/movie/{id}")
  void deleteMovie(@PathVariable Long id) {
    repository.deleteById(id);
  }


  @GetMapping("/api/timeOfDay")
  String getTime() {

      return "{\"time\":\"" + DateUtil.getTime() + "\"}";
  }

}
