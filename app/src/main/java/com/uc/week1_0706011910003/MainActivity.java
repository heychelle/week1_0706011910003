    package com.uc.week1_0706011910003;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uc.week1_0706011910003.Adapter.MyAdapter;
import com.uc.week1_0706011910003.Model.User;
import com.uc.week1_0706011910003.Model.UserData;

import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity implements MyAdapter.OnNoteListener {

        FloatingActionButton btn_add;
        ArrayList<User> mContacts = UserData.saveList;
        RecyclerView mRecyclerView;
        RecyclerView.LayoutManager mLayoutManager;
        RecyclerView.Adapter mAdapter;
        User user;
        TextView no_data;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            btn_add = findViewById(R.id.add_button);
            no_data = findViewById(R.id.no_data);

            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent (MainActivity.this, AddUserActivity.class);
                    intent.putExtra("mContact","main");
                    startActivity(intent);
                }
            });

            if(getIntent().getParcelableExtra("dataUser") !=null){
                user = getIntent().getParcelableExtra("dataUser");
            }
            mRecyclerView = findViewById(R.id.recyclerView);

            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mAdapter = new MyAdapter(mContacts, this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);

            if (mContacts.isEmpty()){
                no_data.setText("No Data");
            }else{
                no_data.setText("");
            }
        }
        @Override
        public void OnNoteClick(int position) {
            mContacts.get(position);
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("mContact", position);
            startActivity(intent);
            finish();
        }
        public boolean doublebackToExitPressedOnce = false;
        @Override
        protected void onResume(){
            super.onResume();
            this.doublebackToExitPressedOnce = false;
        }

        @Override
        public void onBackPressed() {
            if (doublebackToExitPressedOnce) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                startActivity(intent);
            }
            this.doublebackToExitPressedOnce = true;
            Toast.makeText(MainActivity.this, "Press back once again to exit the apps!", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doublebackToExitPressedOnce = false;
                }
            }, 5000);
        }
    }