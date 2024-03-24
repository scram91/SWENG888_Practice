package edu.psu.sweng888.practiceiv.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.psu.sweng888.practiceiv.R;
import edu.psu.sweng888.practiceiv.data.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> mMovieList;
    boolean isSelected = false;
    private ArrayList<Movie> selectedItems = new ArrayList<>();

    public MovieAdapter(List<Movie> movieList){
        this.mMovieList = movieList;
    }
    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);
        holder.mTitleTextView.setText("Title: "+ movie.getTitle());
        holder.mDirectorTextView.setText("Author: "+ movie.getDirector());
        holder.mStudioTextView.setText("Publisher: "+ movie.getStudio());
        holder.mYearTextView.setText("Year: "+ movie.getYear());
    }
    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public ArrayList<Movie> getSelectedItems(){
        return selectedItems;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitleTextView;
        public TextView mDirectorTextView;
        public TextView mStudioTextView;
        public TextView mYearTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.movie_title);
            mDirectorTextView = itemView.findViewById(R.id.movie_director);
            mStudioTextView = itemView.findViewById(R.id.movie_studio);
            mYearTextView = itemView.findViewById(R.id.movie_year);

            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    // on long click, the selected item will change its background to green and be added to the selected items array
                    isSelected = true;
                    if(selectedItems.contains(mMovieList.get(getAdapterPosition()))){
                        itemView.setBackgroundColor(Color.TRANSPARENT);
                        selectedItems.remove(mMovieList.get(getAdapterPosition()));
                    }else{
                        itemView.setBackgroundColor(Color.CYAN);
                        selectedItems.add(mMovieList.get(getAdapterPosition()));
                    }
                    if (selectedItems.size()==0)
                        isSelected=false;
                    return true;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // if item is in selected mode, it will de select it, otherwise it will select it.
                    if (isSelected){
                        if (selectedItems.contains(mMovieList.get(getAdapterPosition()))){
                            itemView.setBackgroundColor(Color.TRANSPARENT);
                            selectedItems.remove(mMovieList.get(getAdapterPosition()));
                        }
                        else{
                            itemView.setBackgroundColor(Color.CYAN);
                            selectedItems.add(mMovieList.get(getAdapterPosition()));
                        }
                        if(selectedItems.size()==0){
                            isSelected=false;
                        }
                    }
                }
            });
        }
    }

}
