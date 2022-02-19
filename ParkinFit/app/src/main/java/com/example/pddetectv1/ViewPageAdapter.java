package com.example.pddetectv1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPageAdapter extends FragmentPagerAdapter {

    public ViewPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment = null;
        if (position == 0)
            fragment = new homefragment();
        else if (position == 1)
            fragment = new profilefragment();
        else if (position == 2)
            fragment = new surveyfragment();
        else if (position==3)
            fragment=new resultfragment();

        return fragment;
    }

    @Override
    public int getCount()
    {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;
        if (position == 0)
            title = "Home";
        else if (position == 1)
            title = "Profile";
        else if (position == 2)
            title = "Severity Survey";
        else if (position==3)
            title="Result";
        return title;
    }
}
