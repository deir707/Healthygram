package com.unipi.diodeir.healthygram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Objects;

public class StartTimerActivity extends AppCompatActivity {
    TextView textView114, textView116,textView117,textView120;
    ImageButton imageButton72;
    Integer sets,min1,min2,sec1,sec2,counter,counter2,counter3,i,timer_num;
    Boolean flag;
    Calendar calendar = Calendar.getInstance();
    MediaPlayer player1,player2,player3;
    CountDownTimer timer;
    RelativeLayout relativeLayout;
    DatabaseReference reference;
    FirebaseAuth auth;
    Dialog dialog;
    String id,date = "";
    Double time, total;
    @SuppressLint("SimpleDateFormat")
    Format f1 = new SimpleDateFormat("mm");
    @SuppressLint("SimpleDateFormat") Format f2 = new SimpleDateFormat("ss");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MM dd");
    DecimalFormat df = new DecimalFormat("#.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_timer);
        textView114 = findViewById(R.id.textView114);
        textView116 = findViewById(R.id.textView116);
        textView117 = findViewById(R.id.textView117);
        textView120 = findViewById(R.id.textView120);
        imageButton72 = findViewById(R.id.imageButton72);
        relativeLayout = findViewById(R.id.relativeLayout);
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);
        auth = FirebaseAuth.getInstance();
        id = auth.getUid();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(id).child("Personal Data").child("Statistics");
        flag = false;
        total = 0.0;
        i =1;
        player1 = MediaPlayer.create(this,R.raw.ding);
        player2 = MediaPlayer.create(this,R.raw.finish);
        player3 = MediaPlayer.create(this,R.raw.tada);
        sets = min1 = min2 = sec1 = sec2 = counter2 = counter3 = 0;
        Intent intent = getIntent();
        sets = intent.getIntExtra("sets",sets);
        min1 = intent.getIntExtra("workmin",min1);
        sec1 = intent.getIntExtra("worksec",sec1);
        min2 = intent.getIntExtra("restmin",min2);
        sec2 = intent.getIntExtra("restsec",sec2);
        time = (double) (((min1 * 60) + sec1) * sets);
        counter = 5;
        timer_num = 0;
        startTimer();
    }

    public void startTimer() {
        relativeLayout.setBackgroundResource(R.color.yellow);
        timer = new CountDownTimer(counter * 1000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                if (counter <= 3) {
                    player1.start();
                }
                calendar.set(Calendar.SECOND, counter);
                calendar.set(Calendar.MINUTE, 0);
                textView114.setText(f1.format(calendar.getTime()));
                textView116.setText(f2.format(calendar.getTime()));
                if (counter >= 3) {
                    textView120.setText("Ready?");
                } else if (counter >= 1) {
                    textView120.setText("Steady?");
                }
                if (flag) {
                    timer.cancel();
                    timer_num = 1;
                }
                counter--;
            }
            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                player2.start();
                calendar.set(Calendar.SECOND, counter);
                calendar.set(Calendar.MINUTE, 0);
                textView114.setText(f1.format(calendar.getTime()));
                textView116.setText(f2.format(calendar.getTime()));
                textView120.setText("Go!");
                workStart();
            }
        };
        timer.start();
    }
    public void workStart() {
        timer = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @SuppressLint({"SetTextI18n"})
            @Override
            public void onFinish() {
                calendar.set(Calendar.SECOND, sec1);
                calendar.set(Calendar.MINUTE, min1);
                counter2 = min1 * 60 + sec1;
                textView120.setText("Set " + i);
                relativeLayout.setBackgroundResource(R.color.green);
                timer = new CountDownTimer(counter2 * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (sec1 <= 3) {
                            player1.start();
                        }
                        calendar.set(Calendar.SECOND, sec1);
                        calendar.set(Calendar.MINUTE, min1);
                        textView114.setText(f1.format(calendar.getTime()));
                        textView116.setText(f2.format(calendar.getTime()));
                        sec1--;
                        if (flag) {
                            timer.cancel();
                            timer_num = 2;
                        }
                    }
                    @Override
                    public void onFinish() {
                        player2.start();
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MINUTE, 0);
                        textView114.setText(f1.format(calendar.getTime()));
                        textView116.setText(f2.format(calendar.getTime()));
                        i++;
                        timer = new CountDownTimer(1000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                            }
                            @Override
                            public void onFinish() {
                                if (i > sets) {
                                    relativeLayout.setBackgroundResource(R.color.cyan);
                                    textView120.setText("Finish...");
                                    player3.start();
                                    imageButton72.setImageResource(R.drawable.baseline_save_24);
                                    imageButton72.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    time = Double.valueOf(df.format(time / 60));
                                                    reference.child(date).child("Training Time").setValue((time));
                                                    Toast.makeText(StartTimerActivity.this,"Your training's time has been saved!",Toast.LENGTH_LONG).show();
                                                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                                                total = (total + (Double.parseDouble(Objects.requireNonNull(dataSnapshot.child("Training Time").getValue()).toString())) / 60);
                                                            }
                                                            if (total >= 156 && total < 208) {
                                                                medal_achievement1();
                                                            }
                                                            else if (total >= 208 && total < 260) {
                                                                medal_achievement1();
                                                            }
                                                            else if (total >= 260) {
                                                                medal_achievement1();
                                                            }
                                                        }
                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {
                                                        }
                                                    });
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });
                                        }
                                    });
                                }
                                else {
                                        restStart();
                                }
                            }
                        };
                        timer.start();

                    }
                };
                timer.start();
            }
        };
        timer.start();
    }

    @SuppressLint({"SetTextI18n"})
    public void restStart() {
        counter3 = min2 * 60 + sec2;
        textView120.setText("Resting...");
        relativeLayout.setBackgroundResource(R.color.cyan);
        timer = new CountDownTimer(counter3*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (sec2 <= 3) {
                    player1.start();
                }
                calendar.set(Calendar.SECOND,sec2);
                calendar.set(Calendar.MINUTE,min2);
                textView114.setText(f1.format(calendar.getTime()));
                textView116.setText(f2.format(calendar.getTime()));
                sec2--;
                if (flag) {
                    timer.cancel();
                    timer_num = 3;
                }
            }
            @Override
            public void onFinish() {
                player2.start();
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MINUTE,0);
                textView114.setText(f1.format(calendar.getTime()));
                textView116.setText(f2.format(calendar.getTime()));
                Intent intent = getIntent();
                min1 = intent.getIntExtra("workmin",min1);
                sec1 = intent.getIntExtra("worksec",sec1);
                min2 = intent.getIntExtra("restmin",min2);
                sec2 = intent.getIntExtra("restsec",sec2);
                workStart();
            }
        };
        timer.start();
    }
    public void pause(View view) {
        if (!flag) {
            imageButton72.setImageResource(R.drawable.baseline_play_arrow_24);
            flag = true;
        }
        else {
            imageButton72.setImageResource(R.drawable.baseline_pause_24);
            flag = false;
            if (timer_num == 1 && sets >= i) {
                startTimer();
            }
            else if (timer_num == 2 && sets >= i) {
                workStart();
            }
            else if (timer_num == 3 && sets >= i) {
                restStart();
            }
        }
    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please press BACK again to exit", Toast.LENGTH_LONG).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    @SuppressLint("SetTextI18n")
    public void medal_achievement1() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.medal_achievement);
        Objects.requireNonNull(dialog.getWindow()).setLayout(1200,1000);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        TextView textview = dialog.findViewById(R.id.textView123);
        ImageView imageView = dialog.findViewById(R.id.imageView15);
        if (total >= 156 && total < 208) {
            textview.setText("Congratulations! You have been training over than 156 hours!");
            imageView.setImageResource(R.drawable.baseline_bronze_medal_24);
        }
        else if (total >= 208 && total < 260) {
            textview.setText("Congratulations! You have been training over than 208 hours!");
            imageView.setImageResource(R.drawable.baseline_silver_medal_24);
        }
        else if (total >= 260) {
            textview.setText("Congratulations! You have been training over than 260 hours!");
            imageView.setImageResource(R.drawable.baseline_gold_medal_24);
        }
        dialog.show();
    }
}