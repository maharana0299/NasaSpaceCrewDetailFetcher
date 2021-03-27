package com.internship.apk.nasaspacecrew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

//import com.internship.apk.nasaspacecrew.db.CrewDatabase;
//import com.internship.apk.nasaspacecrew.db.CrewDatabase;
import com.internship.apk.nasaspacecrew.adapters.CrewAdapter;
import com.internship.apk.nasaspacecrew.db.CrewDatabase;
import com.internship.apk.nasaspacecrew.model.CrewListService;
import com.internship.apk.nasaspacecrew.model.CrewMemDb;
import com.internship.apk.nasaspacecrew.model.CrewMember;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "Main Activity";
	private ProgressDialog progressDoalog;
	private RecyclerView rc;
	private Retrofit retrofit;
	private CrewAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		retrofit = new Retrofit.Builder()
				.baseUrl("https://api.spacexdata.com/v4/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		rc = findViewById(R.id.recyclerView);
		rc.setLayoutManager(new LinearLayoutManager(this));
		adapter = new CrewAdapter(this);
		rc.setAdapter(adapter);

		fetchData(retrofit);

		loadData();
	}

	private void fetchData(Retrofit retrofit) {
		if (isNetworkAvailable()){
			progressDoalog = new ProgressDialog(MainActivity.this);
			progressDoalog.setMessage("Loading....");
			progressDoalog.show();

			CrewListService service  = retrofit.create(CrewListService.class);

			Call<List<CrewMember>> call = service.getAllCrewMember();

			call.enqueue(new Callback<List<CrewMember>>() {
				@Override
				public void onResponse(Call<List<CrewMember>> call, Response<List<CrewMember>> response) {
//					Log.d(TAG, "onResponse: " + response.body().get(1));
					progressDoalog.dismiss();

					new InsertAsync().execute(response.body());
				}

				@Override
				public void onFailure(Call<List<CrewMember>> call, Throwable t) {
					Log.d(TAG, "onFailure: " + t.getMessage());
				}
			});
		}
	}

	private void loadData() {
		CrewDatabase.getInstance(getApplicationContext()).personDao().loadAllCrew().observe(
				this, new Observer<List<CrewMemDb>>() {
					@Override
					public void onChanged(List<CrewMemDb> crewMemDbs) {
//						adapter = new CrewAdapter(getApplicationContext(),crewMemDbs);
//						rc.setAdapter(adapter);
						adapter.setList(crewMemDbs);
						Log.d(TAG, "onChanged: " + adapter.getItemCount());
					}
				}

		);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_item, menu);

		MenuItem delete = menu.findItem(R.id.deleteAll);
		MenuItem refresh = menu.findItem(R.id.reLoad);
		delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem menuItem) {
				Log.d(TAG, "onMenuItemClick: ");

				new DeleteTask().execute(getApplication());
				return true;
			}
		});

		refresh.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem menuItem) {

				fetchData(retrofit);
				return false;
			}
		});
		return true;
	}

	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager
				= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	private class InsertAsync extends AsyncTask<List<CrewMember>,Void,Void> {

		@Override
		protected Void doInBackground(List<CrewMember>...lists) {

			List<CrewMemDb> list = new ArrayList<>();

			for (CrewMember c  : lists[0]) {
				CrewMemDb db = new CrewMemDb();
				db.setAgency(c.getAgency());
				db.setName(c.getName());
				db.setImageUrl(c.getImageUrl());
				db.setStatus(c.getStatus());
				db.setWikiUrl(c.getWikiUrl());
				db.setLaunches(c.getLaunches());
				list.add(db);
			}

			CrewDatabase.getInstance(getApplicationContext())
					.personDao().deleteAll();

			CrewDatabase.getInstance(getApplicationContext())
					.personDao().insertAll(list);

			Log.d(TAG, "doInBackground: ");

			Log.d(TAG, "doInBackground: " + list);

			return null;
		}
	}

	private class DeleteTask extends AsyncTask<Context,Void,Void> {


		@Override
		protected Void doInBackground(Context... contexts) {

			CrewDatabase.getInstance(getApplicationContext()).personDao()
					.deleteAll();
//			loadData();

			return null;
		}
	}
}

