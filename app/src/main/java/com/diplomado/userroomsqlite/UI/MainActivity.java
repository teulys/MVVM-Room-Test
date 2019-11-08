package com.diplomado.userroomsqlite.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.diplomado.userroomsqlite.Model.Entities.User;
import com.diplomado.userroomsqlite.R;
import com.diplomado.userroomsqlite.ViewModel.UserViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText tvName, tvLastname, tvFind;
    private ListView lvUsers;
    private UserViewModel userViewModel;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureUI();
    }

    private void configureUI(){
        lvUsers = findViewById(R.id.lvUsers);
        tvName = findViewById(R.id.tvName);
        tvLastname = findViewById(R.id.tvLastName);
        tvFind = findViewById(R.id.tvFind);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        userViewModel.getAllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                String[] values = new String[ users.size() ];
                for(int i = 0; i < users.size(); i++) {
                    values[i] = users.get(i).fisrtName + " " + users.get(i).lastName;
                }
                adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, values);
                lvUsers.setAdapter(adapter);
            }
        });

    }

    public void onClick(View view){
        if (view.getId() == R.id.btSave) {
            if (tvName.getText().toString().isEmpty() || tvLastname.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "Debe colocar nombre y apellido", Toast.LENGTH_LONG).show();
            }
            else {
                userViewModel.addUser(tvName.getText().toString(), tvLastname.getText().toString());
                tvName.setText("");
                tvLastname.setText("");
            }
        }
        else if (view.getId() == R.id.btFind) {
            if (!tvFind.getText().toString().isEmpty()){
                userViewModel.getUserByName(tvFind.getText().toString());
            }
        }
    }
}
