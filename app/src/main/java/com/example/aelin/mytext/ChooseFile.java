package com.example.aelin.mytext;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static com.example.aelin.mytext.main.file_id_now;
import static com.example.aelin.mytext.main.file_name_now;

/**
 * Created by aelin on 2017/11/29.
 */

//选择文件的页面
public class ChooseFile extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosefile);
        setTitle("请选择文本编号");
        TextView filelist=(TextView)findViewById(R.id.filelist);
        filelist.setText("【1】安倍晋三再次当选日本首相 第4次内阁于今夜启动\n" +
                "【2】巴黎大师赛蒂姆险胜过关 8号种子吞完败出局\n" +
                "【3】杜特尔特称愿与日加强关系 日媒：安倍露出满意表情\n" +
                "【4】俄阿伊三国元首推动跨里海地区合作\n" +
                "【5】俄或遭生化袭击？普京：国外势力正采集俄公民DNA\n" +
                "【6】法国：紧急状态终结束　反恐力度仍不减\n" +
                "【7】今日，阿富汗、韩国均发生油罐车爆炸事件 多人伤亡\n" +
                "【8】沙特授机器人国籍比建跨国新城还震惊 忘了索菲亚曾说要毁灭人类？\n" +
                "【9】索马里首都遭汽车炸弹袭击 至少23人丧生30人伤\n" +
                "【10】外媒：特朗普访韩有意绕开板门店 往届美国总统均曾到访");
        filelist.setMovementMethod(new ScrollingMovementMethod());

        //绑定按钮
        Button file1=(Button)findViewById(R.id.file1);
        Button file2=(Button)findViewById(R.id.file2);
        Button file3=(Button)findViewById(R.id.file3);
        Button file4=(Button)findViewById(R.id.file4);
        Button file5=(Button)findViewById(R.id.file5);
        Button file6=(Button)findViewById(R.id.file6);
        Button file7=(Button)findViewById(R.id.file7);
        Button file8=(Button)findViewById(R.id.file8);
        Button file9=(Button)findViewById(R.id.file9);
        Button file10=(Button)findViewById(R.id.file10);

        file1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                file_id_now="1.txt";
                file_name_now="安倍晋三再次当选日本首相 第4次内阁于今夜启动";
                ArrayList<String> datas=ReadMyFile();
                Intent intent=new Intent(ChooseFile.this,ShowText.class);
                intent.putStringArrayListExtra("extra_data",datas);
                startActivity(intent);
            }
        });

        file2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                file_id_now="2.txt";
                file_name_now="巴黎大师赛蒂姆险胜过关 8号种子吞完败出局";
                ArrayList<String> datas=ReadMyFile();
                Intent intent=new Intent(ChooseFile.this,ShowText.class);
                intent.putStringArrayListExtra("extra_data",datas);
                startActivity(intent);
            }
        });

        file3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                file_id_now="3.txt";
                file_name_now="杜特尔特称愿与日加强关系 日媒：安倍露出满意表情";
                ArrayList<String> datas=ReadMyFile();
                Intent intent=new Intent(ChooseFile.this,ShowText.class);
                intent.putStringArrayListExtra("extra_data",datas);
                startActivity(intent);
            }
        });

        file4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                file_id_now="4.txt";
                file_name_now="俄阿伊三国元首推动跨里海地区合作";
                ArrayList<String> datas=ReadMyFile();
                Intent intent=new Intent(ChooseFile.this,ShowText.class);
                intent.putStringArrayListExtra("extra_data",datas);
                startActivity(intent);
            }
        });

        file5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                file_id_now="5.txt";
                file_name_now="俄或遭生化袭击？普京：国外势力正采集俄公民DNA";
                ArrayList<String> datas=ReadMyFile();
                Intent intent=new Intent(ChooseFile.this,ShowText.class);
                intent.putStringArrayListExtra("extra_data",datas);
                startActivity(intent);
            }
        });

        file6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                file_id_now="6.txt";
                file_name_now="法国：紧急状态终结束　反恐力度仍不减";
                ArrayList<String> datas=ReadMyFile();
                Intent intent=new Intent(ChooseFile.this,ShowText.class);
                intent.putStringArrayListExtra("extra_data",datas);
                startActivity(intent);
            }
        });

        file7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                file_id_now="7.txt";
                file_name_now="今日，阿富汗、韩国均发生油罐车爆炸事件 多人伤亡";
                ArrayList<String> datas=ReadMyFile();
                Intent intent=new Intent(ChooseFile.this,ShowText.class);
                intent.putStringArrayListExtra("extra_data",datas);
                startActivity(intent);
            }
        });

        file8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                file_id_now="8.txt";
                file_name_now="沙特授机器人国籍比建跨国新城还震惊 忘了索菲亚曾说要毁灭人类？";
                ArrayList<String> datas=ReadMyFile();
                Intent intent=new Intent(ChooseFile.this,ShowText.class);
                intent.putStringArrayListExtra("extra_data",datas);
                startActivity(intent);
            }
        });

        file9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                file_id_now="9.txt";
                file_name_now="索马里首都遭汽车炸弹袭击 至少23人丧生30人伤";
                ArrayList<String> datas=ReadMyFile();
                Intent intent=new Intent(ChooseFile.this,ShowText.class);
                intent.putStringArrayListExtra("extra_data",datas);
                startActivity(intent);
            }
        });

        file10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                file_id_now="10.txt";
                file_name_now="外媒：特朗普访韩有意绕开板门店 往届美国总统均曾到访";
                ArrayList<String> datas=ReadMyFile();
                Intent intent=new Intent(ChooseFile.this,ShowText.class);
                intent.putStringArrayListExtra("extra_data",datas);
                startActivity(intent);
            }
        });
    }

    //读取文本
    public ArrayList<String> ReadMyFile(){
        AssetManager assetManager = getAssets();
        InputStream inputStream  = null ;
        try {
            inputStream = assetManager.open("mytext/" + file_id_now);
        } catch (IOException e) {
            System.out.println("读取文本失败了");
        }
        String data = getString(inputStream);
        ArrayList<String> datas=StringtoSentence(data);
        return datas;
    }

    public static String getString(InputStream inputStream) {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        String word;
        try {
            //一行一行地读文件
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    //字符串先归并段落再按句号分开
    public static ArrayList<String> StringtoSentence(String datas) {
        ArrayList<String> sentencedatas = new ArrayList<String>();
        String tempstring="";
        if (datas != null) {
            String[] tempps = datas.split("\n");
            for (int i = 0; i < tempps.length; i++) {
                tempstring+=tempps[i];
            }
            String[] ps = tempstring.split("。");
            for (int i = 0; i < ps.length; i++) {
                ps[i]=ps[i]+"。";
                sentencedatas.add(ps[i]);
            }
        }
        return sentencedatas;
    }
}
