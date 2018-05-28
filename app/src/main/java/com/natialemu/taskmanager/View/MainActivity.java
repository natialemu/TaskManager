package com.natialemu.taskmanager.View;


import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.natialemu.taskmanager.R;


public class MainActivity extends AppCompatActivity {
    private ScreenSlidePagerAdapter mPagerAdapter;

    private ViewPager mPager;

    private DrawerLayout mDrawerLayout;

    private static final int NUM_PAGES = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar appBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(appBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        appBar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        appBar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //TODO
            }
        });

        //appBar.setLogo(R.drawable.L);

        mPager = (ViewPager) findViewById(R.id.pager);

        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());

        mPagerAdapter.addFragment(new HighPriorityFragment(),"High priority");
        mPagerAdapter.addFragment(new ConcurrentTaskFragment(),"Concurrent Tasks");
        mPagerAdapter.addFragment(new AllTasksFragement(), "All tasks");

        mPager.setAdapter(mPagerAdapter);

        mDrawerLayout = findViewById(R.id.main_drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        item.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                }
        );





        /**
         *
         * View for displaying all tasks
         * View for displaying a single chain of tasks
         * View for displaying a Collection of chain of tasks
         * View for displaying concurrent tasks
         *
         *
         * A separate task view with info, ability to remove or add dependency to(from list of available tasks)
         *
         *
         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.settings_item: Toast.makeText(this,"more settings will come",Toast.LENGTH_SHORT); return true;
            case R.id.homeAsUp: mDrawerLayout.openDrawer(GravityCompat.START); return true;
            default: return super.onOptionsItemSelected(item);

        }

//        int id = item.getItemId();
//
////            //noinspection SimplifiableIfStatement
////            if (id == R.id.action_settings) {
////                return true;
////            }
////            if(id == R.id.action_refresh){
////                Toast.makeText(MainActivity.this, "Refresh App", Toast.LENGTH_LONG).show();
////            }
////            if(id == R.id.action_new){
////                Toast.makeText(MainActivity.this, "Create Text", Toast.LENGTH_LONG).show();
////            }
//        return super.onOptionsItemSelected(item);
    }



}
