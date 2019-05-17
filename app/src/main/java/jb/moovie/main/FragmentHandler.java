package jb.moovie.main;

import android.support.v4.app.Fragment;

import jb.moovie.mooive.MoovieFragment;
import jb.moovie.profile.ProfileFragment;
import jb.moovie.R;
import jb.moovie.search.SearchFragment;

public class FragmentHandler {

          private static int currentFragment;

        public static Fragment getCurrentFragment() {
            switch (currentFragment) {
                case 0:
                    return new SearchFragment();
                case 1:
                    return new MoovieFragment();
                case 2:
                    return new ProfileFragment();
            }
            return null;
        }

        public static void setCurrentFragment(int newFragment) {
            currentFragment = newFragment;
        }

        public static int getCurrentFragmentId() {
            switch (currentFragment) {
                case 0:
                    return R.id.action_search;
                case 1:
                    return R.id.action_moovie;
                case 2:
                    return R.id.action_profile;
            }
            return currentFragment;
        }


}
