package com.internship.apk.nasaspacecrew.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CrewListService {

	@GET("crew")
	Call<List<CrewMember>> getAllCrewMember();
}
