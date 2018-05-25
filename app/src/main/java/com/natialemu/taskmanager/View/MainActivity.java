package com.natialemu.taskmanager.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.natialemu.taskmanager.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar appBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(appBar);
        //appBar.setLogo(R.drawable.L);


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
    public boolean onCreateOptionsMenu(Menu menu){
        //add items to menu bar

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//            //noinspection SimplifiableIfStatement
//            if (id == R.id.action_settings) {
//                return true;
//            }
//            if(id == R.id.action_refresh){
//                Toast.makeText(MainActivity.this, "Refresh App", Toast.LENGTH_LONG).show();
//            }
//            if(id == R.id.action_new){
//                Toast.makeText(MainActivity.this, "Create Text", Toast.LENGTH_LONG).show();
//            }
        return super.onOptionsItemSelected(item);
    }
}
