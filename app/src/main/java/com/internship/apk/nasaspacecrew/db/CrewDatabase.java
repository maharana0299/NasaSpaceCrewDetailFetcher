package com.internship.apk.nasaspacecrew.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.internship.apk.nasaspacecrew.dao.CrewDao;
import com.internship.apk.nasaspacecrew.model.CrewMemDb;


@Database(entities = CrewMemDb.class,exportSchema = false,version = 2)
public abstract class CrewDatabase extends RoomDatabase {
	public static final String DB_NAME = "crew_db";
	private static CrewDatabase instance;

	public static synchronized CrewDatabase getInstance(Context context){
		if (instance == null) {
			instance = Room.databaseBuilder(context.getApplicationContext(),CrewDatabase.class,DB_NAME)
					.fallbackToDestructiveMigration()
					.build();
		}

		return instance;
	}

	public abstract CrewDao personDao();
}
