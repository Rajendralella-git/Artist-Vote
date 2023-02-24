package com.artist.vote.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.artist.vote.demo.bean.ArtistBean;
import com.artist.vote.demo.bean.ArtistsResponseList;
import com.artist.vote.demo.bean.VoteResponse;
import com.artist.vote.demo.model.Artist;
import com.artist.vote.demo.service.ArtistVoteService;

@RestController
public class ArtistVoteController {
	@Autowired
	private ArtistVoteService artistVoteService;

	@GetMapping(path = "/artists")
	public ArtistsResponseList getArtistsAndVotes() {
		return artistVoteService.getArtists();
	}

	@GetMapping("/artists/{artistLable}")
	public ArtistBean getArtist(@PathVariable String artistLable) {
		return artistVoteService.getArtist(artistLable);
	}

	@PostMapping("/artists")
	public Artist addArtist(@RequestBody Artist artist) {
		return artistVoteService.saveArtist(artist);
	}

	@PostMapping("/artists/vote")
	public VoteResponse addVote(@RequestBody ArtistBean artistBean) {
		return artistVoteService.saveVote(artistBean.getArtistLabel());
	}

}
