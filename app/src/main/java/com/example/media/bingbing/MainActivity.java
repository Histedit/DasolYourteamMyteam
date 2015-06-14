package com.example.media.bingbing;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    ImageView btLeft;
    ImageView btRight;
    ImageView btStart;
    TextView tvResult;
    TextView tvTimer;
    ImageView ivFront, ivSecond, ivThird, ivFourth, ivFifth, ivSixth, ivSeventh, ivLast;
    int nScore; // 점수
    int nAnswerLast, nAnswerFront, nAnswerSecond, nAnswerThird, nAnswerFourth, nAnswerFifth, nAnswerSixth, nAnswerSeventh; // 답 번호
    boolean isRunning = false;

    int White, Yellow, Red;

    Random rand = new Random();

    CountDownTimer timer = null; // 타이머 변수

    public void scoreChange(boolean updown){

        if(updown){
            nScore += 1;
            tvResult.setTextColor(Yellow);
        }
        else{
            if(nScore>0){
                nScore -= 1;
                tvResult.setTextColor(Red);
            }
        }

    }

    public void timerInit(){ // 타이머 불러오기

        if (timer != null) { // 타이머를 새로 불러왔을 때 초기화 하는 문장
            timer.cancel();
        }

        timer = new CountDownTimer(30500,1000) { // (카운트 다운할 시간, 간격)
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished/1000;
                //long milis = (millisUntilFinished - (millisUntilFinished/1000*1000))/100;
                tvTimer.setText(String.valueOf(seconds));
            } // 돌아갈때 동작

            @Override
            public void onFinish() {
                tvResult.setText(String.valueOf(nScore));
                isRunning = false;
                timer.cancel();
                tvTimer.setText("OVER");
                timer = null;
                btStart.setVisibility(View.VISIBLE);

            } // 끝났을때의 동작
        };
    }

    public void pushImage(){ // 이미지 밀어주기

        ivFront.setImageDrawable(ivSecond.getDrawable());
        nAnswerFront = nAnswerSecond;
        ivSecond.setImageDrawable(ivThird.getDrawable());
        nAnswerSecond = nAnswerThird;
        ivThird.setImageDrawable(ivFourth.getDrawable());
        nAnswerThird = nAnswerFourth;
        ivFourth.setImageDrawable(ivFifth.getDrawable());
        nAnswerFourth = nAnswerFifth;
        ivFifth.setImageDrawable(ivSixth.getDrawable());
        nAnswerFifth = nAnswerSixth;
        ivSixth.setImageDrawable(ivSeventh.getDrawable());
        nAnswerSixth = nAnswerSeventh;
        ivSeventh.setImageDrawable(ivLast.getDrawable());
        nAnswerSeventh = nAnswerLast;
        int temp = rand.nextInt(2);
        nAnswerLast = temp;
        set(ivLast,temp);


    }

    public void set(ImageView iv, int random){

        if(random == 0){
            iv.setImageResource(R.drawable.yellow);
        }
        else if(random == 1){
            iv.setImageResource(R.drawable.purple);
        }
    }

    public void shuffle(){
        int temp = rand.nextInt(2);
        set(ivFront,temp);
        nAnswerFront = temp;
        temp = rand.nextInt(2);
        set(ivSecond,temp);
        nAnswerSecond = temp;
        temp = rand.nextInt(2);
        set(ivThird,temp);
        nAnswerThird = temp;
        temp = rand.nextInt(2);
        set(ivFourth,temp);
        nAnswerFourth = temp;
        temp = rand.nextInt(2);
        set(ivFifth,temp);
        nAnswerFifth = temp;
        temp = rand.nextInt(2);
        set(ivSixth,temp);
        nAnswerSixth = temp;
        temp = rand.nextInt(2);
        set(ivSeventh,temp);
        nAnswerSeventh = temp;
        temp = rand.nextInt(2);
        set(ivLast,temp);
        nAnswerLast = temp;


    }

    // 앱이 처음 시작할 때 실행되는 부분
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/fun.ttf");

        White = getResources().getColor(R.color.color_white);
        Yellow = getResources().getColor(R.color.color_yellow);
        Red = getResources().getColor(R.color.color_red);

        ivFront = (ImageView)findViewById(R.id.ivFront);
        ivSecond = (ImageView)findViewById(R.id.ivSecond);
        ivThird = (ImageView)findViewById(R.id.ivThird);
        ivFourth = (ImageView)findViewById(R.id.ivFourth);
        ivFifth = (ImageView)findViewById(R.id.ivFifth);
        ivSixth = (ImageView)findViewById(R.id.ivSixth);
        ivSeventh = (ImageView)findViewById(R.id.ivSeventh);
        ivLast = (ImageView)findViewById(R.id.ivLast);
        btStart = (ImageView)findViewById(R.id.ivStart);
        btStart.setOnClickListener(new onStart());
        btRight = (ImageView)findViewById(R.id.ivRight);
        btRight.setOnClickListener(new onRight());
        btLeft = (ImageView)findViewById(R.id.ivLeft);
        btLeft.setOnClickListener(new onLeft());
        tvResult = (TextView)findViewById(R.id.textView);
        tvTimer = (TextView)findViewById(R.id.textView2);
        tvResult.setText("0");
        tvResult.setTypeface(custom_font);
        tvResult.setTextSize(27);
        tvResult.setTextColor(Yellow);
        tvTimer.setText("0");
        tvTimer.setTypeface(custom_font);
        tvTimer.setTextSize(27);
        tvTimer.setTextColor(White);
    }

    public class onStart implements OnClickListener { // 새게임시작
        public void onClick(View v) {

            btStart.setVisibility(View.INVISIBLE);
            isRunning = true;
            shuffle();
            nScore = 0;
            tvResult.setText(String.valueOf(nScore));
            timerInit();
            timer.start();

        }
    }

    public class onRight implements OnClickListener {
        public void onClick(View v) {

            if(isRunning){ // 게임중인지 게임중이 아닌지 검사

                int randomNum = rand.nextInt(2);
                if (nAnswerFront == 1){
                    scoreChange(true);
                    tvResult.setText(String.valueOf(nScore));
                    pushImage();
                }
                else {
                    scoreChange(false);
                    tvResult.setText(String.valueOf(nScore));
                    pushImage();
                }
            }
            else {

                Toast.makeText(getApplicationContext(),"Press Go!",Toast.LENGTH_SHORT).show();
               //tvResult.setText("새 게임을 누르세요");

            }

        }
    }

    public class onLeft implements OnClickListener {
        public void onClick(View v) {

            if(isRunning){
                int randomNum = rand.nextInt(2);
                if (nAnswerFront == 0){
                    scoreChange(true);
                    tvResult.setText(String.valueOf(nScore));
                    pushImage();
                }
                else {
                    scoreChange(false);
                    tvResult.setText(String.valueOf(nScore));
                    pushImage();
                }
            }
            else {

                Toast.makeText(getApplicationContext(),"Press Go!", Toast.LENGTH_SHORT).show();

                //tvResult.setText("새 게임을 누르세요");

            }

        }
    }
}

