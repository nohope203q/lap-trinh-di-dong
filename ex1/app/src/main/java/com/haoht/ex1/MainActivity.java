package com.haoht.ex1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    MaterialButton btnCall, btnEmail, btnGithub, btnFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCall = findViewById(R.id.btnCall);
        btnEmail = findViewById(R.id.btnEmail);
        btnGithub = findViewById(R.id.btnGithub);
        btnFacebook = findViewById(R.id.btnFacebook);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "0383760203";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myEmail = "thienhao.huynh.infosec@gmail.com";
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + myEmail));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Liên hệ từ ứng dụng giới thiệu");
                intent.putExtra(Intent.EXTRA_TEXT, "Chào bro, tôi muốn liên hệ về...");

                try {
                    startActivity(Intent.createChooser(intent, "Chọn ứng dụng gửi mail"));
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Lỗi: Không tìm thấy ứng dụng Email!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String githubUrl = "https://github.com/nohope203q";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));
                startActivity(intent);
            }
        });

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String facebookUrl = "https://www.facebook.com/thienhao.huynh.203/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl));
                startActivity(intent);
            }
        });

    }
}