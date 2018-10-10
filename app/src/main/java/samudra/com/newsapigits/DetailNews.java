package samudra.com.newsapigits;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static samudra.com.newsapigits.MainActivity.NEWS_URL;

public class DetailNews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        Intent i = getIntent();
        String Url = i.getStringExtra(NEWS_URL);
        WebView web = (WebView)findViewById(R.id.webview);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl(Url);
    }
}
