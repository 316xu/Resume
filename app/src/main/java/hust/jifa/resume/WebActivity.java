package hust.jifa.resume;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by jfxu on 16/10/2.
 */
public class WebActivity extends Activity {

    private WebView mWebview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        mWebview = (WebView) findViewById(R.id.web);
        mWebview.loadUrl(getIntent().getExtras().getString(IntentConstant.URL_EXTRA));
    }
}
