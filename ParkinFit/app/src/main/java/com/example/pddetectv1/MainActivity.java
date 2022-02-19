package com.example.pddetectv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tablayout;
    ViewPager viewPager;
    ViewPageAdapter viewPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tablayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager);
        viewPageAdapter = new ViewPageAdapter(
                getSupportFragmentManager());
        viewPager.setAdapter(viewPageAdapter);
        tablayout.setupWithViewPager(viewPager);
    }
}