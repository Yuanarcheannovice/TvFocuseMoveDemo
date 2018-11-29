package com.xz.tv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

import cn.caratel.lib.widget.tv.FocusAnimationUtil;
import cn.caratel.lib.widget.tv.FocusMoveViewpagerLauncherHelpler;

/**
 * @author xz
 * 首页
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFocusView();
        initView();
    }

    private void initView(){
        findViewById(R.id.am_tv1).setOnClickListener(this);

    }

    /**
     * 初始化焦点框view和监听
     */
    private void initFocusView(){
        //new 一个新的View 最为焦点框 View,统一控制焦点动画
        final View focusView = new View(this);
        //设置背景框 .9 图(带阴影效果的图片)
        focusView.setBackgroundResource(R.drawable.ico_focus_border_fillet);
        //添加到windows的布局上，windows的布局，是帧布局，正好有覆盖的效果!
        ((ViewGroup) getWindow().getDecorView()).addView(focusView);
        //监听整个界面的焦点移动
        getWindow().getDecorView().getViewTreeObserver().
                addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {

                    @Override
                    public void onGlobalFocusChanged(final View oldFocus, final View newFocus) {
                        if (oldFocus != null) {
                            //需要去掉上一个的动画
                            FocusAnimationUtil.setViewAnimatorBig(oldFocus, false, 300, 1.1f);
                        }
                        if (newFocus == null) {
                            return;
                        }
                        //显示在其他view 上面
                        newFocus.bringToFront();
                        //放大动画 是基于 焦点View的
                        FocusAnimationUtil.setViewAnimatorBig(newFocus, true, 300, 1.1f);
                        //焦点框移动动画
                        FocusAnimationUtil.focusMoveAnimatorBig(newFocus, focusView, 1.1f);
                    }
                });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.am_tv1:
                //上 下 都是 RecyclerView 的界面

                break;
        }
    }
}
