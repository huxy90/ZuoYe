package com.jiyun.day01_zy.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jiyun.day01_zy.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
