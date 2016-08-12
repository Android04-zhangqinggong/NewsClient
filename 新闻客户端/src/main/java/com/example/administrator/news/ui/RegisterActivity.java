package com.example.administrator.news.ui;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.news.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mAccount;
    EditText mPassword;
    Button mEnsure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAccount = (EditText) findViewById(R.id.et_register_account);
        mPassword = (EditText) findViewById(R.id.et_register_password);
        mEnsure = (Button) findViewById(R.id.btn_register_ensure);
        mEnsure.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //利用SharedPreferences保存数据
        SharedPreferences config = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor edit = config.edit();
        edit.putString("account",mAccount.getText().toString());
        edit.putString("password",mPassword.getText().toString());
        edit.commit();//必须提交
        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
        finish();//注册完成后关闭此activity
    }
}
