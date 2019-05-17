package jb.moovie.search.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.transitionseverywhere.ChangeBounds;
import com.transitionseverywhere.ChangeImageTransform;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;

import java.util.Objects;

import jb.moovie.R;
import jb.moovie.main.Movie;
import jb.moovie.search.SearchVolley;


public class DetailsFragment extends Fragment {

    private View view = null;
    private Movie movieDetails = null;
    private ImageView poster;
    boolean expanded = false;
    private ViewGroup transitionsContainer = null;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.details_fragment, container, false);

        transitionsContainer = view.findViewById(R.id.details_fragment);

        movieDetails = SearchVolley.getMovieDetails();

        poster = view.findViewById(R.id.detail_poster);
        poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expanded = !expanded;
                TransitionManager.beginDelayedTransition(transitionsContainer, new TransitionSet()
                        .addTransition(new ChangeBounds())
                        .addTransition(new ChangeImageTransform()));

                ViewGroup.LayoutParams params = poster.getLayoutParams();
                params.height = expanded ? ViewGroup.LayoutParams.MATCH_PARENT :
                        ViewGroup.LayoutParams.WRAP_CONTENT;
                params.width = expanded ? ViewGroup.LayoutParams.MATCH_PARENT :
                        ViewGroup.LayoutParams.WRAP_CONTENT;
                poster.setLayoutParams(params);

                poster.setScaleType(expanded ? ImageView.ScaleType.CENTER_CROP :
                        ImageView.ScaleType.FIT_CENTER);
            }
        });

        setDetails();
        return view;
    }

    private void setDetails() {
        Glide.with(Objects.requireNonNull(getContext())).load(movieDetails.getPoster()).into(poster);

        TextView released = view.findViewById(R.id.detail_released);
        released.setText(movieDetails.getReleased());

        TextView genre = view.findViewById(R.id.detail_genre);
        genre.setText(movieDetails.getGenre());

        TextView runtime = view.findViewById(R.id.detail_runtime);
        runtime.setText(movieDetails.getRuntime());

        TextView imdbRating = view.findViewById(R.id.detail_rating);
        imdbRating.setText(movieDetails.getImdbRating());

        TextView imdbVotes = view.findViewById(R.id.detail_rating_count);
        imdbVotes.setText(movieDetails.getImdbVotes());

        TextView director = view.findViewById(R.id.detail_director);
        director.setText(movieDetails.getDirector());

        TextView writer = view.findViewById(R.id.detail_writer);
        writer.setText(movieDetails.getWriter());

        TextView actors = view.findViewById(R.id.detail_actors);
        actors.setText(movieDetails.getActors());

        TextView plot = view.findViewById(R.id.detail_plot);
        plot.setText(movieDetails.getPlot());

    }

}
