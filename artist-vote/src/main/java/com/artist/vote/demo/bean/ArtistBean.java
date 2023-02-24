package com.artist.vote.demo.bean;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArtistBean {
	private String artistLabel;
	private int votes;
	private String status;
	private Timestamp timestamp;

	public String getArtistLabel() {
		return artistLabel;
	}

	public int getVotes() {
		return votes;
	}

	public String getStatus() {
		return status;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setArtistLabel(String artistLabel) {
		this.artistLabel = artistLabel;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "ArtistBean [artistLabel=" + artistLabel + ", votes=" + votes + ", status=" + status + ", timestamp="
				+ timestamp + "]";
	}

}
