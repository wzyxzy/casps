package com.hj.casps.ui;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;

import java.util.ArrayList;

public class CLEditText extends android.support.v7.widget.AppCompatEditText {
    private ArrayList<TextWatcher> mListeners = null;

    public CLEditText(Context ctx) {
        super(ctx);
    }

    public CLEditText(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
    }

    public CLEditText(Context ctx, AttributeSet attrs, int defStyle) {
        super(ctx, attrs, defStyle);
    }

    @Override
    public void addTextChangedListener(TextWatcher watcher) {
        if (mListeners == null) {
            mListeners = new ArrayList<TextWatcher>();
        }
        mListeners.add(watcher);

        super.addTextChangedListener(watcher);
    }

    @Override
    public void removeTextChangedListener(TextWatcher watcher) {
        if (mListeners != null) {
            int i = mListeners.indexOf(watcher);
            if (i >= 0) {
                mListeners.remove(i);
            }
        }

        super.removeTextChangedListener(watcher);
    }

    public void clearTextChangedListeners() {
        if (mListeners != null) {
            for (TextWatcher watcher : mListeners) {
                super.removeTextChangedListener(watcher);
            }

            mListeners.clear();
            mListeners = null;
        }
    }
}  