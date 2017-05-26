package com.example.wikipeng.coordinatorlayoutdemo00.refresh;

import android.view.MotionEvent;

/**
 * Created by WikiPeng on 2017/3/13 14:12.
 */
public interface ITouchInterceptor {
    boolean isInterceptTouchEvent(MotionEvent e);
    boolean isInterceptMoveDown(MotionEvent e);
    boolean isInterceptMoveUp(MotionEvent e);
}
