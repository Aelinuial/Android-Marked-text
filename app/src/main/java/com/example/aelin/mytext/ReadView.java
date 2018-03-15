package com.example.aelin.mytext;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.text.Selection;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by aelin on 2017/11/26.
 */

public class ReadView extends EditText {
    public ReadView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ReadView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReadView(Context context) {
        super(context);
        initialize();
    }

    private int off; //字符串的偏移值

    private void initialize() {
        setGravity(Gravity.TOP);
        setBackgroundColor(Color.WHITE);
    }

    //设置代码可编辑状态
    @Override
    public boolean getDefaultEditable() {
        return false;
    }

    /*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        Layout layout = getLayout();
        int line = 0;
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                line = layout.getLineForVertical(getScrollY()+ (int)event.getY());
                off = layout.getOffsetForHorizontal(line, (int)event.getX());
                Selection.setSelection(getEditableText(), off);
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                line = layout.getLineForVertical(getScrollY()+(int)event.getY());
                int curOff = layout.getOffsetForHorizontal(line, (int)event.getX());
                Selection.setSelection(getEditableText(), off, curOff);
                break;
        }
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        resize();
    }

    public int resize() {
        CharSequence oldContent = getText();
        CharSequence newContent = oldContent.subSequence(0, getCharNum());
        setText(newContent);
        return oldContent.length() - newContent.length();
    }

    //获取当前页总字数
    public int getCharNum() {
        return getLayout().getLineEnd(getLineNum());
    }


    //获取当前页总行数
    public int getLineNum() {
        Layout layout = getLayout();
        int topOfLastLine = getHeight() - getPaddingTop() - getPaddingBottom() - getLineHeight();
        return layout.getLineForVertical(topOfLastLine);
    }
    */
}
