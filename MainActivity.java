package com.example.kingj.github;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SearchEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText search;
    User user;
    ImageView imageView;
    ProgressBar progressBar;
    Button searchButton,followers,repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView=findViewById(R.id.imageView);
        progressBar=findViewById(R.id.progresBar);
        search =findViewById(R.id.edit);
        searchButton = findViewById(R.id.search);
        followers=findViewById(R.id.followers);
        repository=findViewById(R.id.repository);

        searchButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        progressBar.setVisibility(View.VISIBLE);
        String userName = search.getText().toString();

        Retrofit.Builder builder = new Retrofit.Builder()
                                    .baseUrl("https://api.github.com/users/")
                                    .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        UserService service = retrofit.create(UserService.class);

        Call<User> call = service.getRespository(userName);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

               user.avatar_url=response.body().getAvatar_url();
                String imageUrl = user.getAvatar_url();

                Picasso.with(MainActivity.this).load(imageUrl).resize(5,5).into(imageView);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        progressBar.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);
    }

    }
