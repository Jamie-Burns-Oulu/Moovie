package jb.moovie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import jb.moovie.search.SearchFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        bottomNavigation.setSelectedItemId(FragmentHandler.getCurrentFragmentId());

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FragmentHandler.getCurrentFragment()).commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selected_fragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.action_search:
                            selected_fragment = new SearchFragment();
                            break;
                        case R.id.action_moovie:
                            selected_fragment = new MoovieFragment();
                            break;
                        case R.id.action_profile:
                            selected_fragment = new ProfileFragment();
                            break;
                    }
                    FragmentManager manager = getSupportFragmentManager();
                    manager.popBackStack();
                    FragmentTransaction ft = manager.beginTransaction();
                    ft.replace(R.id.fragment_container, selected_fragment);
                    ft.commit();
                    return true;
                }
            };
}
