package com.conaldes.scomputations;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private static final String URL = "file:///android_asset/www/index.html";
    //private static final String gmapURL = "file:///android_asset/www/googlemap.html";
    WebView webView;
    private JSJAVADataExchange jsInterface = new JSJAVADataExchange();
    private final Messenger mActivityMessenger = new Messenger(new ActivityHandler(this));
    Messenger mServiceMessenger = null;
    boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);

        WebAction();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to CommunicationService
        Intent intent = new Intent(this, CommunicationService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sendMsgToService("persist-data");
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    public void WebAction() {
        webView = (WebView) findViewById(R.id.activity_main);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(jsInterface, "JAVJS_DATA_EXCHANGE");
        webView.getSettings().setAppCacheEnabled(true);
        webView.loadUrl(URL);
        //webView.loadUrl(gmapURL);

        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                webView.loadUrl("file:///android_asset/www/error.html");
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

     /**
     * Defines callbacks for service binding, passed to bindService()
     */
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            Toast.makeText(MainActivity.this, "Service connected!", Toast.LENGTH_SHORT).show();
            // where mServiceMessenger is used to send messages to Service
            // service is the binder returned from onBind method in the Service
            mServiceMessenger = new Messenger(service);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mServiceMessenger = null;
            mBound = false;
        }
    };

    class ActivityHandler extends Handler {
        private final WeakReference<MainActivity> mActivity;

        public ActivityHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msgFromService) {
            switch (msgFromService.what) {
                case CommunicationService.MSG_STRING:
                    String serviceResponse = msgFromService.getData().getString("MSG_STRING");
                    if(serviceResponse.contains("successful") || serviceResponse.contains("records out of")){
                        Toast.makeText(MainActivity.this, serviceResponse, Toast.LENGTH_LONG).show();
                    }else{
                        webView.loadUrl("javascript:buildTable(\""+serviceResponse+"\")");
                    }
                    //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    //    webView.evaluateJavascript("javascript:showJavaMsg(\""+compStatusData+"\");", null);
                    //}
                    //webView.evaluateJavascript("javascript:updateFromAndroid(\"" + text + "\")",null);
                    /*
                    webView.evaluateJavascript("javascript:myTestFunction();", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                            // Do what you want with the return value
                        }
                    });
                     */
                    //webView.loadUrl("javascript:showJavaMsg(\""+compStatusData+"\")");
                    break;
                //default:
                //    responseData = "No response!";
            }
        }
    }

    /*
    public <T extends Object> void checkType(T object) {
        if (object instanceof Integer)
            System.out.println("Integer ");
        else if(object instanceof Double)
            System.out.println("Double ");
        else if(object instanceof Float)
            System.out.println("Float : ");
        else if(object instanceof List)
            System.out.println("List! ");
        else if(object instanceof Set)
            System.out.println("Set! ");
    }

    I created a nice wrapper to call JavaScript methods; it also shows JavaScript errors in log:

    private void callJavaScript(String methodName, Object...params){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("javascript:try{");
        stringBuilder.append(methodName);
        stringBuilder.append("(");
        for (int i = 0; i < params.length; i++) {
            Object param = params[i];
            if(param instanceof String){
                stringBuilder.append("'");
                stringBuilder.append(param.toString().replace("'", "\\'"));
                stringBuilder.append("'");
            }
            if(i < params.length - 1){
                stringBuilder.append(",");
            }
        }
        stringBuilder.append(")}catch(error){Android.onError(error.message);}");
        webView.loadUrl(stringBuilder.toString());
    }
    */

    private void sendMsgToService(String jsData){
        if (mBound) {
            //Message message = Message.obtain();
            Message message = Message.obtain(null, CommunicationService.MSG_STRING, 0, 0);
            message.replyTo = mActivityMessenger;
            Bundle bundle = new Bundle();
            bundle.putString("MSG_STRING", jsData);
            message.setData(bundle);
            try {
                mServiceMessenger.send(message);
            } catch (RemoteException rex) {
                Toast.makeText(MainActivity.this, rex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        //public ArrayList<String> getStringArrayList (String key)
        //putStringArrayList(String key, ArrayList<String> value)
        //Serializable getSerializable(String key)
        //public void putSerializable (String key, Serializable value)
    }

    private class JSJAVADataExchange {
        @android.webkit.JavascriptInterface
        public void randomNos(String nobags) {
            if (!nobags.isEmpty()) {
                sendMsgToService(nobags);
            }
        }

        @android.webkit.JavascriptInterface
        public void relhumdEmc(String cnwetdryb) {
            if (!cnwetdryb.isEmpty()) {
                sendMsgToService(cnwetdryb);
            }
        }

        @android.webkit.JavascriptInterface
        public void selectFans(String fanspara) {
            if (!fanspara.isEmpty()) {
                sendMsgToService(fanspara);
            }
        }

        @android.webkit.JavascriptInterface
        public void pestsDoses(String dosepara) {
            if (!dosepara.isEmpty()) {
                sendMsgToService(dosepara);
            }
        }

        @android.webkit.JavascriptInterface
        public void searchResults(String searchpara) {
            if (!searchpara.isEmpty()) {
                sendMsgToService(searchpara);
            }
        }

        private String a1dToJson(String[] data) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < data.length; i++) {
                String str = data[i];
                if (i > 0)
                    sb.append(",");
                sb.append(str);
            }
            sb.append("]");
            return sb.toString();
        }
    }
}