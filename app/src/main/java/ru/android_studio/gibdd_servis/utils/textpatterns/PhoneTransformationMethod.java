package ru.android_studio.gibdd_servis.utils.textpatterns;

import android.graphics.Rect;
import android.support.annotation.StringRes;
import android.text.method.TransformationMethod;
import android.view.View;

import java.util.Objects;


/**
 * Created by SEkaterina on 10.08.2016.
 */
public class PhoneTransformationMethod implements TransformationMethod {

    private String phonePatter;


    public PhoneTransformationMethod(String phonePatter) {
        this.phonePatter = phonePatter;
    }

    @Override
    public CharSequence getTransformation(CharSequence charSequence, View view) {
        if(phonePatter == null)
            throw new IllegalArgumentException();

        return new PhoneCharSequence(charSequence);
    }

    @Override
    public void onFocusChanged(View view, CharSequence charSequence, boolean b, int i, Rect rect) {

    }

    class PhoneCharSequence implements CharSequence {

        CharSequence source;

        public PhoneCharSequence(CharSequence source) {
            this.source = source;
        }

        @Override
        public int length() {
            return source.length();
        }

        @Override
        public char charAt(int i) {
            if(Character.isDigit(phonePatter.charAt(i)))
                return source.charAt(i);
            else
                return phonePatter.charAt(i);
        }

        @Override
        public CharSequence subSequence(int i, int i1) {
            return source.subSequence(i, i1);
        }
    }
}
