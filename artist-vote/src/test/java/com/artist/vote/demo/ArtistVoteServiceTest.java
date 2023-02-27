package com.artist.vote.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.artist.vote.demo.bean.ArtistBean;
import com.artist.vote.demo.bean.ArtistsResponseList;
import com.artist.vote.demo.bean.VoteResponse;
import com.artist.vote.demo.model.Artist;
import com.artist.vote.demo.model.Vote;
import com.artist.vote.demo.repository.ArtistRepository;
import com.artist.vote.demo.repository.VoteRepository;
import com.artist.vote.demo.service.ArtistVoteService;
import com.artist.vote.demo.util.ArtistUtil;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ArtistVoteServiceTest {
	@InjectMocks
	private ArtistVoteService artistVoteService;

	@Mock
	private ArtistRepository artistRepository;
	@Mock
	private VoteRepository voteRepository;

	@Test
	public void getArtistsTest() {
		Artist artist1 = getArtist(1, "led_zeppelin", "Led Zeppelin");
		Artist artist2 = getArtist(2, "van_halen", "Van Halen");
		Set<Vote> setOfVotes = getSetOfVotes(artist1.getArtistId());
		Set<Vote> setOfVotes1 = getSetOfVotes(artist2.getArtistId());
		List<Artist> artistList = new ArrayList<>();
		artistList.add(artist1);
		artistList.add(artist2);
		when(artistRepository.findAll()).thenReturn(artistList);
		when(voteRepository.findByArtistId(1)).thenReturn(setOfVotes);
		when(voteRepository.findByArtistId(2)).thenReturn(setOfVotes1);
		ArtistsResponseList artistsResponseList = artistVoteService.getArtists();
		assertEquals(2, artistsResponseList.getArtistList().size());
		assertEquals("SUCCESS", artistsResponseList.getStatus());
	}

	@Test
	public void getArtistTest() {
		Artist artist = getArtist(2, "van_halen", "Van Halen");
		Set<Vote> setOfVotes = getSetOfVotes(artist.getArtistId());
		when(artistRepository.findByArtistLabel("van_halen")).thenReturn(artist);
		when(voteRepository.findByArtistId(artist.getArtistId())).thenReturn(setOfVotes);
		ArtistBean artistBean = artistVoteService.getArtist("van_halen");
		assertEquals(3, artistBean.getVotes());
		assertEquals("SUCCESS", artistBean.getStatus());
	}

	@Test
	public void saveArtistTest() {
		Artist artist = getArtist(3, "John_Doe", "John Doe");
		Vote vote = new Vote();
		vote.setVoteId(10);
		Timestamp timestamp = ArtistUtil.getTimestamp();
		vote.setArtistId(artist.getArtistId());
		vote.setCreateDate(timestamp);
		vote.setUpdateDate(timestamp);
		when(artistRepository.save(any(Artist.class))).thenReturn(artist);
		when(voteRepository.save(any(Vote.class))).thenReturn(vote);
		Artist artistResponseArtist = artistVoteService.saveArtist(artist);
		assertEquals(3, artistResponseArtist.getArtistId());
		assertEquals("John_Doe", artistResponseArtist.getArtistLabel());
	}

	@Test
	public void saveVoteTest() {
		Artist artist = getArtist(2, "van_halen", "Van Halen");
		Vote vote = new Vote();
		vote.setVoteId(10);
		Timestamp timestamp = ArtistUtil.getTimestamp();
		vote.setArtistId(artist.getArtistId());
		vote.setCreateDate(timestamp);
		vote.setUpdateDate(timestamp);
		when(artistRepository.findByArtistLabel("van_halen")).thenReturn(artist);
		when(voteRepository.save(any(Vote.class))).thenReturn(vote);
		VoteResponse voteResponse = artistVoteService.saveVote("van_halen");
		assertEquals("SUCCESS", voteResponse.getMessage());
	}

	private Set<Vote> getSetOfVotes(Integer artistId) {
		Set<Vote> setOfVotes = new HashSet<>();
		Vote vote1 = new Vote();
		vote1.setArtistId(artistId);
		vote1.setVoteId(3);
		Vote vote2 = new Vote();
		vote2.setArtistId(artistId);
		vote2.setVoteId(4);
		Vote vote3 = new Vote();
		vote3.setArtistId(artistId);
		vote3.setVoteId(5);
		setOfVotes.add(vote1);
		setOfVotes.add(vote2);
		setOfVotes.add(vote3);
		return setOfVotes;
	}

	private Artist getArtist(Integer id, String artistLabel, String artistName) {
		Artist artist = new Artist();
		artist.setArtistId(id);
		artist.setArtistLabel(artistLabel);
		artist.setArtistName(artistName);
		return artist;
	}
}
