package jb.moovie.search;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import jb.moovie.R;

public class MovieAdapter extends ArrayAdapter<Movie> {

    private Context mContext;
    private List<Movie> moviesList;

    public MovieAdapter(@NonNull Context context, ArrayList<Movie> list) {
        super(context, 0 , list);
        mContext = context;
        moviesList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.search_list, parent,false);

        final Movie currentMovie = moviesList.get(position);

        ImageView poster = listItem.findViewById(R.id.list_poster);
        Glide.with(getContext()).load(currentMovie.getPoster()).into(poster);

        TextView title = listItem.findViewById(R.id.list_title);
        title.setText(currentMovie.getTitle());

        TextView year = listItem.findViewById(R.id.list_year);
        year.setText(currentMovie.getYear());

        listItem.setTag(currentMovie.getImdbID());

        return listItem;

    }
}
