package com.example.administrator.news.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.news.R;

public class AccountShowActivity extends AppCompatActivity {
    TextView mTextView;
    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_show);
        mTextView = (TextView) findViewById(R.id.tv_showaccount);
        mButton = (Button) findViewById(R.id.btn_logout);
        SharedPreferences data = getSharedPreferences("data", MODE_PRIVATE);
        String account = data.getString("account", "");
        mTextView.setText(account);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountShowActivity.this,LoginActivity.class);
                startActivity(intent);
                //点击后,更改isLogin的状态,下次点击头像直接到登录界面
                SharedPreferences isLogin = getSharedPreferences("isLogin", MODE_PRIVATE);
                SharedPreferences.Editor edit = isLogin.edit();
                edit.putBoolean("islogin",false);
                edit.commit();
                finish();
                //jaslfks;lakf  k 'pokiqpotipo34q
            }
        });

    }
}
