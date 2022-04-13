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
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class postlistActivity extends AppCompatActivity {
    RecyclerView historyrecycle;
    DatabaseReference databaseReference;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlist);
        historyrecycle = findViewById(R.id.recycler);
        historyrecycle.setHasFixedSize(true);
        ImageView imageView = findViewById(R.id.history_icon);

        historyrecycle.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(postlistActivity.this,Historypage.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseRecyclerOptions<Post> options =
                new FirebaseRecyclerOptions.Builder<Post>()
                        .setQuery(databaseReference,Post.class).build();

        FirebaseRecyclerAdapter<Post, viewholder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<Post, viewholder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull viewholder holder, int i, @NonNull Post post) {
                        holder.setData(getApplicationContext(),post.getName() , post.getRupees() , null);
                        holder.setOnClickListner(new viewholder.Clicklistner() {
                            @Override
                            public void onItemlongclick(View view, int possition) {
                                name = getItem(possition).getName();

                                showDeleteDialog(name);
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.row,parent,false);
                        return new viewholder(view);
                    }
                };
        firebaseRecyclerAdapter.startListening();
        historyrecycle.setAdapter(firebaseRecyclerAdapter);

    }
    private void showDeleteDialog(String name){
        AlertDialog.Builder builder = new AlertDialog.Builder(postlistActivity.this);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure want to delete");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Query query = databaseReference.orderByChild("name").equalTo(name);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()){

                            ds.getRef().removeValue();

                        }
                        Toast.makeText(postlistActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }

}