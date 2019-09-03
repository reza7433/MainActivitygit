package com.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.Retrofit.ApiClient;
import com.example.Retrofit.ApiService;
import com.example.StudentsViewHolder.BaseNewsModels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ServerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<NewsModel> newsModelList;
    FrameLayout frameLayout;
    private String url = "https://newsapi.org/v2/everything?q=bitcoin&from=2019-07-31&sortBy=publishedAt&apiKey=1e59d881846c4dc9942e906e4ff0b4ca";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);


        // getLastNews();
        // StringRequestSample();
        getLastNewsRetrofit();


        recyclerView = (RecyclerView) findViewById(R.id.rv_server_lastNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        frameLayout = (FrameLayout) findViewById(R.id.frame_server_waiting);
        newsModelList = new ArrayList<>();

/*

        SampleGenericClass<Integer> sampleGenericClass = new SampleGenericClass<>();
        sampleGenericClass.add(new Integer(10));
        Toast.makeText(this, sampleGenericClass.get()+"", Toast.LENGTH_SHORT).show();
*/


    }


    private void getLastNewsRetrofit() {
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<BaseNewsModels> call = service.getBaseNewsModel("bitcoin", "2019-07-29", "publishedAt", "1e59d881846c4dc9942e906e4ff0b4ca");
        call.enqueue(new Callback<BaseNewsModels>() {
            @Override
            public void onResponse(Call<BaseNewsModels> call, retrofit2.Response<BaseNewsModels> response) {
                BaseNewsModels baseNewsModel = response.body();
                List<ArticleModel> articleModels = baseNewsModel.getArticleModels();
                for (int i = 0; i < articleModels.size(); i++) {
                    ArticleModel articleModel = articleModels.get(i);
                    NewsModel newsModel = new NewsModel();
                    newsModel.setTitle(articleModel.getTitle());
                    newsModel.setSource(articleModel.getSourceModel().getName());
                    newsModel.setUrl(articleModel.getUrl());
                    newsModel.setImageUrl(articleModel.getUrlToImage());
                    newsModel.setDate(articleModel.getPublishedAt());

                    newsModelList.add(newsModel);

                }
                frameLayout.setVisibility(View.GONE);
                recyclerView.setAdapter(new NewsAdapter(ServerActivity.this, newsModelList));


            }

            @Override
            public void onFailure(Call<BaseNewsModels> call, Throwable t) {

            }
        });
    }

    private void getLastNews() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray articles = response.getJSONArray("articles");
                    for (int i = 0; i < articles.length(); i++) {
                        JSONObject article = articles.getJSONObject(i);
                        JSONObject sourceObject = article.getJSONObject("source");
                        String source = sourceObject.getString("name");
                        String title = article.getString("title");
                        String imageUrl = article.getString("urlToImage");
                        String date = article.getString("publishedAt");
                        String url = article.getString("url");
                        NewsModel newsModel = new NewsModel();
                        newsModel.setTitle(title);
                        newsModel.setSource(source);
                        newsModel.setDate(date);
                        newsModel.setImageUrl(imageUrl);
                        newsModel.setUrl(url);

                        newsModelList.add(newsModel);


                    }
                    frameLayout.setVisibility(View.GONE);
                    recyclerView.setAdapter(new NewsAdapter(ServerActivity.this, newsModelList));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(15000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

    }

    private void StringRequestSample() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }

}