package com.divasive.movietrack.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Movie {

    private @Id @GeneratedValue Long id;
    private String name;
    private String genre;
    private String release;
    private String rating;

    public Movie() {}

    public Movie(String name, String genre, String release, String rating) {
	this.name = name;
	this.genre = genre;
	this.release = release;
	this.rating = rating;
    }
}
