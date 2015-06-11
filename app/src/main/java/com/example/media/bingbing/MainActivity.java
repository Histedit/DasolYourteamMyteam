package com.example.media.bingbing;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.support.v7.app.ActionBarActivity;
import android.os.*;
import android.os.StrictMode.ThreadPolicy;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import android.media.MediaPlayer;
import android.net.Uri;
import android.graphics.*;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;


public class MainActivity extends ActionBarActivity {

    Button btLeft;
    Button btRight;
    Button btStart;
    TextView tvResult;
    TextView tvFinish;
    ImageView iv;
    int tries;
    int sum;
    int score;
    int buttonNum;


    //String text;

    // 앱이 처음 시작할 때 실행되는 부분
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        iv = (ImageView)findViewById(R.id.imageView);
        btStart = (Button)findViewById(R.id.button3);
        btStart.setOnClickListener(new onStart());
        btRight = (Button)findViewById(R.id.button2);
        btRight.setOnClickListener(new onRight());
        btLeft = (Button)findViewById(R.id.button);
        btLeft.setOnClickListener(new onLeft());
        tvResult = (TextView)findViewById(R.id.textView);

        tvResult.setText("두 수 사이의 소수");
    }

    public class onStart implements OnClickListener {
        public void onClick(View v) {
            Random rand = new Random();
            int randomnum = rand.nextInt(2);
            tries += 1;
            tvResult.setText(String.valueOf(tries));

            if(randomnum == 0){
                iv.setImageResource(R.drawable.yellow);
                sum=0;
            }
            if(randomnum == 1){
                iv.setImageResource(R.drawable.purple);
                sum=1;
            }


        }
    }

    public class onRight implements OnClickListener {
        public void onClick(View v) {
            Random rand = new Random();
            int randomnum = rand.nextInt(2);
                if (sum==1){
                tries += 1;
                tvResult.setText(String.valueOf(tries));

                    if(randomnum == 0){
                        iv.setImageResource(R.drawable.yellow);
                        sum=0;
                    }
                    if(randomnum == 1){
                        iv.setImageResource(R.drawable.purple);
                        sum=1;
                    }


                }
            else {
                tvResult.setText("게임오버");

            }

        }
    }
        public class onLeft implements OnClickListener {
            public void onClick(View v) {
                Random rand = new Random();
                int randomnum = rand.nextInt(2);
                if (sum==0){
                    tries += 1;
                    tvResult.setText(String.valueOf(tries));

                    if(randomnum == 0){
                        iv.setImageResource(R.drawable.yellow);
                        sum=0;
                    }
                    if(randomnum == 1){
                        iv.setImageResource(R.drawable.purple);
                        sum=1;
                    }
                }
                else {
                    tvResult.setText("게임오버");

                }



            }
        }









    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

