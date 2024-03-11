package edu.psu.sweng888.sweng888practice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter {
    private List<Movies> movies;

    public MovieAdapter(List<Movies> movies) { this.movies = movies; }

   // @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    //@Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView, categoryTextView, yearTextView, ratingTextView, descriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}