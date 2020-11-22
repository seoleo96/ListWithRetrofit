package com.seoleo96.listwithretrofit;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class UsersListAdapter extends BaseAdapter {

    List<Users>usersList;
    Context context;

    public UsersListAdapter(Context context, int resource, List<Users> usersList) {
        this.usersList=usersList;
        this.context=context;
    }

    @Override
    public int getCount() {
        return usersList.size();
    }

    @Override
    public Object getItem(int position) {
        return usersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return usersList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.row_users, null);


            TextView usernameId = (TextView) convertView.findViewById(R.id.usernames_number);
            TextView username = (TextView) convertView.findViewById(R.id.username);
            usernameId.setText( Integer.toString(usersList.get(position).getId()));
            username.setText( usersList.get(position).getUsername());


        }
        return convertView;
    }


}
