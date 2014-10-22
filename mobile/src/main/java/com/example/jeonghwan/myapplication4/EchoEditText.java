package com.example.jeonghwan.myapplication4;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * Created by jeonghwan on 14. 10. 22..
 */
public class EchoEditText extends EditText {

    public EchoEditText(Context context) {
        super(context);
    }

    public EchoEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EchoEditText(Context context, AttributeSet attrs, int defStyles) {
        super(context, attrs, defStyles);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.d("tag1", "onKeyup()");
        Log.d("tag1", "onKeyUp() " + (char)event.getUnicodeChar());
        append("" + (char)event.getUnicodeChar());

        return super.onKeyUp(keyCode, event);
    }
}
