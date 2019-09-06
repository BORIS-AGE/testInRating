package com.example.boris.testrestapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.boris.testrestapi.api.Api;
import com.example.boris.testrestapi.api.Client;
import com.example.boris.testrestapi.models.Item;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Item likes, reposts, comments, mentions;
    Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDefaults();


    }

    private void setDefaults(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        api = Client.getClient().create(Api.class);

        loadLikes("801");
        loadReposters("801");
        loadCommentors("801");
        loadMentions("801");
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
