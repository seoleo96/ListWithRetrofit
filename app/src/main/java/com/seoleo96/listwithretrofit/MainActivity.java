package com.seoleo96.listwithretrofit;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private final static String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private List<Comments> commentsArrayList = new ArrayList<>();
    private List<Users> usersArrayList = new ArrayList<>();
    private List<Posts> postsArrayList = new ArrayList<>();
    UsersListAdapter adapter;
    CommentsListAdapter adapterComments;
    PostsBaseAdapter postsBaseAdapter;
    ApiInterface apiInterface;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        listview = findViewById(R.id.listview);
        apiInterface = retofit.create(ApiInterface.class);
//        getUsers();
//        getComments();
        getPosts();
    }

    private void getPosts() {
        Call<List<Posts>> call = apiInterface.getUsersPosts(10);
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if (!response.isSuccessful()) {
                    Log.d("MainActivity", response.message());
                    return;
                }
                postsArrayList = response.body();
                postsBaseAdapter = new PostsBaseAdapter(getApplicationContext(), postsArrayList);
                listview.setAdapter(postsBaseAdapter);

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Log.d("MainActivity", t.getMessage());
            }
        });
    }

    private void getComments() {

        Call<List<Comments>> call = apiInterface.getComments(10);
        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {

                if (!response.isSuccessful()) {
                    Log.d("MainActivity", response.message());
                    return;
                }

                commentsArrayList = response.body();
                adapterComments = new CommentsListAdapter(commentsArrayList, getApplicationContext());
                listview.setAdapter(adapterComments);

            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                Log.d("MainActivity", t.getMessage());
            }
        });
    }


    private void getUsers() {
        Call<List<Users>> call = apiInterface.getUsers();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if (!response.isSuccessful()) {
                    Log.d("MainActivity", response.message());
                    return;
                }
                usersArrayList = response.body();
                adapter = new UsersListAdapter(getApplicationContext(), usersArrayList);
                listview.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Log.d("MainActivity", t.getMessage());
            }
        });
    }
}
