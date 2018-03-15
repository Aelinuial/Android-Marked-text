package com.example.aelin.mytext;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.example.aelin.mytext.ShowText.mark_clear;
import static com.example.aelin.mytext.ShowText.showtext;
import static com.example.aelin.mytext.main.file_id_now;
import static com.example.aelin.mytext.main.file_name_now;
import static com.example.aelin.mytext.ShowText.mark_pages;
import static com.example.aelin.mytext.ShowText.mark_ent1;
import static com.example.aelin.mytext.ShowText.mark_en1_type;
import static com.example.aelin.mytext.ShowText.mark_ent2;
import static com.example.aelin.mytext.ShowText.mark_en2_type;
import static com.example.aelin.mytext.ShowText.mark_relations;

/**
 * Created by aelin on 2017/11/28.
 */

public class MyJson {
    //创建json文件
    public static void SaveMyJson() {
        String jsonString="";
        JSONObject text_mark = new JSONObject();
        try {
            text_mark.put("文件名",file_name_now);
            JSONArray jsonarray=new JSONArray();
            for(int i=0;i<mark_pages.size();i++){
                JSONObject jsonObj=new JSONObject();
                jsonObj.put("页数",mark_pages.get(i));
                jsonObj.put("实体一",mark_ent1.get(i));
                jsonObj.put("实体一类型",mark_en1_type.get(i));
                jsonObj.put("实体二",mark_ent2.get(i));
                jsonObj.put("实体二类型",mark_en2_type.get(i));
                jsonObj.put("实体关系",mark_relations.get(i));
                jsonarray.put(jsonObj);
            }
            text_mark.put("标注记录",jsonarray);
            jsonString=text_mark.toString();
            System.out.println(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String path= Environment.getExternalStorageDirectory().toString()+"/"+file_id_now;
        System.out.println(path);
        File file = new File(path);
        try {
            if (file.exists()) {
                Toast.makeText(showtext,"标注文件将被覆盖",Toast.LENGTH_SHORT).show();
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(showtext,"标注存储失败",Toast.LENGTH_SHORT).show();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(jsonString.getBytes());
            fileOutputStream.close();
            Toast.makeText(showtext,"标注存储成功",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(showtext,"标注存储失败",Toast.LENGTH_SHORT).show();
        }
    }

    //读取json文件
    public static void ReadMyJson() {
        System.out.println("开始函数");
        mark_clear();
        JSONObject mymarkjson;
        String path= Environment.getExternalStorageDirectory().toString()+"/"+file_id_now;
        StringBuffer sb = new StringBuffer();
        File file = new File(path);
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder builder = new StringBuilder();
            while((line = br.readLine()) != null){
                builder.append(line);
            }
            br.close();
            try {
                mymarkjson =new JSONObject(builder.toString());
                JSONArray my_mark=mymarkjson.getJSONArray("标注记录");
                for(int i=0;i<my_mark.length();i++){
                    JSONObject temp_mark=my_mark.getJSONObject(i);
                    mark_pages.add(temp_mark.getInt("页数"));
                    mark_ent1.add(temp_mark.getString("实体一"));
                    mark_en1_type.add(temp_mark.getString("实体一类型"));
                    mark_ent2.add(temp_mark.getString("实体二"));
                    mark_en2_type.add(temp_mark.getString("实体二类型"));
                    mark_relations.add(temp_mark.getString("实体关系"));
                }
            }catch (JSONException e){
                Toast.makeText(showtext,"标注解析失败",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(showtext,"标注读取失败",Toast.LENGTH_SHORT).show();
        }
    }
}