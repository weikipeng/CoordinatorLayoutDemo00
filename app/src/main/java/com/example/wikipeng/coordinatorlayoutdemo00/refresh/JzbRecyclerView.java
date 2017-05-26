package com.example.wikipeng.coordinatorlayoutdemo00.refresh;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by WikiPeng on 2017/3/3 10:39.
 */
public class JzbRecyclerView extends RecyclerView implements ITouchInterceptor {
    protected LinearLayoutManager mLinearLayoutManager;
    protected int                 mFirstVisiblePosition;
    protected int                 mLastVisiblePosition;
    protected boolean             isWebViewInterceptDown;
    protected boolean             isWebViewInterceptUp;


    //----------------------------------------------------------------
    //--------------------------------注释--------------------------------
    //----------------------------------------------------------------
    private float mInterceptTouchDownX = -1;
    private float mInterceptTouchDownY = -1;

    protected boolean isOnlyWebView;

    public JzbRecyclerView(Context context) {
        super(context);
    }

    public JzbRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public JzbRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void updateParam() {
        if (mLinearLayoutManager == null) {
            LayoutManager layoutManager = getLayoutManager();

            if (layoutManager instanceof LinearLayoutManager) {
                mLinearLayoutManager = (LinearLayoutManager) layoutManager;
            }
        }

        if (mLinearLayoutManager == null) {
            return;
        }

        mFirstVisiblePosition = mLinearLayoutManager.findFirstVisibleItemPosition();
        mLastVisiblePosition = mLinearLayoutManager.findLastVisibleItemPosition();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        updateParam();

        isWebViewInterceptUp = false;
        isWebViewInterceptDown = false;
        isOnlyWebView = mFirstVisiblePosition == 0 && mLastVisiblePosition == 0;

        if (mFirstVisiblePosition == 0) {
            ViewHolder viewHolder = findViewHolderForAdapterPosition(mFirstVisiblePosition);
//            if (viewHolder instanceof VHWebView) {
//                isWebViewInterceptUp = ((VHWebView) (viewHolder)).isInterceptMoveUp(ev);
//                isWebViewInterceptDown = ((VHWebView) (viewHolder)).isInterceptMoveDown(ev);
//
//                ((VHWebView) (viewHolder)).setOnlyWebView(isOnlyWebView);
//
//                if (!isOnlyWebView) {
//                    ((VHWebView) viewHolder).scrollToBottom();
//                }
//            }
        }

//        FOpenLog.e("jzbFocus debug JzbRecyclerView===> dispatchTouchEvent " + JzbWebView.getEventName(ev) + "\n" +
//                "firstVisiblePosition:" + mFirstVisiblePosition
//                + "______lastVisiblePosition:" + mLastVisiblePosition);

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mInterceptTouchDownX = event.getRawX();
                mInterceptTouchDownY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (mInterceptTouchDownX == -1) {
                    mInterceptTouchDownX = (int) event.getRawX();
                }
                if (mInterceptTouchDownY == -1) {
                    mInterceptTouchDownY = (int) event.getRawY();
                }

                if (isOnlyWebView) {
                    int interceptTouchMoveDistanceY = (int) (event.getRawY() - mInterceptTouchDownY);
                    if ((interceptTouchMoveDistanceY > 0 && isWebViewInterceptDown)
                            ||
                            (interceptTouchMoveDistanceY < 0 && isWebViewInterceptUp)) {
//                        FOpenLog.e("jzbFocus debug JzbRecyclerView 放过 ——————-===> onInterceptTouchEvent " + JzbWebView.getEventName(event));
                        return false;
                    }
                }

                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                // 重置
                mInterceptTouchDownX = -1;
                mInterceptTouchDownY = -1;
                break;
        }

//        FOpenLog.e("jzbFocus debug JzbRecyclerView===> onInterceptTouchEvent " + JzbWebView.getEventName(event)
//                + "<<<<<<<<<<<<<<<<<<<<<<<<拦截");

        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
//        FOpenLog.e("jzbFocus debug JzbRecyclerView===> onTouchEvent " + JzbWebView.getEventName(e));
        return super.onTouchEvent(e);
    }

    @Override
    public boolean isInterceptTouchEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean isInterceptMoveDown(MotionEvent e) {
        updateParam();
        if (mFirstVisiblePosition == 0 && mLastVisiblePosition == 0) {
            ViewHolder viewHolder = findViewHolderForAdapterPosition(mFirstVisiblePosition);
//            if (viewHolder instanceof VHWebView) {
//                return ((VHWebView) (viewHolder)).isInterceptMoveDown(e);
//            }
        }

        return false;
    }

    @Override
    public boolean isInterceptMoveUp(MotionEvent e) {
        return false;
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);

        if (mFirstVisiblePosition == 0) {
            ViewHolder viewHolder = findViewHolderForAdapterPosition(mFirstVisiblePosition);
//            if (viewHolder instanceof VHWebView) {
//                final WebView webView = ((VHWebView) viewHolder).getWebView();
//                switch (state) {
//                    case SCROLL_STATE_IDLE:
//                        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//                        webView.invalidate();
//                        webView.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//                                webView.invalidate();
//                            }
//                        }, 300);
//
////                        FOpenLog.e("jzbFocus debug JzbRecyclerView===> onScrollStateChanged \nSCROLL_STATE_IDLE " +
////                                "firstVisiblePosition:" + mFirstVisiblePosition
////                                + "______lastVisiblePosition:" + mLastVisiblePosition);
//
//                        break;
//                    case SCROLL_STATE_DRAGGING:
//                        webView.invalidate();
//                        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//                        webView.invalidate();
////                        FOpenLog.e("jzbFocus debug JzbRecyclerView===> onScrollStateChanged \nSCROLL_STATE_DRAGGING " +
////                                "firstVisiblePosition:" + mFirstVisiblePosition
////                                + "______lastVisiblePosition:" + mLastVisiblePosition);
//
//                        break;
//                    case SCROLL_STATE_SETTLING:
//                        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
////                        FOpenLog.e("jzbFocus debug JzbRecyclerView===> onScrollStateChanged \n SCROLL_STATE_SETTLING " +
////                                "firstVisiblePosition:" + mFirstVisiblePosition
////                                + "______lastVisiblePosition:" + mLastVisiblePosition);
//
//                        break;
//                }
//            }
        }
    }
}
