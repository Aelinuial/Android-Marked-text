package com.example.aelin.mytext;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

/**
 * Created by aelin on 2017/11/29.
 */

//显示说明的页面
public class ReadMe extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readme);
        setTitle("软件说明");
        TextView readme=(TextView)findViewById(R.id.textView3);
        readme.setText("文本实体标注\n" +
                "\n" +
                "【版本号】Ver1.0\n" +
                "\n" +
                "【时间】2017-11-29\n" +
                "\n" +
                "【作者】Dango  Birdy\n" +
                "\n" +
                "【使用说明】\n" +
                "1)点击选择文件，选择一篇内置文本\n" +
                "2)点击右上角的菜单，选择“标注关系”并选择一种关系\n" +
                "3)长按要标注的文字并选中，点击右上角的菜单，选择“实体类型”并选择一种类型\n" +
                "4)长按要标注的另一段文字并选中，重复如上步骤\n" +
                "5)标注结果会显示在页面上，当整篇文章都标注完成后，点击右上角菜单，选择“存储标注”\n" +
                "6)再次打开文本时，点击右上角菜单，选择“显示标注”，若该页面有过标注记录则会显示在页面上\n" +
                "\n" +
                "【注意事项】\n" +
                "1)一句话中，同一种关系仅允许标注一次\n" +
                "2)每次存储标注都会存储此次打开该文本后的所有标注，并会覆盖上一次存储的记录");
        readme.setMovementMethod(new ScrollingMovementMethod());
    }
}
