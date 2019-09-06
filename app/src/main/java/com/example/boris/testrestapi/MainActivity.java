package com.example.boris.testrestapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boris.testrestapi.api.Api;
import com.example.boris.testrestapi.api.Client;
import com.example.boris.testrestapi.controller.MyRecyclerAdapter;
import com.example.boris.testrestapi.models.Item;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Item likes, reposts, comments, mentions;
    private Api api;
    private RecyclerView recyclerLikes, recyclerComments, recyclerReposts, recyclerMentions;
    private TextView likesCount, commentsCount, repostsCount, menonsCount;
    private int loadCount = 0;
    private String id = "801";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDefaults();


    }

    private void setRecycler() {
        recyclerLikes.setAdapter(new MyRecyclerAdapter(likes, this));
        likesCount.setText(new StringBuilder("Лайки ").append(likes.getData().size()));

        recyclerComments.setAdapter(new MyRecyclerAdapter(comments, this));
        commentsCount.setText(new StringBuilder("Комментаторы ").append(comments.getData().size()));

        recyclerReposts.setAdapter(new MyRecyclerAdapter(reposts, this));
        repostsCount.setText(new StringBuilder("Репосты ").append(reposts.getData().size()));

        recyclerMentions.setAdapter(new MyRecyclerAdapter(mentions, this));
        menonsCount.setText(new StringBuilder("Отметки ").append(mentions.getData().size()));

        loadCount = 0;
    }

    private void setDefaults() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        api = Client.getClient().create(Api.class);

        loadLikes(id);
        loadReposters(id);
        loadCommentors(id);
        loadMentions(id);

        likesCount = findViewById(R.id.likesMain);
        commentsCount = findViewById(R.id.commentsMain);
        repostsCount = findViewById(R.id.repostsMain);
        menonsCount = findViewById(R.id.mentionsMain);

        recyclerLikes = findViewById(R.id.recyclerLikes);
        recyclerComments = findViewById(R.id.recyclerComments);
        recyclerReposts = findViewById(R.id.recyclerReposts);
        recyclerMentions = findViewById(R.id.recyclerMentions);

        recyclerLikes.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerComments.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerReposts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerMentions.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void loadLikes(String id) {
        progressDialog.show();
        try {
            Call<Item> call = api.getLikes(id);

            call.enqueue(new Callback<Item>() {
                @Override
                public void onResponse(Call<Item> call, Response<Item> response) {
                    if (!response.isSuccessful()) {
                        makeErrorNotification("code: " + response.code());
                        return;
                    }
                    if (response.body() != null) {
                        likes = response.body();
                        if (++loadCount == 4)
                            setRecycler();
                    } else
                        makeErrorNotification("response.body() == null");
                }

                @Override
                public void onFailure(Call<Item> call, Throwable t) {
                    makeErrorNotification("Error: " + t.toString());
                }
            });
        } catch (Exception e) {
            makeErrorNotification(e.toString());
        }
        progressDialog.dismiss();
    }

    private void loadReposters(String id) {
        progressDialog.show();
        try {
            Call<Item> call = api.getReposts(id);

            call.enqueue(new Callback<Item>() {
                @Override
                public void onResponse(Call<Item> call, Response<Item> response) {
                    if (!response.isSuccessful()) {
                        makeErrorNotification("code: " + response.code());
                        return;
                    }
                    if (response.body() != null) {
                        reposts = response.body();
                        if (++loadCount == 4)
                            setRecycler();
                    } else
                        makeErrorNotification("response.body() == null");
                }

                @Override
                public void onFailure(Call<Item> call, Throwable t) {
                    makeErrorNotification("Error: " + t.toString());
                }
            });
        } catch (Exception e) {
            makeErrorNotification(e.toString());
        }
        progressDialog.dismiss();
    }

    private void loadCommentors(String id) {
        progressDialog.show();
        try {
            Call<Item> call = api.getComments(id);

            call.enqueue(new Callback<Item>() {
                @Override
                public void onResponse(Call<Item> call, Response<Item> response) {
                    if (!response.isSuccessful()) {
                        makeErrorNotification("code: " + response.code());
                        return;
                    }
                    if (response.body() != null) {
                        comments = response.body();
                        if (++loadCount == 4)
                            setRecycler();
                    } else
                        makeErrorNotification("response.body() == null");
                }

                @Override
                public void onFailure(Call<Item> call, Throwable t) {
                    makeErrorNotification("Error: " + t.toString());
                }
            });
        } catch (Exception e) {
            makeErrorNotification(e.toString());
        }
        progressDialog.dismiss();
    }

    private void loadMentions(String id) {
        progressDialog.show();
        try {
            Call<Item> call = api.getIMentions(id);

            call.enqueue(new Callback<Item>() {
                @Override
                public void onResponse(Call<Item> call, Response<Item> response) {
                    if (!response.isSuccessful()) {
                        makeErrorNotification("code: " + response.code());
                        return;
                    }
                    if (response.body() != null) {
                        mentions = response.body();
                        if (++loadCount == 4)
                            setRecycler();
                    } else
                        makeErrorNotification("response.body() == null");
                }

                @Override
                public void onFailure(Call<Item> call, Throwable t) {
                    makeErrorNotification("Error: " + t.toString());
                }
            });
        } catch (Exception e) {
            makeErrorNotification(e.toString());
        }
        progressDialog.dismiss();
    }
    
    public void makeErrorNotification(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
        System.out.println(str);
    }

}
