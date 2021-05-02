package com.conaldes.scomputations;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SCAppWebViewClient extends WebViewClient {

    private Activity activity = null;

    public SCAppWebViewClient(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //if(Uri.parse(url).getHost().endsWith("html5rocks.com")) {
        //    return false;
        //}

        if(Uri.parse(url).getHost().length() == 0) {
            return false;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);
        return true;
    }
}