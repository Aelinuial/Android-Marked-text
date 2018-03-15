package com.example.aelin.mytext;

import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.ScrollingMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

import static com.example.aelin.mytext.MyJson.ReadMyJson;
import static com.example.aelin.mytext.MyJson.SaveMyJson;
import static com.example.aelin.mytext.main.file_name_now;

/**
 * Created by aelin on 2017/11/17.
 */

public class ShowText extends BaseActivity {
    //用来记录已标注的
    public static ArrayList<Integer> mark_pages = new ArrayList<Integer>();
    public static ArrayList<String> mark_ent1 = new ArrayList<String>();
    public static ArrayList<String> mark_en1_type = new ArrayList<String>();
    public static ArrayList<String> mark_ent2 = new ArrayList<String>();
    public static ArrayList<String> mark_en2_type = new ArrayList<String>();
    public static ArrayList<String> mark_relations = new ArrayList<String>();

    public static int mark_type_now = 0;//当前标注的关系
    public static int mark_flag = 0;//当前标注的状态

    public static int mark_entity_last;//上一个选中的实体类型
    public static int mark_entity_now;//现在选中的实体类型

    private int mark_last_start;//上一次选中的起点
    private int mark_last_end;//上一次选中的终点
    private int mark_now_start;
    private int mark_now_end;

    private int position;
    private int wholepage;
    private int word_type = 18;
    public static ReadView mytext;
    public static TextView mark_1;
    public static TextView mark_2;
    private ArrayList<String> datas = new ArrayList<String>();
    public static ShowText showtext;

    public static int readjson_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_text);
        setTitle(file_name_now);
        readjson_flag=0;
        showtext = this;
        mark_clear();
        Intent intent = getIntent();
        datas = getIntent().getStringArrayListExtra("extra_data");
        wholepage = datas.size();//可翻页的总页数
        mytext = (ReadView) findViewById(R.id.textView);
        //mytext.setMovementMethod(ScrollingMovementMethod.getInstance());
        loadPage(0);
        mytext.setTextSize(word_type);
        mark_1 = (TextView) findViewById(R.id.mark_1);
        mark_1.setMovementMethod(ScrollingMovementMethod.getInstance());
        mark_2 = (TextView) findViewById(R.id.mark_2);
        mark_2.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    //加载某一页
    private void loadPage(int position) {
        mytext.setText(datas.get(position));
    }

    //上一页按钮
    public void previewPageBtn(View view) {
        if (position > 0) {
            position -= 1;
            loadPage(position);
            mark_1.setText("");
            mark_2.setText("");
            //mytext.resize();
        }
    }

    //下一页按钮
    public void nextPageBtn(View view) {
        if (position < wholepage - 1) {
            position += 1;
            loadPage(position);
            mark_1.setText("");
            mark_2.setText("");
            //mytext.resize();
        }
    }

    //重写onCreateOptionMenu(Menu menu)方法，当菜单第一次被加载时调用
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //填充选项菜单（读取XML文件、解析、加载到Menu组件上）
        //getMenuInflater().inflate(R.menu.show_text, menu);
        menu.add(0, 1, Menu.NONE, "字体放大");
        menu.add(0, 2, Menu.NONE, "字体缩小");
        SubMenu submenu = menu.addSubMenu(0, 3, Menu.NONE, "标注关系");
        submenu.add(3, 301, 1, "所属国家");
        submenu.add(3, 302, 2, "国籍");
        SubMenu submenu2 = menu.addSubMenu(0, 4, Menu.NONE, "实体类型");
        submenu2.add(4, 401, 1, "国家名");
        submenu2.add(4, 402, 2, "城市名");
        submenu2.add(4, 403, 3, "人名");
        menu.add(0, 5, Menu.NONE, "存储标注");
        menu.add(0, 6, Menu.NONE, "显示标注");
        menu.add(0, 7, Menu.NONE, "退出");
        return true;
    }

    //重写OptionsItemSelected(MenuItem item)来响应菜单项(MenuItem)的点击事件（根据id来区分是哪个item）
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1://文字放大
                if (word_type < 21)
                    mytext.setTextSize(++word_type);
                break;
            case 2://文字缩小
                if (word_type > 15)
                    mytext.setTextSize(--word_type);
                break;
            case 301:
                mark_type_now = 1;
                break;
            case 302:
                mark_type_now = 2;
                break;
            case 401:
                MyMark(1);
                break;
            case 402:
                MyMark(2);
                break;
            case 403:
                MyMark(3);
                break;
            case 5:
                SaveMyJson();
                mark_clear();
                break;
            case 6:
                if(readjson_flag!=1)
                    ReadMyJson();
                readjson_flag=1;
                int temp1=isinmark(position,"所属国家");
                int temp2=isinmark(position,"国籍");
                if(temp1==-1&&temp2==-1){
                    Toast.makeText(ShowText.this, "该页没有标注记录", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (temp1 != -1) {
                        mark_1.setText(Setmark(temp1));
                    }
                    if (temp2 != -1) {
                        mark_2.setText(Setmark(temp2));
                    }
                    Toast.makeText(ShowText.this, "已显示该页标注", Toast.LENGTH_SHORT).show();
                }
                break;
            case 7:
                ActivityCollector.finishAll();
                break;
        }
        return false;
    }

    //标注
    private void MyMark(int type) {
        if(mark_type_now==0){
            Toast.makeText(ShowText.this,"未选择标注关系",Toast.LENGTH_SHORT).show();
            return;
        }
        String show_mark = "";
        if (mark_flag == 0) {
            mark_last_start = mytext.getSelectionStart();
            mark_last_end = mytext.getSelectionEnd();
            mark_entity_last = type;
            mark_flag = 1;
        } else {
            switch (mark_type_now) {
                case 1:
                    mark_entity_now = type;
                    mark_now_start = mytext.getSelectionStart();
                    mark_now_end = mytext.getSelectionEnd();
                    SpannableStringBuilder ssbuilder = new SpannableStringBuilder(mytext.getText().toString());
                    //ForegroundColorSpan--文字前景色，BackgroundColorSpan--文字背景色
                    ForegroundColorSpan blueSpan_last = new ForegroundColorSpan(Color.BLUE);
                    ForegroundColorSpan blueSpan_now = new ForegroundColorSpan(Color.BLUE);
                    ssbuilder.setSpan(blueSpan_last, mark_last_start, mark_last_end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssbuilder.setSpan(blueSpan_now, mark_now_start, mark_now_end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mytext.setText(ssbuilder);
                    break;
                case 2:
                    mark_entity_now = type;
                    mark_now_start = mytext.getSelectionStart();
                    mark_now_end = mytext.getSelectionEnd();
                    SpannableStringBuilder ssbuilder2 = new SpannableStringBuilder(mytext.getText().toString());
                    //ForegroundColorSpan--文字前景色，BackgroundColorSpan--文字背景色
                    ForegroundColorSpan yellowSpan_last = new ForegroundColorSpan(Color.YELLOW);
                    ForegroundColorSpan yellowSpan_now = new ForegroundColorSpan(Color.YELLOW);
                    ssbuilder2.setSpan(yellowSpan_last, mark_last_start, mark_last_end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    ssbuilder2.setSpan(yellowSpan_now, mark_now_start, mark_now_end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    mytext.setText(ssbuilder2);
                    break;
            }
            String en1 = mytext.getText().toString();
            en1 = en1.substring(mark_last_start, mark_last_end);
            String en2 = mytext.getText().toString();
            en2 = en2.substring(mark_now_start, mark_now_end);
            show_mark = "实体一：" + en1 + "  类型：" + entitytypetoword(mark_entity_last) + "\n实体二：" + en2 + "  类型：" + entitytypetoword(mark_entity_now) + "\n对应关系：" + marktypetoword(mark_type_now);
            //将该条标注记录记下来
            mark_pages.add(position);
            mark_ent1.add(en1);
            mark_en1_type.add(entitytypetoword(mark_entity_last));
            mark_ent2.add(en2);
            mark_en2_type.add(entitytypetoword(mark_entity_now));
            mark_relations.add(marktypetoword(mark_type_now));
            switch (mark_type_now) {
                case 1:
                    mark_1.setText(show_mark);
                    break;
                case 2:
                    mark_2.setText(show_mark);
                    break;
            }
            mark_flag = 0;
        }
    }

    //实体类型转换成文字
    public String entitytypetoword(int type) {
        if (type == 1)
            return "国家名";
        if (type == 2)
            return "城市名";
        if (type == 3)
            return "人名";
        else
            return "类型错误";
    }

    //关系类型转换成文字
    public String marktypetoword(int type) {
        if (type == 1)
            return "所属国家";
        if (type == 2)
            return "国籍";
        else
            return "关系错误";
    }

    //清空现有的记录
    public static void mark_clear() {
        mark_pages.clear();
        mark_ent1.clear();
        mark_en1_type.clear();
        mark_ent2.clear();
        mark_en2_type.clear();
        mark_relations.clear();
    }

    //判断该页面是否有标注
    public int isinmark(int page,String relation) {
        for(int i=0;i<mark_pages.size();i++){
            if(mark_pages.get(i)==page&&mark_relations.get(i).equals(relation))
                return i;
        }
        return -1;
    }

    //显示标注
    public String Setmark(int index){
        String setmarkstring="";
        String en1=mark_ent1.get(index);
        String type1=mark_en1_type.get(index);
        String en2=mark_ent2.get(index);
        String rela=mark_relations.get(index);
        String type2=mark_en2_type.get(index);
        setmarkstring="实体一：" + en1 + "  类型：" + type1 + "\n实体二：" + en2 + "  类型：" + type2 + "\n对应关系：" + rela;
        return setmarkstring;
    }
}
