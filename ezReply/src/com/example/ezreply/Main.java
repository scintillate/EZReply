package com.example.ezreply;

import java.io.DataOutputStream;
import java.net.Socket;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;
//import android.telephony.SmsManager;
import android.database.DatabaseUtils;

public class Main extends Activity {
	private TextView message = null;
	//private SmsManager sms = SmsManager.getDefault();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        LinearLayout lView = new LinearLayout(this);
        
        //String SMSs = getAllSMS();
        testSocket();
        
        message = new TextView(this);
        message.setText("hi");

        lView.addView(message);

        setContentView(lView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private String getSMS(int id){
    	ContentResolver contentResolver = getContentResolver();
    	 String selection = "_id="+id;
    	    Uri uri = Uri.parse("content://sms");
    	    Cursor cursor = contentResolver.query(uri, null, selection, null, null);
    	    //String phone = cursor.getString(cursor.getColumnIndex("address"));
    	    //String thread = cursor.getString(cursor.getColumnIndex("thread_id"));
    	    //int type = cursor.getInt(cursor.getColumnIndex("type"));// 2 = sent, etc.
    	    //String date = cursor.getString(cursor.getColumnIndex("date"));
    	    //String body = cursor.getString(cursor.getColumnIndex("body"));
    	    DatabaseUtils.dumpCursor(cursor);
    	    return "body";
    }
    
    private String getAllSMS(){
    	  ContentResolver contentResolver = getContentResolver();
    	  Uri uri = Uri.parse("content://sms");
    	  Cursor query = contentResolver.query(uri, null, null, null, null);
    	  String result = DatabaseUtils.dumpCursorToString(query);
    	  query.close();
    	  return result;
    }
    
    private Boolean testSocket(){
    	new Thread(new Runnable()
    	{
    		public void run() {
		    	try
		    	{
			    	Socket socket = new Socket("192.168.1.102",27015);
			    	DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
			    	DOS.writeUTF(" 漢語 HELLO WORLD");
			    	socket.close();
		    	}
		    	catch (Exception e) {
		    		System.out.println("system error: " + e);
		    		}
    		}
    	}).start();
    	return true;
    }
}

