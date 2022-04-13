package com.example.moneynotebook;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class viewholder extends RecyclerView.ViewHolder {
    public viewholder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClicklistener.onItemlongclick(view,getAdapterPosition());
                return false;
            }
        });
    }
    public void setData(Context context, String name, String rupees , String status){
        TextView textView = itemView.findViewById(R.id.textview_row);
        TextView textview2 = itemView.findViewById(R.id.textview_row2);
        TextView textview3 = itemView.findViewById(R.id.textview_row3);
        textView.setText("Name : "+name);
        textview2.setText("Rs : "+rupees+".0/-");
        textview3.setText(status);
    }
    private viewholder.Clicklistner mClicklistener;



    public interface Clicklistner{
         void onItemlongclick(View view, int possition);




    }
    public void setOnClickListner(viewholder.Clicklistner clickListner){
    mClicklistener = clickListner;
    }
}
