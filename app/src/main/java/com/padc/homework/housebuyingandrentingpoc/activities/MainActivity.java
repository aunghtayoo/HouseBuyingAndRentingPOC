package com.padc.homework.housebuyingandrentingpoc.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.padc.homework.housebuyingandrentingpoc.R;
import com.padc.homework.housebuyingandrentingpoc.adapters.HouseItemsViewRVAdapter;
import com.padc.homework.housebuyingandrentingpoc.data.models.HouseInfoModel;
import com.padc.homework.housebuyingandrentingpoc.data.vos.HouseInfoVO;
import com.padc.homework.housebuyingandrentingpoc.delegates.ForYouFragmentDelegate;
import com.padc.homework.housebuyingandrentingpoc.delegates.HouseItemDelegate;
import com.padc.homework.housebuyingandrentingpoc.fragments.ForYouFragment;
import com.padc.homework.housebuyingandrentingpoc.fragments.TopCollectionFragment;

import java.util.List;

import static com.padc.homework.housebuyingandrentingpoc.activities.DetailActivity.newIntent;

public class MainActivity extends BaseActivity implements HouseItemDelegate, ForYouFragmentDelegate, LocationListener {

    BottomNavigationView bottomNavView;
    LocationManager mLocationManager;
    LatLng mCurrentLocation;

    private static boolean isGridView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife.bind(this);

        bottomNavView = findViewById(R.id.main_bottom_nav);
        setOnNavigationItemSelectedListener();
    }

    private void loadForYouFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_content, new ForYouFragment(this,this))
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
    public void onTabHouseItem(int id) {
        startActivity(newIntent(getApplicationContext(),id));
    }

    @Override
    public void onGetHouseItems(final TopCollectionFragment context, View view) {

        final RecyclerView recyclerView = view.findViewById(R.id.rv_top_collection);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        final HouseItemsViewRVAdapter adapter = new HouseItemsViewRVAdapter(this);


        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

        } else {
            mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }

        mHouseModel.getHouseInfo(new HouseInfoModel.GetEventsFromDataLayerDelegate() {
            @Override
            public void onSuccess(List<HouseInfoVO> houseInfoVOS) {
                adapter.setNewData(houseInfoVOS);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(String message) {
                context.showSnackBar(message);
            }
        });

    }

    @Override
    public void onTabLocation(LatLng latLng) {
        Intent intent = intentGoogleMap(mCurrentLocation,latLng);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else {
            Toast.makeText(this, "Google Map not support!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSearchFilter(String searchWord) {
        List<HouseInfoVO> resultList = mHouseModel.findHouseByName(searchWord);
        if(isGridView){
            showAsGrid(resultList);
        } else {
            showAsList(resultList);
        }
    }

    @Override
    public void onTabGridView() {
        isGridView=true;
        List<HouseInfoVO> houseInfoVOList = mHouseModel.getDataRepository();
        showAsGrid(houseInfoVOList);
    }

    @Override
    public void onTabListView() {
        isGridView=false;
        List<HouseInfoVO> houseInfoVOList = mHouseModel.getDataRepository();
        showAsList(houseInfoVOList);
    }

    private void showAsGrid(List<HouseInfoVO> houseInfoVOList){
        RecyclerView recyclerView = findViewById(R.id.rv_top_collection);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        final HouseItemsViewRVAdapter adapter = new HouseItemsViewRVAdapter(this);
        adapter.setNewData(houseInfoVOList);
        recyclerView.setAdapter(adapter);
    }

    private void showAsList(List<HouseInfoVO> houseInfoVOList){
        RecyclerView recyclerView = findViewById(R.id.rv_top_collection);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        final HouseItemsViewRVAdapter adapter = new HouseItemsViewRVAdapter(this);
        adapter.setNewData(houseInfoVOList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = new LatLng(location.getLatitude(),location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private static Intent intentGoogleMap(LatLng curPos, LatLng destPos){
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr="+curPos.latitude+","+curPos.longitude+"&daddr="+destPos.latitude+","+destPos.longitude));
        return intent;
    }
}