package jb.moovie.search;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.transitionseverywhere.ArcMotion;
import com.transitionseverywhere.ChangeBounds;
import com.transitionseverywhere.Fade;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.transitionseverywhere.extra.Scale;

import java.util.Objects;

import jb.moovie.R;

public class SearchFragment extends Fragment {

    private View view = null;
    private EditText searchField;
    private ConstraintLayout constraintLayout;
    private ListView listView;
    private MovieAdapter mAdapter = null;
    private ViewGroup transitionsContainer = null;
    private ConstraintSet originalConstraints = new ConstraintSet();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_fragment, container, false);

        searchField = view.findViewById(R.id.search_bar);
        transitionsContainer = view.findViewById(R.id.search_fragment);
        listView = view.findViewById(R.id.movies_list);
        constraintLayout = (ConstraintLayout) transitionsContainer;
        originalConstraints.clone(constraintLayout);

        search();

        return view;
    }

    //TODO MAKE UPDATES WORK AS INTENDED
    private void search() {
        searchField.addTextChangedListener(new TextWatcher() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(mAdapter != null)  mAdapter.clear();
                SearchVolley.filmSearch(s, getContext());
                mAdapter = new MovieAdapter(Objects.requireNonNull(getContext()), SearchVolley.getMoviesList());
                if (s.length() == 0) {
                    adjustView(false);
                    mAdapter.clear();
                } else {
                    details();
                    adjustView(true);
                    listView.setAdapter(mAdapter);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    //TODO FIX UPDATING BUG
    @SuppressLint("ClickableViewAccessibility")
    private void details() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long idx) {
                SearchVolley.filmDetails(String.valueOf(view.getTag()), getContext());
                DetailsFragment detailsFragment = new DetailsFragment();
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, detailsFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void adjustView(boolean b) {
        Display display = Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        ViewGroup.LayoutParams params = searchField.getLayoutParams();

        TransitionManager.beginDelayedTransition(transitionsContainer,
                new ChangeBounds().setPathMotion(new ArcMotion()).setDuration(500));

        if (b) {
            constraintSet.clear(R.id.search_bar, ConstraintSet.BOTTOM);
            constraintSet.applyTo(constraintLayout);
            params.width = (int) (size.x * 0.95);
        } else {
            originalConstraints.applyTo(constraintLayout);
            params.width = (int) (size.x * 0.65);
        }
        searchField.setLayoutParams(params);
    }

}