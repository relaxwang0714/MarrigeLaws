package com.project.android.marrigelaws;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 13zqn on 2017/6/30.
 */

public class Login extends AppCompatActivity{
    private TextView mSign;
    private Button mLogin;
    private EditText user;
    private EditText pwd;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlogin);
        mSign = (TextView)findViewById(R.id.sign);
        mLogin = (Button)findViewById(R.id.login);
        mSign.setOnClickListener(new msignButton());
        mLogin.setOnClickListener(new mloginButton());
        user =(EditText)findViewById(R.id.user_login);
        pwd = (EditText) findViewById(R.id.pwd_login);
        getSupportActionBar().hide();
    }
    private class msignButton implements View.OnClickListener{
        public void onClick(View view){
            Intent intent = new Intent();
            intent.setClass(Login.this,Sign.class);
            Login.this.startActivity(intent);
        }
    }
   private class mloginButton implements View.OnClickListener{
        public void onClick(View view){
            if(validate()){
                if(loginPro()){
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    DialogUtil.showDialog(Login.this,"error",false);
                }
            }

        }
    }

    private boolean loginPro() {
        String username =user.getText().toString();
        String password =pwd.getText().toString();
        JSONObject jsonObject;
        try{
            Login ab = new Login();
            String result = ab.query(username,password);
            System.out.print(result);
            JSONTokener jsonTokener =new JSONTokener(result);
            jsonObject =(JSONObject)jsonTokener.nextValue();

            if(jsonObject.getInt("user")>0){
                return true;
            }
        }
        catch(Exception e){
//            DialogUtil.showDialog(Login.this,"wrong",false);
            e.printStackTrace();

        }
        return false;
    }
    private boolean validate(){
        String username = user.getText().toString().trim();
        if(username.equals("")){
            DialogUtil.showDialog(Login.this,"请输入用户名",false);
            return false;
        }
        return true;
    }
    private String query(String username,String password) throws Exception{
        Map<String ,String> map = new HashMap<>();
        map.put("user",username);
        map.put("pass",password);
        String url =HttpUtil.BASE_URLThree;
        return new String(HttpUtil.postRequest(url,map));

    }


}

