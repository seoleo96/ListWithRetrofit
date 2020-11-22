package com.seoleo96.listwithretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private final static String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private List<Users> usersArrayList = new ArrayList<>();
    UsersListAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listview = findViewById(R.id.listview);

//        listview.setAdapter((ListAdapter) adapter);

        Retrofit retofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retofit.create(ApiInterface.class);
        Call<List<Users>> call = apiInterface.getUsers();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                if (!response.isSuccessful()){
                    Log.d("MainActivity", response.message());
                    return;
                }
                usersArrayList = response.body();
                adapter = new UsersListAdapter(getApplicationContext(), R.layout.row_users, usersArrayList);
                listview.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Log.d("MainActivity", t.getMessage());
            }
        });


    }
}
