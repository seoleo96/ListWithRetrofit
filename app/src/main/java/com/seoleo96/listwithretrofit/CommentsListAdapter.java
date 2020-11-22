package com.seoleo96.listwithretrofit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CommentsListAdapter extends BaseAdapter {

    List<Comments> commentsList;
    Context context;

    public CommentsListAdapter(List<Comments> comments, Context context) {
        commentsList = comments;
        this.context = context;
    }

    @Override
    public int getCount() {
        return commentsList.size();
    }

    @Override
    public Object getItem(int position) {
        return commentsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return commentsList.get(position).getId();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.row_users, null);
            TextView id = convertView.findViewById(R.id.usernames_number);
            TextView email = convertView.findViewById(R.id.username);
            id.setText(Integer.toString(commentsList.get(position).getId()));
            email.setText(commentsList.get(position).getEmail());
        }
        return convertView;
    }
}
