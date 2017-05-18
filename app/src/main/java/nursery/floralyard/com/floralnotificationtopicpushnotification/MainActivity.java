package nursery.floralyard.com.floralnotificationtopicpushnotification;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText mGlobalTitle;
    private EditText mGLobalMessage;
    private EditText mGlobalUrl;
    private Button mPushButton;
    private CheckBox mGlobalCheckBox;
    private WebView webView;
    private static final String TAG = "MainActivity.this";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview);

        webView.getSettings().setJavaScriptEnabled(true);
        mGlobalTitle = (EditText) findViewById(R.id.global_title);
        mGlobalCheckBox = (CheckBox) findViewById(R.id.global_checkbox);
        mGlobalUrl = (EditText) findViewById(R.id.global_image_url);
        mGLobalMessage = (EditText) findViewById(R.id.global_message);
        mPushButton = (Button) findViewById(R.id.global_push);
        progressDialog = new ProgressDialog(this);
        mPushButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mGlobalTitle.getText().toString().trim()) || TextUtils.isEmpty(mGLobalMessage.getText().toString().trim()) || TextUtils.isEmpty(mGlobalUrl.getText().toString().trim())) {
                    Toast.makeText(MainActivity.this, "Fields are Empty", Toast.LENGTH_LONG).show();
                } else {
                    if (mGlobalCheckBox.isChecked()) {
                        URL url = NetworkUtils.makeGlobalImageUrl(mGlobalTitle.getText().toString().trim(), mGLobalMessage.getText().toString().trim(), mGlobalUrl.getText().toString().trim());
                        Log.i(TAG, url.toString());
                        progressDialog.setMessage("Sending...");
                        //  makerequest(url);
                        webView.setWebViewClient(new WebViewClient() {
                            @Override
                            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                super.onPageStarted(view, url, favicon);
                                progressDialog.show();
                            }

                            @Override
                            public void onPageFinished(WebView view, String url) {
                                super.onPageFinished(view, url);
                                progressDialog.hide();
                            }
                        });
                        webView.loadUrl(url.toString());
                    } else {
                        Toast.makeText(getApplicationContext(), "Please Check to Continue", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    public void makerequest(URL Url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String title = "Hello World";
        String url = "http://codeham.com/floralyard/firebase/?title=" + title + "&message=GudNightFriends&url_field=http://codeham.com/floralyard/notification/push1.jpg&include_image=on&push_type=topic";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.i(TAG, response.substring(0, 500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, ("That didn't work!"));
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


}
