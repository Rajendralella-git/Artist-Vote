package com.artist.vote.demo.util;

import java.sql.Timestamp;
import java.util.Date;

public class ArtistUtil {
	public static Timestamp getTimestamp() {
		return new Timestamp(new Date().getTime());
	}
}
