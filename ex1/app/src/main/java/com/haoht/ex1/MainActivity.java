package com.haoht.ex1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private MaterialButton btnCall, btnEmail, btnGithub, btnFacebook;
    private Button btnProcess;
    private EditText etInput4;

    private EditText etInput5;
    private Button btnProcess5;
    private TextView tvOutput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        btnCall = findViewById(R.id.btnCall);
        btnEmail = findViewById(R.id.btnEmail);
        btnGithub = findViewById(R.id.btnGithub);
        btnFacebook = findViewById(R.id.btnFacebook);
        btnProcess = findViewById(R.id.btnProcess);
        etInput4 = findViewById(R.id.etInput4);
        etInput5 = findViewById(R.id.etInput5);
        btnProcess5 = findViewById(R.id.btnProcess5);
        tvOutput = findViewById(R.id.tvOutput);
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

        btnProcess.setOnClickListener(v -> {
            String input = etInput4.getText().toString().trim();

            if (input.isEmpty()) {
                Log.d("RESULT", "Chưa nhập dữ liệu!");
                return;
            }

            String[] numbers = input.split(",");
            StringBuilder odd = new StringBuilder("Số lẻ: ");
            StringBuilder even = new StringBuilder("Số chẵn: ");

            for (String numStr : numbers) {
                try {
                    int n = Integer.parseInt(numStr.trim());
                    if (n % 2 == 0) {
                        even.append(n).append(" ");
                    } else {
                        odd.append(n).append(" ");
                    }
                } catch (NumberFormatException e) {
                    Log.e("ERROR", "Giá trị không hợp lệ: " + numStr);
                }
            }

            Log.d("RESULT", odd.toString());
            Log.d("RESULT", even.toString());
        });

        btnProcess5.setOnClickListener(v -> {
            String input = etInput5.getText().toString().trim();

            if (input.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập chuỗi!", Toast.LENGTH_SHORT).show();
                return;
            }

            String reversed = reverseWords(input);
            String upper = reversed.toUpperCase();
            tvOutput.setText(upper);
        });
    }

    private String reverseWords(String s) {
        String[] words = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i > 0) sb.append(" ");
        }
        return sb.toString();
    }
    }