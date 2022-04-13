package com.example.moneynotebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Historypage extends AppCompatActivity {
    RecyclerView historyrecycle;
    DatabaseReference databaseReference;
    LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historypage);
        historyrecycle = findViewById(R.id.historyrecycle);
        historyrecycle.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        historyrecycle.setLayoutManager(linearLayoutManager);
        onStart();
    }
    @Override
        protected void onStart() {
            super.onStart();
            databaseReference = FirebaseDatabase.getInstance().getReference().child("backup");
            FirebaseRecyclerOptions<Post> options =
                    new FirebaseRecyclerOptions.Builder<Post>()

                            .setQuery(databaseReference, Post.class).build();

            FirebaseRecyclerAdapter<Post, viewholder> firebaseRecyclerAdapter =
                    new FirebaseRecyclerAdapter<Post, viewholder>(options) {
                        @Override
                        protected void onBindViewHolder(@NonNull viewholder holder, int i, @NonNull Post post) {
                            holder.setData(getApplicationContext(), post.getName(), post.getRupees(), post.getStatus());


                        }

                        @NonNull
                        @Override
                        public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            View view = LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.row, parent, false);
                            return new viewholder(view);
                        }
                    };
            firebaseRecyclerAdapter.startListening();
            historyrecycle.setAdapter(firebaseRecyclerAdapter);

        }


    }


