package com.example.Volley.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.example.Volley.R;
import com.example.Volley.adapter.MainActivityPagerAdapter;
import com.example.Volley.os.PagerSlidingTabStrip;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.pagerSlidingTabStrip);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new MainActivityPagerAdapter(getSupportFragmentManager()));
        tabs.setViewPager(viewPager);
    }

}
