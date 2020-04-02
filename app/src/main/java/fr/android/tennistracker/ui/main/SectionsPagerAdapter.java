package fr.android.tennistracker.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import fr.android.tennistracker.vue.tabbed_statistics.Fragment1Statistiques;
import fr.android.tennistracker.vue.tabbed_statistics.Fragment2Graphiques;
import fr.android.tennistracker.vue.tabbed_statistics.Fragment3Hitorique;
import fr.android.tennistracker.R;
import fr.android.tennistracker.vue.tabbed_statistics.ScoreDetailsActivity;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_title_statistiques, R.string.tab_title_graphiques, R.string.tab_title_historique};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch(position){
            case 0:
                fragment = new Fragment1Statistiques();
                ScoreDetailsActivity scoreDetailsActivity = (ScoreDetailsActivity)mContext;
                Bundle bundle = scoreDetailsActivity.getIntent().getExtras();
                fragment.setArguments(bundle);
                break;
            case 1:
                fragment = new Fragment2Graphiques();
                break;
            case 2:
                fragment = new Fragment3Hitorique();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }
}