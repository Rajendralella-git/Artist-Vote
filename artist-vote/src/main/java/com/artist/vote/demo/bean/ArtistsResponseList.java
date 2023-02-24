package com.artist.vote.demo.bean;

import java.sql.Timestamp;
import java.util.Set;

public class ArtistsResponseList {
	private Set<ArtistBean> artistList;
	private String status;
	private Timestamp timestamp;

	public Set<ArtistBean> getArtistList() {
		return artistList;
	}

	public String getStatus() {
		return status;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setArtistList(Set<ArtistBean> artistList) {
		this.artistList = artistList;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "ArtistList [artistList=" + artistList + ", status=" + status + ", timestamp=" + timestamp + "]";
	}
}
