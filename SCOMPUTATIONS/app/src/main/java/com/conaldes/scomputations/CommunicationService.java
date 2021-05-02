package com.conaldes.scomputations;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;
import androidx.annotation.RequiresApi;

import com.conaldes.scomputations.compdata.ResultDatabase;
import com.conaldes.scomputations.compdata.model.Result;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class CommunicationService extends Service {

    public static final int MSG_STRING = 1;
    Messenger replyMessanger;
    Messenger mMessenger;
    private SiloCom SILOCOM = null;
    private ResultDatabase resultDatabase;
    private boolean firstRequest = false;

    @Override
    public IBinder onBind(Intent intent) {
        //Toast.makeText(getApplicationContext(), "binding", Toast.LENGTH_LONG).show();
        SILOCOM = SiloCom.getInstance(getApplicationContext());
        resultDatabase = ResultDatabase.getInstance(getApplicationContext());
        mMessenger = new Messenger(new IncomingHandler(this));
        return mMessenger.getBinder();
    }

    class IncomingHandler extends Handler {

        private Context applicationContext;
        IncomingHandler(Context context) {
            applicationContext = context.getApplicationContext();
        }

        @Override
        public void handleMessage(Message msgFromClient) {
            switch (msgFromClient.what) {
                case MSG_STRING:
                    replyMessanger = msgFromClient.replyTo;
                    Bundle bundle = msgFromClient.getData();
                    String msgstr = bundle.getString("MSG_STRING");
                    //Toast.makeText(CommunicationService.this,"Request received: " + msgstr,Toast.LENGTH_SHORT).show();
                    //if(appExpired()) {
                        //new PerformTask(CommunicationService.this, msgstr).execute();
                    //}else{
                    //    Toast.makeText(CommunicationService.this,"App has expired" + msgstr,Toast.LENGTH_SHORT).show();
                    //}
                    new PerformTask(CommunicationService.this, msgstr).execute();
                    break;
                default:
                    super.handleMessage(msgFromClient);
            }
        }
    }

    private boolean appExpired() {
        // creating a Calendar object
        Calendar c = Calendar.getInstance();

        // set Month
        // MONTH starts with 0 i.e. ( 0 - Jan)
        c.set(Calendar.MONTH, 9);

        // set Date
        c.set(Calendar.DATE, 05);

        // set Year
        c.set(Calendar.YEAR, 2020);

        // creating a date object with specified time.
        Date dateOne = c.getTime();

        // creating a date of object
        // storing the current date
        Date currentDate = new Date();

        System.out.print("Is currentDate after date one : ");
        boolean status = false;
        // if currentDate is after dateOne
        if(currentDate.before(dateOne)){
            status = false;
        }else{
            status = true;
        }
        return status;
    }

    /*
    class LooperThread extends Thread {
        public Handler mHandler;

        public void run() {
            Looper.prepare();

            mHandler = new Handler() {
                public void handleMessage(Message msg) {
                    // process incoming messages here
                }
            };

            Looper.loop();
        }
    }
    */

    //@RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private void sendMsgToMainActivity(String msgToclient) {
        //Toast.makeText(NodeService.this,"(replyMessanger != null): " + (replyMessanger != null),Toast.LENGTH_SHORT).show();
        if (replyMessanger != null) {
            //Log.d("NodeService", "Cannot send message to activity - no activity registered to this service.");
            Bundle bundle = new Bundle();
            bundle.putString("MSG_STRING", msgToclient);
            Message replyMsg = Message.obtain(null, MSG_STRING);
            replyMsg.setData(bundle);
            try {
                replyMessanger.send(replyMsg);//replying / sending msg to activity
                //Toast.makeText(CommunicationService.this,msgToclient,Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                //e.printStackTrace();
                Toast.makeText(CommunicationService.this,"RemoteException: " + e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean deleteFileFromFileSystem(){
        File file = new File(CommunicationService.this.getFilesDir()+"/silofile/silocomp.txt");
        boolean filedeleted = false;
        if (file.exists()) {
            filedeleted = file.delete();
        }        
        return filedeleted;
    }

    private class PerformTask extends AsyncTask<Void, Void, String> {
        private WeakReference<CommunicationService> serviceReference;
        private String msgstr;

        // only retain a weak reference to the activity
        PerformTask(CommunicationService context, String msgstr) {
            serviceReference = new WeakReference<>(context);
            this.msgstr = msgstr;
        }

        // doInBackground methods runs on a worker thread
        @Override
        protected String doInBackground(Void... objs) {
            String msgToclient = "";
            if(!firstRequest){                
                firstRequest = true;
                boolean filedeleted = deleteFileFromFileSystem();
                List<Result> results = resultDatabase.getResultDao().getResults("randnos");
                List<String> tempList = new ArrayList<>();
                String content = "";
                for(Result result: results){
                    content = result.getContent();
                    tempList.add(content);
                }
                boolean randadded = SILOCOM.setRandNOSList(tempList);
                results = resultDatabase.getResultDao().getResults("relhemc");
                tempList = null;
                tempList = new ArrayList<>();
                content = "";
                for(Result result: results){
                    content = result.getContent();
                    tempList.add(content);
                }
                boolean relhadded = SILOCOM.setRelhEWCList(tempList);
                results = resultDatabase.getResultDao().getResults("fanssel");
                tempList = null;
                tempList = new ArrayList<>();
                content = "";
                for(Result result: results){
                    content = result.getContent();
                    tempList.add(content);
                }
                boolean fansadded = SILOCOM.setFansSELList(tempList);
                results = resultDatabase.getResultDao().getResults("pciduse");
                tempList = null;
                tempList = new ArrayList<>();
                content = "";
                for(Result result: results){
                    content = result.getContent();
                    tempList.add(content);
                }
                boolean pcidadded = SILOCOM.setPcidUSEList(tempList);
                boolean loadstatus = randadded && relhadded && fansadded && pcidadded;
                if(loadstatus)
                    msgToclient = "Data loaded successfully!";
                else
                    msgToclient = "All data not loaded successfully!";
                sendMsgToMainActivity(msgToclient);
                List<Result> allResults = resultDatabase.getResultDao().getResults();
                for(Result result: allResults){
                    resultDatabase.getResultDao().deleteResults(result);
                }
            }
            if(msgstr.contains("numbags")){
                msgToclient = SILOCOM.randomNos(msgstr);
                sendMsgToMainActivity(msgToclient);
            }else if(msgstr.contains("cropname")){
                msgToclient = SILOCOM.relHumdEqmc(msgstr);
                sendMsgToMainActivity(msgToclient);
            }else if(msgstr.contains("grainname")){
                msgToclient = SILOCOM.fansSelect(msgstr);
                sendMsgToMainActivity(msgToclient);
            }else if(msgstr.contains("structure")){
                msgToclient = SILOCOM.pesticideDoses(msgstr);
                sendMsgToMainActivity(msgToclient);
            }else if(msgstr.contains("result-random")){
                msgToclient = SILOCOM.randNOSjsonString();
                sendMsgToMainActivity(msgToclient);
            }else if(msgstr.contains("result-relativehumidity")){
                msgToclient = SILOCOM.relhEWCjsonString();
                sendMsgToMainActivity(msgToclient);
            }else if(msgstr.contains("result-fanselection")){
                msgToclient = SILOCOM.fansSELjsonString();
                sendMsgToMainActivity(msgToclient);
            }else if(msgstr.contains("result-pesticidedosages")){
                msgToclient = SILOCOM.pcidUSEjsonString();
                sendMsgToMainActivity(msgToclient);
            }else if(msgstr.contains("persist-data")){
                int no_success = 0;
                int no_records = 0;
                Set<String> randNOSSet = SILOCOM.getRandNOSSet();
                int randln = randNOSSet.size();
                for(String randnorec: randNOSSet){
                    Result result = new Result();
                    result.setContent(randnorec);
                    result.setType("randnos");
                    resultDatabase.getResultDao().insertResult(result);
                    no_success++;
                }
                Set<String> relhEWCSet = SILOCOM.getRelhEWCSet();
                int relhln = relhEWCSet.size();
                for(String relhemcrec: relhEWCSet){
                    Result result = new Result();
                    result.setContent(relhemcrec);
                    result.setType("relhemc");
                    resultDatabase.getResultDao().insertResult(result);
                    no_success++;
                }
                Set<String> fansSELSet = SILOCOM.getFansSELSet();
                int fansln = fansSELSet.size();
                for(String fansSELrec: fansSELSet){
                    Result result = new Result();
                    result.setContent(fansSELrec);
                    result.setType("fanssel");
                    resultDatabase.getResultDao().insertResult(result);
                    no_success++;
                }
                Set<String> pcidUSESet = SILOCOM.getPcidUSESet();
                int pcidln = pcidUSESet.size();
                for(String pciduserec: pcidUSESet){
                    Result result = new Result();
                    result.setContent(pciduserec);
                    result.setType("pciduse");
                    resultDatabase.getResultDao().insertResult(result);
                    no_success++;
                }
                no_records = randln + relhln + fansln + pcidln;
                msgToclient = Integer.toString(no_success) + " out of " + Integer.toString(no_records) + " saved successfully!";
            }
            return msgToclient;
        }

        // onPostExecute runs on main thread
        @Override
        protected void onPostExecute(String msgToclient) {
            sendMsgToMainActivity(msgToclient);
        }
    }
}