package com.example.wikipeng.coordinatorlayoutdemo00;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * Created by wikipeng on 2017/5/24.
 */

public class CircleActivity extends AppCompatActivity{
    protected AppBarLayout mAppBarLayout;
    protected CollapsingToolbarLayout mCollapsingToolbarLayout;
    protected Toolbar mToolbar;
    protected TextView titleTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);

        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appBar);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        titleTextView = (TextView) mToolbar.findViewById(R.id.headerTitle);
        titleTextView.setText("aabb家长帮圈子");

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    // Collapsed
                    titleTextView.setText("ccdd真心话");
                } else if (verticalOffset == 0) {
                    // Expanded
                    titleTextView.setText("aabb家长帮圈子");
                } else {
                    // Somewhere in between
                }
            }
        });






        ActionBar supportActionBar = getSupportActionBar();
//        supportActionBar.setTitle("真心话");
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDisplayShowTitleEnabled(false);
        //        getSupportActionBar().setTitle("");
//        mToolbar.setTitle("测试");
//        mToolbar.setSubtitle("subTitle");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_circle,menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    }
}
