package com.example.aelin.mytext;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.res.AssetManager;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class main extends BaseActivity {
    public static String file_name_now;
    public static String file_id_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("实体标注");
        Button button_choose = (Button) findViewById(R.id.button7);
        Button button_exit = (Button) findViewById(R.id.button6);
        Button btnreadme=(Button)findViewById(R.id.btnreadme);

        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
            }
        });
        btnreadme.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(main.this,ReadMe.class);
                startActivity(intent);
            }
        });
        button_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(main.this,ChooseFile.class);
                startActivity(intent);
            }
        });
    }

    //查找文件
    private String searchFile(String path) {
        AssetManager assetManager = getAssets();
        String[] files = null ;
        String result = "";
        try {
            files = assetManager.list(path);
        } catch (IOException e) {
            Log.e ("tag", e.getMessage());
        }
        for (String file : files) {
            result += file + "\n";
        }
        return result;
    }
}


