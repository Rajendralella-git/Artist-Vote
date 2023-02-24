package com.artist.vote.demo.bean;

import java.sql.Timestamp;

public class VoteResponse {

	private Integer voteId;
	private String artistName;
	private String message;
	private Timestamp operationTime;

	public Integer getVoteId() {
		return voteId;
	}

	public String getArtistName() {
		return artistName;
	}

	public String getMessage() {
		return message;
	}

	public void setVoteId(Integer voteId) {
		this.voteId = voteId;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Timestamp operationTime) {
		this.operationTime = operationTime;
	}
}
