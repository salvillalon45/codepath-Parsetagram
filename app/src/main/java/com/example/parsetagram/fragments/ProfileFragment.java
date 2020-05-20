package com.example.parsetagram.fragments;

import android.util.Log;

import com.example.parsetagram.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment {

    public static final String TAG = "ProfileFragment";

    @Override
    protected void queryPosts() {
        // Specify which class to query
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.include(Post.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        // Specify the object id
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                Log.i(TAG, "In done");
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts ", e);
                    return;
                }
                else {
                    for (Post post : posts) {
                        Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                    }
                    allPosts.addAll(posts);
                    // ALWWAYS NOTIFY THE ADAPTER OF DATA CHANGE
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

}
