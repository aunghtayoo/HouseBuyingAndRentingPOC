package com.padc.homework.housebuyingandrentingpoc.activities;

import android.content.Context;
import android.content.Intent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padc.homework.housebuyingandrentingpoc.R;
import com.padc.homework.housebuyingandrentingpoc.data.vos.HouseInfoVO;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity {

    public static final String EXTRA_HOUSE_ID = "eventIdExtra";

    private int id;
    private HouseInfoVO houseInfo;

    public DetailActivity() { }

    public static Intent newIntent(Context context, int eventIdExtra){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_HOUSE_ID,eventIdExtra);
        return intent;
    }

    @BindView(R.id.ic_back)
    ImageView icBack;

    @BindView(R.id.text_price_detail)
    TextView textPrice;

    @BindView(R.id.fab_nav)
    FloatingActionButton fabNavigation;

    @BindView(R.id.text_location)
    TextView locationName;

    @BindView(R.id.img_bg)
    ImageView imgBg;

    @BindView(R.id.text_sqft)
    TextView textSquareFeet;

    @BindView(R.id.lbl_desc)
    TextView lblDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        id = getIntent().getIntExtra(EXTRA_HOUSE_ID,0);
        houseInfo = mHouseModel.findHouseById(id);

        double latitude = houseInfo.getLatitude();
        double longitude = houseInfo.getLongitude();
        fabNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //(1) get current lat,lgn.
                //(2) setup implicit intent to show google map.
            }
        });

        DecimalFormat df = new DecimalFormat(this.getString(R.string.price_format));
        textPrice.setText(df.format(houseInfo.getPrice()));

        locationName.setText(houseInfo.getName());

        Glide.with(this)
                .load(houseInfo.getHouseImageUrl())
                .into(imgBg);

        textSquareFeet.setText(getString(R.string.square_feet_format, houseInfo.getSquareFeet()));

        lblDescription.setText(houseInfo.getDescription());

        icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
