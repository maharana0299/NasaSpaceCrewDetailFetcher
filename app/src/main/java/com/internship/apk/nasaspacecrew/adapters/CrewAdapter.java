package com.internship.apk.nasaspacecrew.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.ColumnInfo;

import com.bumptech.glide.Glide;
import com.internship.apk.nasaspacecrew.MainActivity;
import com.internship.apk.nasaspacecrew.R;
import com.internship.apk.nasaspacecrew.model.CrewMemDb;

import java.util.ArrayList;
import java.util.List;

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.ViewHolder> {

	private final Context context;
	private List<CrewMemDb> lists;

	public CrewAdapter(Context context, List<CrewMemDb> crewMemDbs) {
		this.context = context;
		this.lists = crewMemDbs;
	}

	public CrewAdapter(Context mainActivity) {
		this.context = mainActivity;
		this.lists = new ArrayList<>();
	}

	public void setList(List<CrewMemDb> ls) {
		this.lists = ls;
		notifyDataSetChanged();
	}
	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

		Context context = parent.getContext();
		LayoutInflater inflater = LayoutInflater.from(context);

		// Inflate the custom layout
		View contactView = inflater.inflate(R.layout.item_crew_list, parent, false);
		return new ViewHolder(contactView);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

		CrewMemDb crewMemDb = lists.get(position);

		holder.nameTextView.setText(crewMemDb.getName());
		holder.agencyTextView.setText(crewMemDb.getAgency());
		Glide.with(context)
				.load(crewMemDb.getImageUrl())
				.into(holder.profileImageView);

		holder.moreInfoTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//todo
				String url = crewMemDb.getWikiUrl();
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

				context.startActivity(Intent.createChooser(i,"Open Url")
				.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
			}
		});

		holder.statusTextView.setText(crewMemDb.getStatus());
	}

	@Override
	public int getItemCount() {
		return lists.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {

		private TextView nameTextView, agencyTextView, moreInfoTextView, statusTextView;
		private ImageView profileImageView;
		public ViewHolder(@NonNull View itemView) {
			super(itemView);

			nameTextView = itemView.findViewById(R.id.nameTextView);
			agencyTextView = itemView.findViewById(R.id.agencyTextView);
			moreInfoTextView = itemView.findViewById(R.id.moreInfoTextView);
			profileImageView = itemView.findViewById(R.id.profileImageView);
			statusTextView = itemView.findViewById(R.id.statusTextView);
		}
	}
}
