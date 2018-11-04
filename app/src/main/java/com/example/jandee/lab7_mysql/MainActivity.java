package com.example.jandee.lab7_mysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText txtID, txtName,txtTel,txtEmail;
    private Button btnSave;
    private ListView dataView;
    private MySQLConnect mySQLConnect;
    private List<String> items;
    private ArrayAdapter<String> adt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        update();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySQLConnect.sentData(txtID.getText().toString(),txtName.getText().toString(),txtTel.getText().toString(),txtEmail.getText().toString());

                items.add(txtID.getText().toString()+"\n"+ txtName.getText().toString()+"\n"+txtTel.getText().toString()+"\n"+txtEmail.getText().toString());

                adt.notifyDataSetChanged();
            }
        });
    }
    public void update(){
        items = mySQLConnect.getData();
        adt = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);
        dataView.setAdapter(adt);
    }

    public void init() {
        txtID = (EditText)findViewById(R.id.txtID);
        txtName = (EditText)findViewById(R.id.txtName);
        txtTel = (EditText)findViewById(R.id.txtTel);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        btnSave = (Button)findViewById(R.id.btnSave);
        dataView = (ListView)findViewById(R.id.dataView);
        mySQLConnect = new MySQLConnect(MainActivity.this);
    }
}
