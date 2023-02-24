package com.artist.vote.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artist.vote.demo.model.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {
	public Artist findByArtistLabel(String artistLabel);
}
