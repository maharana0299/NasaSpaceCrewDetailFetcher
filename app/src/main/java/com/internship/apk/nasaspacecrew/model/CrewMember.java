package com.internship.apk.nasaspacecrew.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;


public class CrewMember {

	public static final String ACTIVE_STATUS = "active";

	@SerializedName("name")
	private String name;

	@SerializedName("agency")
	private String agency;

	@SerializedName("image")
	private String imageUrl;

	@SerializedName("wikipedia")
	private String wikiUrl;

	@SerializedName("launches")
	private String[] launches;

	@SerializedName("status")
	private String status;

	@SerializedName("id")
	private String uId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getWikiUrl() {
		return wikiUrl;
	}

	public void setWikiUrl(String wikiUrl) {
		this.wikiUrl = wikiUrl;
	}

	public String[] getLaunches() {
		return launches;
	}

	public void setLaunches(String[] launches) {
		this.launches = launches;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	@Override
	public String toString() {
		return "CrewMember{" +
				"name='" + name + '\'' +
				", agency='" + agency + '\'' +
				", imageUrl='" + imageUrl + '\'' +
				", wikiUrl='" + wikiUrl + '\'' +
				", launches=" + Arrays.toString(launches) +
				", status='" + status + '\'' +
				", uId='" + uId + '\'' +
				'}';
	}
}

/*
{
        "name": "Robert Behnken",
        "agency": "NASA",
        "image": "https://imgur.com/0smMgMH.png",
        "wikipedia": "https://en.wikipedia.org/wiki/Robert_L._Behnken",
        "launches": [
            "5eb87d46ffd86e000604b388"
        ],
        "status": "active",
        "id": "5ebf1a6e23a9a60006e03a7a"
    },
 */