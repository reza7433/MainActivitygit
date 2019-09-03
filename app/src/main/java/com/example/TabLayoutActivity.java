package com.example;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.Fragment.FirstFragment;
import com.example.Fragment.SecondFragment;


public class TabLayoutActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;
    MyViewPagerAdapter myViewPagerAdapter;
    private Object TextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        setContentView(R.layout.activity_tab_layout);

        tabLayout = (TabLayout) findViewById(R.id.tab_tablayout_tab);
        viewPager = (ViewPager) findViewById(R.id.viewPager_tablayout);


        tabLayout.setupWithViewPager(viewPager);

        myViewPagerAdapter.addFragment(new FirstFragment(), "first fragment");
        myViewPagerAdapter.addFragment(new SecondFragment(), "second fragment");
        myViewPagerAdapter.addFragment(new SecondFragment(), "second fragment");

        viewPager.setAdapter(myViewPagerAdapter);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_edit_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_delete_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_edit_black_24dp);

    }


    private void changeTabsFont() {
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTextSize(10);
                }
            }
        }
    }

}