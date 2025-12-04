package com.haoht.ex7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class ProfileActivity extends AppCompatActivity {

    ImageView imgProfile;
    private final ActivityResultLauncher<Intent> mAvatarResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    String newAvatarUrl = result.getData().getStringExtra("new_avatar_url");

                    if (newAvatarUrl != null) {
                        Glide.with(ProfileActivity.this)
                                .load(newAvatarUrl)
                                .placeholder(R.drawable.avt)
                                .skipMemoryCache(true)
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .into(imgProfile);
                        Toast.makeText(ProfileActivity.this, "Đã cập nhật ảnh profile!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imgProfile = findViewById(R.id.profile_image);

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, UploadImageActivity.class);
                mAvatarResultLauncher.launch(intent);
            }
        });
    }
}