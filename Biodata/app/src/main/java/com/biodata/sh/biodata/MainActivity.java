package com.biodata.sh.biodata;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText editTextNama;
    private EditText editTextEmail;
    private EditText editTextAlamat;

    private Button buttonAdd;
    private Button buttonView;

//    Intent intent = getIntent();
//    String username = intent.getStringExtra("username");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextAlamat = (EditText) findViewById(R.id.editTextAlamat);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }

    private void addB(){

        final String nama = editTextNama.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String alamat = editTextAlamat.getText().toString().trim();

        class AddB extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Adding...","Wait...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this,s, Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Config.KEY_NAMA,nama);
                params.put(Config.KEY_EMAIL,email);
                params.put(Config.KEY_ALAMAT,alamat);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }
        AddB ae = new AddB();
        ae.execute();


    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            addB();
        }

        if(v == buttonView){
            startActivity(new Intent(this,ViewAllActivity.class));
        }
    }



}
