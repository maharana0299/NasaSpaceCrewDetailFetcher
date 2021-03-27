package com.internship.apk.nasaspacecrew.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;

@Entity(tableName = "CREWS")
public class CrewMemDb {

	public static final String ACTIVE_STATUS = "active";

	@PrimaryKey(autoGenerate = true)
	private int id;

	@ColumnInfo(name = "name")
	private String name;

	@ColumnInfo(name = "agency")
	private String agency;

	@ColumnInfo(name = "image")
	private String imageUrl;

	@ColumnInfo(name = "wikipedia")
	private String wikiUrl;

	@ColumnInfo(name = "status")
	private String status;

	@ColumnInfo(name = "launches")
	private String launches;

	public static String getActiveStatus() {
		return ACTIVE_STATUS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String[] geLaunches() {
		return launches.split(", ");
	}

	public void setLaunches(String[] launches) {
		String str = "";
		for (String s : launches){
			str += s + ", ";
		}
		this.launches = str;
	}

	public String getLaunches() {
		return launches;
	}

	public void setLaunches(String launches) {
		this.launches = launches;
	}

	@Override
	public String toString() {
		return "CrewMemDb{" +
				"id=" + id +
				", name='" + name + '\'' +
				", agency='" + agency + '\'' +
				", imageUrl='" + imageUrl + '\'' +
				", wikiUrl='" + wikiUrl + '\'' +
				", status='" + status + '\'' +
				", agency=" + launches + '\'' +
				'}';
	}
}