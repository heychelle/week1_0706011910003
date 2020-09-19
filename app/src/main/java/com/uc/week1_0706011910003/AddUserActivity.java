package com.uc.week1_0706011910003;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;
import com.uc.week1_0706011910003.Model.User;
import com.uc.week1_0706011910003.Model.UserData;

import java.util.ArrayList;

public class AddUserActivity extends AppCompatActivity implements TextWatcher {

    TextInputLayout input_name, input_age, input_address;
    Button button_data;
    Button button2;
    String name, address, age;
    Intent intent;
    Intent intent2;
    String con;
    int daftar;
    ArrayList<User> mContacts = UserData.saveList;

    Toolbar toolbar;

    static ArrayList<User> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user_activity);

        button2 = findViewById(R.id.button_user_data);
        input_name = findViewById(R.id.input_name);
        input_age = findViewById(R.id.input_age);
        input_address = findViewById(R.id.input_address);
        button_data = findViewById(R.id.button_user_data);
        toolbar = findViewById(R.id.tool_add);

        input_name.getEditText().addTextChangedListener(this);
        input_age.getEditText().addTextChangedListener(this);
        input_address.getEditText().addTextChangedListener(this);

        final Loading loading = new Loading(AddUserActivity.this);

        intent = getIntent();
        con = intent.getStringExtra("mContact");

        intent2 = getIntent();
        daftar = intent2.getIntExtra("position", 0);

        if (con.equalsIgnoreCase("main")) {
            toolbar.setTitle("Add User");
            button2.setText("Save Data");
        } else {
            toolbar.setTitle("Edit User");
            button2.setText("Update Data");
            TextInputLayout set_name = (TextInputLayout) findViewById(R.id.input_name);
            set_name.getEditText().setText(mContacts.get(daftar).getName());

            TextInputLayout set_age = (TextInputLayout) findViewById(R.id.input_age);
            set_age.getEditText().setText(mContacts.get(daftar).getAge());
            String txt = " years old";
//            age = age.replaceAll("\\D+","");
//            input_age.setText(age);

            TextInputLayout set_address = (TextInputLayout) findViewById(R.id.input_address);
            set_address.getEditText().setText(mContacts.get(daftar).getAddress());
        }

        button_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (con.equalsIgnoreCase("main")) {
                    final User contact = new User(name, address, age);

                    UserData.saveList.add(contact);

                    loading.startLoadingDialog();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(AddUserActivity.this, MainActivity.class);
                            intent.putExtra("dataUser", contact);
                            startActivity(intent);
                            finish();
                        }
                    }, 5000);
                } else {

                    mContacts.get(daftar).setName(name);
                    mContacts.get(daftar).setAge(age);
                    mContacts.get(daftar).setAddress(address);
                    Intent intent = new Intent(AddUserActivity.this, DetailActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(con.equalsIgnoreCase("main")){
                    Intent intent = new Intent(AddUserActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(AddUserActivity.this, DetailActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        name = input_name.getEditText().getText().toString().trim();
        age = input_age.getEditText().getText().toString().trim();
        address = input_address.getEditText().getText().toString().trim();

        if (!name.isEmpty() && !address.isEmpty() && !age.isEmpty()) {
            button_data.setEnabled(true);
        } else {
            button_data.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
