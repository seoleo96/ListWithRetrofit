package com.seoleo96.listwithretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PostsBaseAdapter extends BaseAdapter {
    List<Posts> postsList;
    Context context;

    public PostsBaseAdapter( Context context, List<Posts> post){
        postsList = post;
        this.context = context;
    }

    @Override
    public int getCount() {
        return postsList.size();
    }

    @Override
    public Object getItem(int position) {
        return postsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return postsList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.row_users, null);

            TextView id = convertView.findViewById(R.id.usernames_number);
            TextView title = convertView.findViewById(R.id.username);

            id.setText(Integer.toString(postsList.get(position).getId()));
            title.setText(postsList.get(position).getTitle());


        }

        return convertView;
    }
}
