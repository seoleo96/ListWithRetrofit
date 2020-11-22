package com.seoleo96.listwithretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

public interface ApiInterface {
    @GET("users")
    Call<List<Users>> getUsers();

    @GET("posts/{id}/comments")
    Call<List<Comments>> getComments(@Path("id") int userId);

    @GET("posts")
    Call<List<Posts>> getUsersPosts(@Query("userId") int userId );



}
