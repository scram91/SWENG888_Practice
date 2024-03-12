package edu.psu.sweng888.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private ArrayList<Book> books;
    private Context mContext;

    public BookAdapter(Context context, ArrayList<Book> books) {
        this.books = books;
        this.mContext = context;
    }

    public BookAdapter(ArrayList<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {
        Book book = books.get(position);
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        holder.publisher.setText(book.getPublisher());
        holder.publication.setText(book.getPublication());
        holder.isbn.setText(Long.toString(book.getIsbn()));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,author, publisher, publication, isbn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title_text);
            author = itemView.findViewById(R.id.author_text);
            publisher = itemView.findViewById(R.id.publisher_text);
            publication = itemView.findViewById(R.id.publication_text);
            isbn = itemView.findViewById(R.id.isbn_text);
        }
    }
}
