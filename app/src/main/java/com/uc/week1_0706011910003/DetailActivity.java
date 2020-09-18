package com.uc.week1_0706011910003;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.uc.week1_0706011910003.Adapter.MyAdapter;
import com.uc.week1_0706011910003.Model.User;
import com.uc.week1_0706011910003.Model.UserData;

import java.util.ArrayList;
import java.util.Iterator;

public class DetailActivity extends AppCompatActivity {

    ArrayList<User> mContacts = UserData.saveList;
    Intent intent;
    Button button_delete;
    Button button_edit;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    User user;
    int con;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        toolbar = findViewById(R.id.tool_details);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        intent = getIntent();
        con = intent.getIntExtra("mContact", 0);

        mContacts.get(con).getName();
        mContacts.get(con).getAge();
        mContacts.get(con).getAddress();

        TextView set_name = (TextView) findViewById(R.id.view_name);
        set_name.setText(mContacts.get(con).getName());

        TextView set_age = (TextView) findViewById(R.id.view_age);
        set_age.setText(mContacts.get(con).getAge());

        TextView set_address = (TextView) findViewById(R.id.view_address);
        set_address.setText(mContacts.get(con).getName());

        button_delete = findViewById(R.id.button_delete);

                button_delete.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                // TODO Auto-generated method stub
                alertMessage();
            }
        });

        button_edit = findViewById(R.id.button_edit);

        button_edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                Toast.makeText(DetailActivity.this, "bom",
//                        Toast.LENGTH_LONG).show();
                // TODO Auto-generated method stub
                Intent intent = new Intent (DetailActivity.this, AddUserActivity.class);
                intent.putExtra("mContact","detail");
                intent.putExtra("position",con);
                startActivity(intent);
            }
        });

    }

    final Loading loading = new Loading(DetailActivity.this);

    public void alertMessage() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:

//                        Iterator<User> iter = UserData.saveList.iterator();
//                        while (iter.hasNext()) {
//                            User user = iter.next();
//                            if (user.name.equals(mContacts.get(con).getName())) {
//                                iter.remove();
//                            }
//                        }
    
                        mContacts.remove(con);
                        Log.d("test", String.valueOf(con));

                        // Yes button clicked
                        Toast.makeText(DetailActivity.this, "Delete Success",
                                Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                        startActivity(intent);

                        loading.startLoadingDialog();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                            }
                        }, 5000);
                        break;


                    case DialogInterface.BUTTON_NEGATIVE:
                        // No button clicked
                        // do nothing
                        dialog.dismiss();
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure to delete " + mContacts.get(con).getName() + " data?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();



    }



}
