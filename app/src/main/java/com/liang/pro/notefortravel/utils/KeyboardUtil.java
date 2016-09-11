package com.liang.pro.notefortravel.utils;


import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import com.liang.pro.notefortravel.R;

public class KeyboardUtil {  
        private KeyboardView keyboardView;  
        private Keyboard k;// ���ּ���  
        private EditText ed;
        public KeyboardUtil(Activity act, Context ctx, EditText edit) {  
                this.ed = edit;
                k = new Keyboard(ctx, R.xml.symbols);
                keyboardView = (KeyboardView) act.findViewById(R.id.keyboard_view);  
                keyboardView.setKeyboard(k);  
                keyboardView.setEnabled(true);  
                keyboardView.setPreviewEnabled(true);
                keyboardView.setVisibility(View.VISIBLE);
                keyboardView.setOnKeyboardActionListener(listener);  
        }  
        private OnKeyboardActionListener listener = new OnKeyboardActionListener() {  
                @Override  
                public void swipeUp() {  
                }  
   
                @Override  
                public void swipeRight() {  
                }  
   
                @Override  
                public void swipeLeft() {  
                }  
   
                @Override  
                public void swipeDown() {  
                }  
   
                @Override  
                public void onText(CharSequence text) {  
                }  
   
                @Override  
                public void onRelease(int primaryCode) {  
                }  
   
                @Override  
                public void onPress(int primaryCode) {  
                }  
                //һЩ������������codes�ǹ̶��ı�����ɡ����˵�
                @Override  
                public void onKey(int primaryCode, int[] keyCodes) {  
                        Editable editable = ed.getText();  
                        int start = ed.getSelectionStart();  
                        if (primaryCode == Keyboard.KEYCODE_DELETE) {// ����  
                                if (editable != null && editable.length() > 0) {  
                                        if (start > 0) {  
                                                editable.delete(start - 1, start);  
                                        }  
                                }  
                        }else if (primaryCode == 4896) {// ��� 
                        	editable.clear();
                        } else { //��Ҫ������������ڱ༭���� 
                                editable.insert(start, Character.toString((char) primaryCode));  
                        }  
                }  
        };  
       
    public void showKeyboard() {  
        int visibility = keyboardView.getVisibility();  
        if (visibility == View.GONE || visibility == View.INVISIBLE) {  
            keyboardView.setVisibility(View.VISIBLE);  
        }  
    }  
}  
