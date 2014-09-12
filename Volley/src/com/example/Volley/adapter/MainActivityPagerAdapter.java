package com.example.Volley.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.example.Volley.fragment.DownloadFragment;
import com.example.Volley.fragment.PictureFragment;
import com.example.Volley.fragment.PokemonFragment;
import com.example.Volley.fragment.SimpleFragment;


public class MainActivityPagerAdapter extends MyFragmentPagerAdapter {

    public MainActivityPagerAdapter(FragmentManager fragmentManager) {

        super(fragmentManager);
    }

    private final String[] TITLES = {"神奇宝贝", "自然风景", "下载"};

    @Override
    public CharSequence getPageTitle(int position) {

        return TITLES[position];
    }

    @Override
    public int getCount() {

        return TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new PokemonFragment();
            case 1:
                return new PictureFragment();
            case 2:
                return new DownloadFragment();

            default:
                return null;
        }
    }

}