package com.indexyear.jd.watchful;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jason.panzer on 5/7/2017.
 */

public class GridCardAdapter extends BaseAdapter {
    private Context mContext;

    public GridCardAdapter(Context c) {
        mContext = c;
    } //Todo: pass in strings from parent

    public int getCount() {
        return mCardStrings.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new CardView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View card;
        TextView cardText;
        final int passPosition = position + 1;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            card = new View(mContext);
            card = LayoutInflater.from(mContext).inflate(R.layout.cardview_items,parent,false);
            //card.setLayoutParams(new GridView.LayoutParams(300, 300));
            //appButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
            card.setPadding(8, 8, 8, 8);
        } else {
            card = (View) convertView;
        }

        cardText = (TextView) card.findViewById(R.id.title);

        cardText.setText(mCardStrings[position]);

        card.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(passPosition == 1){
                    //build the intent here that leads to RecyclerView
                    Intent intent = new Intent(mContext, RecyclerActivity.class);
                    mContext.startActivity(intent);
                }
                else {
                    Toast.makeText(mContext, "THING" + passPosition,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return card;
    }

    // references to our images
    private String[] mCardStrings = {
            "These Will",
            "Later",
            "Become",
            "Tweets!",
    };
}
