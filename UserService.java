package com.example.kingj.github;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface UserService {


    @GET("users/{user}")
    Call<User> getRespository(@Path("user")String user);

}
