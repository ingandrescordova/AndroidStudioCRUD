package com.biodata.sh.biodata;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        final EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterLink);

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String username = editTextUsername.getText().toString().trim();
                final String password = editTextPassword.getText().toString().trim();

//                Toast.makeText(getApplicationContext(), username, Toast.LENGTH_LONG).show();
//                Toast.makeText(getApplicationContext(), password, Toast.LENGTH_LONG).show();
//                final HashMap<String, String> params = new HashMap<String, String>();
//
//                params.put(ConfigLogin.TAG_USERNAME, username);
//                params.put(ConfigLogin.TAG_PASSWORD, password);


                StringRequest stringRequest = new StringRequest(Request.Method.POST, ConfigLogin.URL_LOGIN,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

//                                if (response.toString() == "success") {
                                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(i);
//                                }else{
//                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                                    builder.setMessage("Login Failed")
//                                        .setNegativeButton("Retry", null)
//                                        .create()
//                                        .show();
//
//                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams()  {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put(ConfigLogin.TAG_USERNAME, username);
                        params.put(ConfigLogin.TAG_PASSWORD, password);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(getApplication());
                requestQueue.add(stringRequest);


//                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ConfigLogin.URL_LOGIN, new JSONObject(params),
//                        new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
//                            }
//                        });
//
//                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//                requestQueue.add(jsonObjectRequest);
//
//
//                Response.Listener<String> responseListener = new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//                            JSONObject jsonResponse = new JSONObject(response);
//                            boolean success = jsonResponse.getBoolean("success");
//
//                            if (success) {
//
//                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                intent.putExtra("username", username);
//                                LoginActivity.this.startActivity(intent);
//
//                            } else {
//                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                                builder.setMessage("Login Failed")
//                                        .setNegativeButton("Retry", null)
//                                        .create()
//                                        .show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                };
//                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
//                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
//                queue.add(loginRequest);
            }

        });


    }
}