package jb.moovie.search;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Objects;

import jb.moovie.R;

public class SearchFragment extends Fragment {

    private View view = null;
    private EditText searchField;
    private ConstraintLayout constraintLayout;
    private ListView listView;
    MovieAdapter mAdapter = null;
    ViewGroup transitionsContainer = null;
    ConstraintSet originalConstraints = new ConstraintSet();


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

    private void search() {
        searchField.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    if(mAdapter != null) {
                        mAdapter.clear();
                        adjustView(false);
                    }
                }else{
                    adjustView(true);
                }

                SearchVolley.filmSearch(s, getContext());

                mAdapter = new MovieAdapter(Objects.requireNonNull(getContext()), SearchVolley.getMoviesList());
                listView.setAdapter(mAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void adjustView(boolean b) {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        ViewGroup.LayoutParams params = searchField.getLayoutParams();

        if (b) {
            constraintSet.clear(R.id.search_bar, ConstraintSet.BOTTOM);
            constraintSet.applyTo(constraintLayout);
            params.width = (int) (size.x * 0.95);
        }else{
            originalConstraints.applyTo(constraintLayout);
            params.width = (int) (size.x * 0.65);
        }

        searchField.setLayoutParams(params);
    }

}
