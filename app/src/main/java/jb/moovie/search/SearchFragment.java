package jb.moovie.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import jb.moovie.R;

public class SearchFragment extends Fragment {

    private View view = null;
    private EditText searchField;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_fragment, container, false);
        searchField = view.findViewById(R.id.search_bar);
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
                if(count>=3){
                    SearchVolley.filmSearch(s, getContext());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
