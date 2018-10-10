package samudra.com.newsapigits;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity implements NewsAdapter.OnItemClickListener,ImagesSlider.OnItemClickListener   {
    private RecyclerView mRecyclerView , recyclerView;
    private  NewsAdapter mNewsAdapter;
    private ImagesSlider mImageSlider;
    private ArrayList<NewsItem> mNewsItems;
    private RequestQueue mRequestQueue;
    public static final String NEWS_URL="news";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView = findViewById(R.id.vertical);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        mNewsItems = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        handleSSLHandshake();
        parseJSON();

    }
    private void parseJSON() {
        String url = "https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=1b7736686b974c9eb308993b5aa7e6ae";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("articles");
                    for(int i = 0; i<jsonArray.length(); i++){
                        JSONObject resul = jsonArray.getJSONObject(i);
                        String judul = resul.getString("title");
                        String url = resul.getString("url");
                        String image = resul.getString("urlToImage");
                        String author = resul.getString("author");
                        String publish = resul.getString("publishedAt");
                        NewsItem tambah = new NewsItem(image,judul,url,author,publish);
                        mNewsItems.add(tambah);
                    }
                    mNewsAdapter = new NewsAdapter(MainActivity.this,mNewsItems);
                    mImageSlider = new ImagesSlider(MainActivity.this,mNewsItems);
                    mRecyclerView.setAdapter(mNewsAdapter);
                    recyclerView.setAdapter(mImageSlider);
                    mNewsAdapter.setOnItemClickListener(MainActivity.this);
                    mImageSlider.setOnItemClickListener(MainActivity.this);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } ,new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }
    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }
    @Override
    public void onItemClick(int position) {
        Intent news = new Intent(this,DetailNews.class);
        NewsItem clickedItem = mNewsItems.get(position);

        news.putExtra(NEWS_URL,clickedItem.getUrl());
        startActivity(news);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}
