package com.internship.apk.nasaspacecrew.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.internship.apk.nasaspacecrew.model.CrewMemDb;

import java.util.List;

@Dao
public interface CrewDao {

	@Query("SELECT * FROM CREWS")
	LiveData<List<CrewMemDb>> loadAllCrew();

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insertAll(List<CrewMemDb> members);

	@Query("DELETE FROM CREWS")
	void deleteAll();
}
