package com.padc.homework.housebuyingandrentingpoc.views.viewsholder;

import android.support.annotation.NonNull;
import android.support.design.chip.Chip;
import android.view.View;
import android.widget.TextView;

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

    public HouseItemViewHolder(@NonNull View itemView, final HouseItemDelegate delegate) {
        super(itemView);
        ButterKnife.bind(this,itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delegate.onTabHouseItem();
            }
        });
    }

    @Override
    public void bindData(HouseInfoVO data) {

        address.setText(data.getAddress());

        DecimalFormat df = new DecimalFormat("$ ##");
        price.setText(df.format(data.getSquare_feet()));

        squareFeet.setText(String.valueOf(data.getSquare_feet()));
    }
}
