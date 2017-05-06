package com.indexyear.jd.watchful;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by jd on 5/4/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    String[] leftString, rightString;
    Context context;
    View view1;
    ViewHolder viewHolder1;
    TextView textView;

    public RecyclerViewAdapter(Context context1,String[] SubjectValues1, String[] SubjectValues2){

        leftString = SubjectValues1;
        rightString = SubjectValues2;
        context = context1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textView1, textView2;

        public ViewHolder(View v){

            super(v);

            textView1 = (TextView)v.findViewById(R.id.subject_textview);
            textView2 = (TextView)v.findViewById(R.id.object_textview);
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        view1 = LayoutInflater.from(context).inflate(R.layout.recyclerview_items,parent,false);

        viewHolder1 = new ViewHolder(view1);

        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        holder.textView1.setText(leftString[position]);
        holder.textView2.setText(rightString[position]);
    }

    @Override
    public int getItemCount(){

        return leftString.length;
    }
}
