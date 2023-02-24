package com.artist.vote.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.artist.vote.demo.bean.ArtistBean;
import com.artist.vote.demo.bean.VoteResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArtistVoteControllerRestTemplTest {

	private static final String url = "http://localhost:8080/artists";
	private static final String ARTIST_LABLE = "van_halen";

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void getAllArtistTest() {
		System.out.println(this.restTemplate.getForObject(url, String.class));
		assertThat(this.restTemplate.getForObject(url, String.class)).contains(ARTIST_LABLE);
	}

	@Test
	public void getArtistTest() {
		ArtistBean artistResponse = this.restTemplate.getForObject(url + "/" + ARTIST_LABLE, ArtistBean.class);
		assertEquals(ARTIST_LABLE, artistResponse.getArtistLabel());
	}

	@Test
	public void pollVoteTest() {
		ArtistBean artistResponse = new ArtistBean();
		artistResponse.setArtistLabel(ARTIST_LABLE);
		ResponseEntity<VoteResponse> response = this.restTemplate.postForEntity(url + "/vote", artistResponse,
				VoteResponse.class);
		VoteResponse voteResponse = response.getBody();
		assertEquals(ARTIST_LABLE, voteResponse.getArtistName());
	}

	@Test
	public void getArtistFailureTest() {
		ArtistBean artistResponse = this.restTemplate.getForObject(url + "/Van", ArtistBean.class);
		System.out.println("Test--" + artistResponse.getArtistLabel());
		assertNull(artistResponse.getArtistLabel());
	}

	@Test
	public void pollVoteFailureTest() {
		ArtistBean artistResponse = new ArtistBean();
		artistResponse.setArtistLabel("Van");
		ResponseEntity<VoteResponse> response = this.restTemplate.postForEntity(url + "/vote", artistResponse,
				VoteResponse.class);
		VoteResponse voteResponse = response.getBody();
		assertNull(voteResponse.getArtistName());
	}
}