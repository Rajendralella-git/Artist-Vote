package com.artist.vote.demo.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "artist")
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="artist_id")
	private Integer artistId;
	@Column(name = "artist_label")
	private String artistLabel;
	@Column(name = "artist_name")
	private String artistName;
	@Column(name = "artist_description")
	private String artistDescription;
	@Column(name = "is_active")
	private Short isActive;
	@Column(name = "create_date")
	private Timestamp createDate;
	@Column(name = "update_date")
	private Timestamp updateDate;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "vote_id", referencedColumnName = "artist_id")
	private Set<Vote> vote;

	public Artist() {

	}

	public Integer getArtistId() {
		return artistId;
	}

	public String getArtistLabel() {
		return artistLabel;
	}

	public String getArtistName() {
		return artistName;
	}

	public String getArtistDescription() {
		return artistDescription;
	}

	public Short getIsActive() {
		return isActive;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setArtistId(Integer artistId) {
		this.artistId = artistId;
	}

	public void setArtistLabel(String artistLabel) {
		this.artistLabel = artistLabel;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public void setArtistDescription(String artistDescription) {
		this.artistDescription = artistDescription;
	}

	public void setIsActive(Short isActive) {
		this.isActive = isActive;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "Artist [artistId=" + artistId + ", artistLabel=" + artistLabel + ", artistName=" + artistName
				+ ", artistDescription=" + artistDescription + ", isActive=" + isActive + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", vote=" + vote + "]";
	}

	public Set<Vote> getVote() {
		return vote;
	}

	public void setVote(Set<Vote> vote) {
		this.vote = vote;
	}

}
