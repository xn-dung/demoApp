package com.example.myapplication.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.model.BaiDang;
import com.example.myapplication.model.NguyenLieu;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class FoodPostActivity extends AppCompatActivity {

    private TextView tvTenMon;
    private TextView tvNguyenLieu;
    private TextView tvCachLam;
    private TextView tvLuotThich;
    private YouTubePlayerView youtubePlayerView;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_post);


        Intent intent = getIntent();
        BaiDang baiDang = (BaiDang) intent.getSerializableExtra("baidang");


        tvTenMon = findViewById(R.id.tvTenMon);
        tvNguyenLieu = findViewById(R.id.tvNguyenLieu);
        tvCachLam = findViewById(R.id.tvCachLam);
        tvLuotThich = findViewById(R.id.tvLuotThich);
        youtubePlayerView = findViewById(R.id.youtubePlayerView);
        btnBack = findViewById(R.id.btnBack);


        tvTenMon.setText(baiDang.getTenMon());
        tvCachLam.setText(baiDang.getCachLam());
        tvLuotThich.setText(String.valueOf(baiDang.getLuotThich()));

        StringBuilder nlBuilder = new StringBuilder();
        for (NguyenLieu nl : baiDang.getNguyenLieu()) {
            nlBuilder.append("- ").append(nl.getTen()).append("\n");
        }
        tvNguyenLieu.setText(nlBuilder.toString());


        getLifecycle().addObserver(youtubePlayerView);
        String linkYtb = baiDang.getLinkYtb();
        String videoId = extractYoutubeVideoId(linkYtb);

        youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0);
                youTubePlayer.unMute();
                youTubePlayer.setVolume(100);
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodPostActivity.this, SearchFoodByNameActivity.class);
                startActivity(intent);
            }
        });
    }


    private String extractYoutubeVideoId(String url) {
        if (url == null || url.isEmpty()) return "";

        if (url.contains("v=")) {
            String id = url.substring(url.indexOf("v=") + 2);
            int ampIndex = id.indexOf("&");
            if (ampIndex != -1) {
                id = id.substring(0, ampIndex);
            }
            return id;
        }


        if (url.contains("youtu.be/")) {
            String id = url.substring(url.indexOf("youtu.be/") + 9);
            int qmIndex = id.indexOf("?");
            if (qmIndex != -1) {
                id = id.substring(0, qmIndex);
            }
            return id;
        }

        return url;
    }
}
