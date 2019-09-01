package com.padc.homework.housebuyingandrentingpoc.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.view.MenuItem;

import com.padc.homework.housebuyingandrentingpoc.R;
import com.padc.homework.housebuyingandrentingpoc.delegates.HouseItemDelegate;
import com.padc.homework.housebuyingandrentingpoc.fragments.ForYouFragment;

public class MainActivity extends BaseActivity implements HouseItemDelegate {

    BottomNavigationView bottomNavView;

    private static MainActivity getObjInstance(){
        return new MainActivity();
    }

    private static Intent newIntent(){
        Intent intent = new Intent(getObjInstance(), DetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavView = findViewById(R.id.main_bottom_nav);
        setOnNavigationItemSelectedListener();
    }

    private void loadForYouFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_content, new ForYouFragment(this))
                .addToBackStack(null)
                .commit();
    }

    private void setOnNavigationItemSelectedListener(){
        //set initial view.
        int id = bottomNavView.getSelectedItemId();
        switch (id){
            case R.id.action_for_you :
                loadForYouFragment();
                break;
            case R.id.action_find_me :
                //loadSearchFragment();
                break;
            case R.id.action_favourite :
                //Event
                break;
            case R.id.action_browse :
                //Action
                break;
            case R.id.action_profile :
                break;
            /*case R.id.action_more :
                break;*/
        }

        //listen and set selected view.
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.action_for_you :
                        loadForYouFragment();
                        break;
                    case R.id.action_find_me :
                        //loadSearchFragment();
                        break;
                    case R.id.action_favourite :
                        //Event
                        break;
                    case R.id.action_browse :
                        //Action
                        break;
                    case R.id.action_profile :
                        break;
                    /*case R.id.action_more :
                        break;*/
                }
                return true;
            }
        });
    }

    @Override
    public void onTabHouseItem() {
        startActivity(newIntent());
    }
}
