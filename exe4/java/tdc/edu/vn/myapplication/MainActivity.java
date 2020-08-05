package tdc.edu.vn.myapplication;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //khai bao bien
    ImageView imageView1;
    ImageButton imgbtnPlay, imgbtnStop, imgbtnPrev, imgNext, imgDanhSach;
    TextView tenbaihat, tgdau, tgcuoi;
    SeekBar seekBar;
    ArrayList<Song> arrayList1;
    MediaPlayer mediaPlayer;
    int position = 0;
    SeekBar seekBarVolumn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setControl();

        setEvent();
    }

    private void setControl() {
        imageView1 = (ImageView) findViewById(R.id.img_baihat);
        imgbtnPlay = (ImageButton) findViewById(R.id.imgbtn_play);
        imgbtnStop = (ImageButton) findViewById(R.id.imgbtn_stop);
        imgbtnPrev = (ImageButton) findViewById(R.id.imgbtn_prev);
        imgNext = (ImageButton) findViewById(R.id.imgbtn_next);
        imgDanhSach = (ImageButton) findViewById(R.id.imgDanhSach);

        tenbaihat = (TextView) findViewById(R.id.tv_tenbaihat);
        tgdau = (TextView) findViewById(R.id.tv_dau);
        tgcuoi = (TextView) findViewById(R.id.tv_cuoi);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBarVolumn = (SeekBar) findViewById(R.id.seekBar_volumn);
    }

    private void khoitao() {
        arrayList1 = new ArrayList<>();
        arrayList1.add(new Song("Anh chẳng sao mà", R.drawable.khangviet, R.raw.b1));
        arrayList1.add(new Song("Anh nhớ nhé", R.drawable.anhnhonhe, R.raw.b2));
        arrayList1.add(new Song("Đời là thế thôi", R.drawable.phule, R.raw.b3));
        arrayList1.add(new Song("Anh có tài mà", R.drawable.anhcotaima, R.raw.b4));
        arrayList1.add(new Song("Ta đi tìm em", R.drawable.taditimem, R.raw.b5));
    }

    private void play() {
        try {
            position = getIntent().getExtras().getInt("ma");
            mediaPlayer = MediaPlayer.create(MainActivity.this, arrayList1.get(position).getFile());
            tenbaihat.setText(arrayList1.get(position).getTenbaihat());
            imageView1.setImageResource(arrayList1.get(position).getHinh());
            mediaPlayer.start();
            imgbtnPlay.setImageResource(R.drawable.ic_pause);
            setTime();
            updateTime();
        } catch (Exception ex) {
            position = 0;
            mediaPlayer = MediaPlayer.create(MainActivity.this, arrayList1.get(position).getFile());
            tenbaihat.setText(arrayList1.get(position).getTenbaihat());
            imageView1.setImageResource(arrayList1.get(position).getHinh());
        }


    }

    private void setEvent() {
        khoitao();
        //khoi tao bai hat
        play();
        imgDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DanhSach.class);
                startActivity(intent);
            }
        });
        imgbtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgbtnPlay.setImageResource(R.drawable.ic_play);
                } else {
                    mediaPlayer.start();
                    imgbtnPlay.setImageResource(R.drawable.ic_pause);
                }
                setTime();
                updateTime();
            }
        });

        imgbtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                imgbtnPlay.setImageResource(R.drawable.ic_play);
                //khoi tao lai bai hat
                play();
                setTime();
                updateTime();
            }
        });

        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if (position > arrayList1.size() - 1) {
                    position = 0;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                //khoi tao lai bai hat
                play();
                mediaPlayer.start();
                imgbtnPlay.setImageResource(R.drawable.ic_pause);
                setTime();
                updateTime();
            }
        });

        imgbtnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (position < 0) {
                    position = arrayList1.size() - 1;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                //khoi tao lai bai hat
                play();
                mediaPlayer.start();
                imgbtnPlay.setImageResource(R.drawable.ic_pause);
                setTime();
                updateTime();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.quay);
        imageView1.startAnimation(animation);

        final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolumn = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        seekBarVolumn.setMax(maxVolumn);

        seekBarVolumn.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, seekBarVolumn.getProgress(), 0);
            }
        });

    }

    private void setTime() {
        SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
        tgcuoi.setText(dinhDangGio.format(mediaPlayer.getDuration()) + "");

        //lay tong thoi gian 1 bai hat
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private void updateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
                tgdau.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()) + "");


                seekBar.setProgress(mediaPlayer.getCurrentPosition());

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position > arrayList1.size() - 1) {
                            position = 0;
                        }
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                        }

                        //khoi tao lai bai hat
                        mediaPlayer = MediaPlayer.create(MainActivity.this, arrayList1.get(position).getFile());
                        tenbaihat.setText(arrayList1.get(position).getTenbaihat());
                        mediaPlayer.start();
                        imgbtnPlay.setImageResource(R.drawable.ic_pause);
                        setTime();
                        updateTime();
                    }
                });
                handler.postDelayed(this, 500);
            }
        }, 100);
    }

    private void volumn() {
        final AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolumn = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        seekBarVolumn.setMax(maxVolumn);

        seekBarVolumn.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, seekBarVolumn.getProgress(), 0);
            }
        });


    }

}
