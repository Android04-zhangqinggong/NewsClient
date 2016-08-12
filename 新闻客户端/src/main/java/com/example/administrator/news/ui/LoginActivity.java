package com.example.administrator.news.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.news.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "LoginActivity";
    EditText mAccountEdit;
    EditText mPasswordEdit;
    CheckBox mCheckBox;
    Button mLogin;//登录
    Button mRegister;//注册
    private SharedPreferences mPrf;
    private SharedPreferences.Editor mEdit;
    private SharedPreferences mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAccountEdit = (EditText) findViewById(R.id.et_account);
        mPasswordEdit = (EditText) findViewById(R.id.et_password);
        mCheckBox = (CheckBox) findViewById(R.id.cb_remenberpassword);
        mLogin = (Button) findViewById(R.id.btn_login);
        mRegister = (Button) findViewById(R.id.btn_register);
        //保存数据,名称为isremember
        mPrf = getSharedPreferences("isremember",MODE_PRIVATE);
        //不是赋值,如果没有值,get不到,默认false
        boolean isRemember= mPrf.getBoolean("remember_password", false);

        mData = getSharedPreferences("data",MODE_PRIVATE);
        if (isRemember){
            /*如果选择了记住密码,将记住的账号和密码都展示在输入*/
            mAccountEdit.setText(mData.getString("account",""));
            mPasswordEdit.setText(mData.getString("password",""));
            //并且chekBox显示打钩状态
            mCheckBox.setChecked(true);
            Log.d(TAG, "onCreate: " + "密码显示在输入框!!!!!" + mData.getString("account","") + mData.getString("password",""));
        }

        mLogin.setOnClickListener(this);
        mRegister.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login://点击登录,获取输入框账号和密码,进行匹配
                String account = mAccountEdit.getText().toString();
                String password = mPasswordEdit.getText().toString();
                mData = getSharedPreferences("data",MODE_PRIVATE);
                if (account.equals(mData.getString("account","")) &&
                        password.equals(mData.getString("password",""))){
                    //根据打钩与否,更改isremember数据的状态
                    mEdit = mPrf.edit();
                    if (mCheckBox.isChecked()){
                        mEdit.putBoolean("remember_password", true);
                        Log.d(TAG, "onClick: " + "记住密码!!!!!");
                    }else{
                        mEdit.clear();
                    }
                    mEdit.commit();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "用户名或密码不对", Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.btn_register://点击注册,跳转至注册界面
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;

            default:
                break;


        }
    }
}
