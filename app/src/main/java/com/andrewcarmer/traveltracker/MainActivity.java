package com.andrewcarmer.traveltracker;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.res.Resources;
import android.util.Log;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.FrameLayout;


public class MainActivity extends Activity implements ItemFragment.OnListFragmentInteractionListener {
    public final static String EXTRA_MESSAGE = "com.andrewcarmer.traveltracker.MESSAGE";
    private static final String TAG = "Track The Travel";
    public static final ItemFragment frag1 = ItemFragment.newInstance("This Frag 1", "hello");
    public static final ItemFragment frag2 = ItemFragment.newInstance("This Frag 2", "hello");

    public static String[]  myOptions;
    public static String[]  mySuboptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Log.i(TAG, "Fired up");

        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

//        fragmentTransaction.add(R.id.options_frame, null);
//        fragmentTransaction.add(R.id.suboptions_frame, null);

        fragmentTransaction.commit();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.menu_main, menu);
         return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement

        if ( R.id.expenses == id ) {
            getFragmentManager().beginTransaction().remove(frag2).commit();
            getFragmentManager().beginTransaction().add(R.id.options_frame, frag1).commit();
            return true;
        } else if ( R.id.trips == id ){
            getFragmentManager().beginTransaction().remove(frag1).commit();
            getFragmentManager().beginTransaction().add(R.id.suboptions_frame, frag2).commit();
            return true;
        } else {
            return true;
        }
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void onListFragmentInteraction(String data, int position){
        //do things
        Intent intent = new Intent(this, ExpenceDetails.class);

        intent.putExtra("details_message", data);
        intent.putExtra("details_position", position);

        //this should dynamically be the fragment(check is visible), but I'm not sure if I'm doing it right
        intent.putExtra("fragment", frag1.isVisible());

        startActivity(intent);
    }



}


