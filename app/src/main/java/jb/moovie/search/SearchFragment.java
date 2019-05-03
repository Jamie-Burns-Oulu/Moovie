package jb.moovie.search;

import android.animation.LayoutTransition;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Objects;

import jb.moovie.FragmentHandler;
import jb.moovie.R;

public class SearchFragment extends Fragment {

    private View view = null;
    private EditText searchField;
    private ConstraintLayout constraintLayout;
    private ListView listView;
    MovieAdapter mAdapter = null;
    ViewGroup.LayoutParams params;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_fragment, container, false);

        searchField = view.findViewById(R.id.search_bar);
        constraintLayout = view.findViewById(R.id.search_fragment);
        params = searchField.getLayoutParams();

        search();

        return view;
    }

    private void search() {
        searchField.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ((mAdapter != null) && (count == 0)) {
                    mAdapter.clear();
                    adjustView(false);
                }
                adjustView(true);
                SearchVolley.filmSearch(s, getContext());
                listView = view.findViewById(R.id.movies_list);
                mAdapter = new MovieAdapter(Objects.requireNonNull(getContext()), SearchVolley.getMoviesList());
                listView.setAdapter(mAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void adjustView(boolean b) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        if (b) {
            constraintSet.clear(R.id.search_bar, ConstraintSet.BOTTOM);
            constraintSet.applyTo(constraintLayout);
            params.width = ((int) (size.x * 0.95));
        } else {
            params.width = ((int) (size.x * 0.5));
        }
        searchField.setLayoutParams(params);
        ((ViewGroup) view.findViewById(R.id.search_fragment)).getLayoutTransition()
                .enableTransitionType(LayoutTransition.CHANGING);
        //TODO FIX THIS
    }
}
