package edu.psu.sweng888.practiceiv.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.psu.sweng888.practiceiv.R;
import edu.psu.sweng888.practiceiv.data.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> mMovieList;

    public MovieAdapter(List<Movie> movieList){
        this.mMovieList = movieList;
    }
    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);
        holder.bind(movie);
    }
    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public void setMovies(List<Movie> movies) {
        mMovieList = movies;
        notifyDataSetChanged();
    }
    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitleTextView;
        private TextView mDirectorTextView;
        private TextView mStudioTextView;
        private TextView mYearTextView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.movie_title);
            mDirectorTextView = itemView.findViewById(R.id.movie_director);
            mStudioTextView = itemView.findViewById(R.id.movie_studio);
            mYearTextView = itemView.findViewById(R.id.movie_year);
        }

        public void bind(Movie movie) {
            mTitleTextView.setText("Title: "+ movie.getTitle());
            mDirectorTextView.setText("Author: "+ movie.getDirector());
            mStudioTextView.setText("Publisher: "+ movie.getStudio());
            mYearTextView.setText("Year: "+ movie.getYear());
        }
    }

}
