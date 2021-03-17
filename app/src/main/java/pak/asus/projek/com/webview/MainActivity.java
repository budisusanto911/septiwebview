package pak.asus.projek.com.webview;

import android.annotation.TargetApi;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    WebView webView = (WebView)findViewById(R.id.webview);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.getSettings().setLoadWithOverviewMode(true);
    webView.getSettings().setUseWideViewPort(true);
    webView.setWebViewClient(new WebViewClient(){

      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {

        view.loadUrl(url);

        return true;
      }
      @Override
      public void onPageFinished(WebView view, final String url) {

      }
    });

    webView.loadUrl("https://ebook.balitbangham.go.id/file/350da73ceb8ca5dfce0b9392a9afd242.html");

  }

}