package com.artist.vote.demo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artist.vote.demo.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
	public Set<Vote> findByArtistId(Integer artistId);
}
