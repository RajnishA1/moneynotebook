package com.example;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moneynotebook.Post;
import com.example.moneynotebook.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Formattable;
import java.util.Objects;

public class postAdapter extends FirebaseRecyclerAdapter<Post, postAdapter.PastViewHolder> {

    public postAdapter(@NonNull FirebaseRecyclerOptions<Post> options) {
        super(options);
    }



    @Override
    protected void onBindViewHolder(@NonNull final postAdapter.PastViewHolder holder,   int i ,@NonNull Post post) {
    holder.dataname.setText(post.getName());
    holder.datarupees.setText(post.getRupees());




    holder.Delete.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {

        }


        public void onClick(View view , int i) {



            try {

                FirebaseDatabase.getInstance().getReference().child("users")
                        .child(getRef(i).getKey())
                        .child("status")
                        .setValue("paid");
                         notifyDataSetChanged();
                Toast.makeText(view.getContext(), i, Toast.LENGTH_SHORT).show();
            } catch (Exception e){
                Toast.makeText(view.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }



        }
    });

   }


    @NonNull
    @Override
    public PastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post, parent, false);

        return new PastViewHolder(view);
    }

    class PastViewHolder extends RecyclerView.ViewHolder{

        TextView dataname , datarupees;
        Button Delete;
        public PastViewHolder(@NonNull View itemView) {
            super(itemView);
            dataname = itemView.findViewById(R.id.dataname);
            datarupees = itemView.findViewById(R.id.datarupees);
            Delete = itemView.findViewById(R.id.Delete);
        }
    }
}




