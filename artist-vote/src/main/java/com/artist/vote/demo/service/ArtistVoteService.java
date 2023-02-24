package com.artist.vote.demo.service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artist.vote.demo.bean.ArtistBean;
import com.artist.vote.demo.bean.ArtistsResponseList;
import com.artist.vote.demo.bean.VoteResponse;
import com.artist.vote.demo.global.application.exceptions.ArtistNotFoundException;
import com.artist.vote.demo.model.Artist;
import com.artist.vote.demo.model.Vote;
import com.artist.vote.demo.repository.ArtistRepository;
import com.artist.vote.demo.repository.VoteRepository;
import com.artist.vote.demo.util.ArtistUtil;

@Service
public class ArtistVoteService {
	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private VoteRepository voteRepository;

	public ArtistsResponseList getArtists() {
		List<Artist> artistList = artistRepository.findAll();
		Set<ArtistBean> artistBeanList = new HashSet<>();
		ArtistsResponseList artistsList = new ArtistsResponseList();
		for (Artist artist : artistList) {
			ArtistBean artistBean = new ArtistBean();
			artistBean.setArtistLabel(artist.getArtistLabel());
			Set<Vote> votesList = voteRepository.findByArtistId(artist.getArtistId());
			artistBean.setVotes(votesList.size());
			artistBeanList.add(artistBean);
		}
		artistsList.setArtistList(artistBeanList);
		artistsList.setStatus("SUCCESS");
		artistsList.setTimestamp(ArtistUtil.getTimestamp());
		return artistsList;
	}

	public ArtistBean getArtist(String artistLable) {
		Artist artist = artistRepository.findByArtistLabel(artistLable);
		Optional<Artist> optional = Optional.ofNullable(artist);
		if (!optional.isPresent()) {
			throw new ArtistNotFoundException("Artist Not Found");
		}
		Set<Vote> votes = voteRepository.findByArtistId(artist.getArtistId());
		ArtistBean artistBean = new ArtistBean();
		artistBean.setArtistLabel(artistLable);
		artistBean.setVotes(votes.size());
		artistBean.setStatus("SUCCESS");
		artistBean.setTimestamp(ArtistUtil.getTimestamp());
		return artistBean;
	}

	@Transactional
	public Artist saveArtist(Artist artist) {
		Timestamp timestamp = ArtistUtil.getTimestamp();
		artist.setCreateDate(timestamp);
		artist.setUpdateDate(timestamp);
		artistRepository.save(artist);
		Vote vote = new Vote();
		vote.setArtistId(artist.getArtistId());
		vote.setCreateDate(timestamp);
		vote.setUpdateDate(timestamp);
		Set<Vote> voteSet = new HashSet<>();
		voteSet.add(vote);
		artist.setVote(voteSet);
		voteRepository.save(vote);
		return artist;
	}

	@Transactional
	public VoteResponse saveVote(String artistLable) {
		Artist artist = artistRepository.findByArtistLabel(artistLable);
		Optional<Artist> optional = Optional.ofNullable(artist);
		Vote vote = null;
		VoteResponse voteResponse = null;
		if (!optional.isPresent()) {
			throw new ArtistNotFoundException("Artist not found");
		}
		vote = new Vote();
		Timestamp timestamp = ArtistUtil.getTimestamp();
		vote.setArtistId(artist.getArtistId());
		vote.setCreateDate(timestamp);
		vote.setUpdateDate(timestamp);
		voteRepository.save(vote);
		voteResponse = new VoteResponse();
		voteResponse.setArtistName(artist.getArtistLabel());
		voteResponse.setVoteId(vote.getVoteId());
		voteResponse.setOperationTime(timestamp);
		voteResponse.setMessage("SUCCESS");
		return voteResponse;
	}
}
