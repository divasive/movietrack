package com.divasive.movietrack;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;


import com.divasive.movietrack.domain.Movie;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MovieTrackRestTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${test.result.find.by.id}")
    private String findByIdResult;

    @Value("${test.result.put}")
    private String putResult;

    
    @Test
    public void findByIdTest() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/movie/1",
						  String.class)).isEqualTo(findByIdResult);

    }

    
    @Test
    public void createAndDeleteMovie() throws Exception {

	//create
	HttpEntity<Movie> createRequest = new HttpEntity<Movie>(new Movie("Damage","drama","1992","R"));
	Movie movie = restTemplate.postForObject("http://localhost:" + port + "/api/movie", createRequest, Movie.class);
	assertThat(movie != null);
	assertThat(movie.getName()).isEqualTo("Damage");

	//delete
	RequestEntity deleteRequest = RequestEntity
	    .delete(new URI("http://localhost:" + port + "/api/movie/" + movie.getId()))
	    .build();
	assertThat(restTemplate.exchange(deleteRequest, String.class).getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    
    @Test
    public void putMovie() throws Exception {

	//put
	HttpEntity<Movie> putEntity = new HttpEntity<Movie>(new Movie("The Imitation Game", "Drama", "2014", "R"));
	assertThat(restTemplate.exchange("http://localhost:" + port + "/api/movie/1",
					 HttpMethod.PUT, putEntity, String.class).getStatusCode()).isEqualTo(HttpStatus.OK);
    

	assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/movie/1",
						  String.class)).isEqualTo(putResult);
	    
    }

    
}
