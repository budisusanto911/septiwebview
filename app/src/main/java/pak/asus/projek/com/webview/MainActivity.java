package pak.asus.projek.com.webview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, Handler.Callback {
  WebView webView = null;
  private static final int CLICK_ON_WEBVIEW = 1;
  private static final int CLICK_ON_URL = 2;

  private final Handler handler = new Handler(this);
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Button idButton = (Button)findViewById(R.id.idButton);
    idButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
    webView = (WebView)findViewById(R.id.webview);
    webView.getSettings().setAllowContentAccess(true);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.getSettings().setLoadWithOverviewMode(true);
    webView.getSettings().setUseWideViewPort(true);
    webView.addJavascriptInterface(new WebAppInterface(this), "Android");
    webView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
    webView.setWebViewClient(new WebViewClient(){

      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {

        Log.i("TAG", "shouldOverrideUrlLoading: ");
        view.loadUrl(url);

        return true;
      }
      @Override
      public void onPageFinished(WebView view, final String url) {

      }
      @Override
      public void onLoadResource(WebView view, String url) {
        Log.d("URL","Load Resource"+ url);
        super.onLoadResource(view, url);
      }
    });

    webView.loadUrl("https://ebook.balitbangham.go.id/file/e4b2b0bc032678142b728c1780e7db92.html");


  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
      this.webView.goBack();
      return true;
    }

    return super.onKeyDown(keyCode, event);
  }

  @Override
  public boolean onTouch(View v, MotionEvent event) {
    if (v.getId() == R.id.webview && event.getAction() == MotionEvent.ACTION_DOWN){
      handler.sendEmptyMessageDelayed(CLICK_ON_WEBVIEW, 500);
    }
    return false;
  }

  @Override
  public boolean handleMessage(Message msg) {
    if (msg.what == CLICK_ON_URL){
      handler.removeMessages(CLICK_ON_WEBVIEW);
      return true;
    }
    if (msg.what == CLICK_ON_WEBVIEW){
      Toast.makeText(this, "WebView clicked", Toast.LENGTH_SHORT).show();
      return true;
    }
    return false;
  }

}
