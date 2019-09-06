package com.padc.homework.housebuyingandrentingpoc.views.viewsholder;

import androidx.annotation.NonNull;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.LatLng;
import com.padc.homework.housebuyingandrentingpoc.R;
import com.padc.homework.housebuyingandrentingpoc.data.vos.HouseInfoVO;
import com.padc.homework.housebuyingandrentingpoc.delegates.HouseItemDelegate;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HouseItemViewHolder extends BaseViewHolder<HouseInfoVO> {

    @BindView(R.id.lbl_loc)
    TextView address;

    @BindView(R.id.lbl_price)
    Chip price;

    @BindView(R.id.lbl_room)
    TextView squareFeet;

    @BindView(R.id.img_house)
    ImageView imgHouse;

    @BindView(R.id.fab)
    FloatingActionButton fabNavigation;

    public HouseItemViewHolder(@NonNull View itemView, final HouseItemDelegate delegate) {
        super(itemView);
        ButterKnife.bind(this,itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                delegate.onTabHouseItem(mData.getId());
            }
        });

        fabNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.onTabLocation(new LatLng(mData.getLatitude(),mData.getLongitude()));
            }
        });
    }

    @Override
    public void bindData(HouseInfoVO data) {

        mData = data;

        address.setText(data.getAddress());
        DecimalFormat df = new DecimalFormat("Ks ###,###");
        price.setText(df.format(data.getPrice()));
        squareFeet.setText(String.valueOf(data.getSquareFeet()));
        Glide.with(itemView)
                .load(data.getHouseImageUrl())
                .into(imgHouse);
    }
}
