package com.lzj.douyin.redheart.library;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.util.Random;

/**
 * 仿抖音红心效果
 */
public class RedHeartLayout extends RelativeLayout {
    private Context mContext;
    private float[] mRandomAngle = {-25, -15, 0, 15, 25};//随机心形图片角度
    private int image_resId=R.drawable.ic_heart;//默认图片
    private int mWidth=120; //默认图片宽
    private int mHeight=120;//默认图片高


    /**
     * new 使用
     * @param context
     */
    public RedHeartLayout(Context context) {
        super(context);
        initView(context);
    }
    /**
     *  当有style自定义样式时候 使用
     * @param context
     * @param attrs
     * @param defStyleAttr
     */

    public RedHeartLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    /**
     *  布局使用
     * @param context
     * @param attrs
     */
    public RedHeartLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //自定义属性 宽、高、图片
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.HeartLayout);
        mWidth=typedArray.getInteger(R.styleable.HeartLayout_heart_width,mWidth);
        mHeight=typedArray.getInteger(R.styleable.HeartLayout_heart_height,mHeight);
        image_resId=typedArray.getResourceId(R.styleable.HeartLayout_heart_image_resId,image_resId);
        initView(context);
    }

    /**
     *  将宽高dp转化为px
     * @param context
     */
    private void initView(Context context) {
        this.mContext = context;
        this.mHeight=dip2px(mContext,mHeight);
        this.mWidth=dip2px(mContext,mWidth);
    }

    /**
     *  触摸事件处理
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //第一步,创建心形ImageView
        final ImageView imageView=new ImageView(mContext);
        imageView.setImageResource(image_resId);
        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(mWidth, mHeight);
        int xLeft=(int)event.getX()- mWidth/2;
        int yTop=(int)event.getY() - mHeight;
        layoutParams.setMargins(xLeft,yTop,0,0);
        imageView.setLayoutParams(layoutParams);
        addView(imageView);
        //第二步,添加动画效果集
        AnimatorSet animatorSet = new AnimatorSet();
                    //缩放动画，X轴2倍缩小至0.9倍
        animatorSet.play(scaleXY(imageView, "scaleX", 2f, 0.9f, 100, 0))
                   //缩放动画 , Y轴2倍缩小至0.9倍
                   .with(scaleXY(imageView, "scaleY", 2f, 0.9f, 100, 0))
                   //旋转动画 , 随机旋转角度
                   .with(rotation(imageView, 0, 0))
                   //透明度动画 , 透明度从0-1
                   .with(alpha(imageView, 0, 1, 100, 0))
                   //缩放动画 , X轴0.9倍缩小至1倍
                   .with(scaleXY(imageView, "scaleX", 0.9f, 1, 50, 150))
                   //缩放动画 , Y轴0.9倍缩小至1倍
                   .with(scaleXY(imageView, "scaleY", 0.9f, 1, 50, 150))
                   //平移动画 , Y轴从0向上移动700单位
                   .with(translationY(imageView, "translationY", 0, -700, 800, 400))
                   //透明度动画 , 透明度从1-0
                   .with(alpha(imageView, 1, 0, 400, 400))
                   //缩放动画 , X轴1倍放大至3倍
                   .with(scaleXY(imageView, "scaleX", 1, 3f, 800, 400))
                   //缩放动画 , Y轴1倍放大至3倍
                   .with(scaleXY(imageView, "scaleY", 1, 3f, 800, 400));
        animatorSet.start();
        //动画移除
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                 removeViewInLayout(imageView);
            }
        });
        return super.onTouchEvent(event);
    }

    /**
     *   X轴或Y轴 缩放
     * @param view
     * @param propertyName
     * @param from
     * @param to
     * @param time
     * @param delayTime
     * @return
     */
    public  ObjectAnimator scaleXY(View view, String propertyName, float from, float to, long time, long delayTime) {
        ObjectAnimator _scaleXY=  ObjectAnimator.ofFloat(view,propertyName,from,to);
        ////设置插值器为 匀速(补充：有加速、先加后减等)
        _scaleXY.setInterpolator(new LinearInterpolator());
        //设置开始前延迟
        _scaleXY.setStartDelay(delayTime);
        //设置动画持续时间
        _scaleXY.setDuration(time);
        _scaleXY.start();
        return _scaleXY;
    }

    /**
     *   X轴或Y轴 平移
     * @param view
     * @param from
     * @param to
     * @param time
     * @param delayTime
     * @return
     */
    public  ObjectAnimator translationY(View view,  String propertyName,float from, float to, long time, long delayTime) {
        ObjectAnimator translation = ObjectAnimator.ofFloat(view, propertyName, from, to);
        translation.setInterpolator(new LinearInterpolator());
        translation.setStartDelay(delayTime);
        translation.setDuration(time);
        return translation;
    }

    /**
     *   透明化处理
     * @param view
     * @param from
     * @param to
     * @param time
     * @param delayTime
     * @return
     */
    public  ObjectAnimator alpha(View view, float from, float to, long time, long delayTime) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", from, to);
        alpha.setInterpolator(new LinearInterpolator());
        alpha.setStartDelay(delayTime);
        alpha.setDuration(time);
        return alpha;
    }

    /**
     *  旋转
     * @param view
     * @param time
     * @param delayTime
     * @return
     */
    public ObjectAnimator rotation(View view, long time, long delayTime) {
        //随机旋转角度
        float angle= mRandomAngle[new Random().nextInt(4)];
        ObjectAnimator rotation = ObjectAnimator.ofFloat(view, "rotation", angle);
        rotation.setDuration(time);
        rotation.setStartDelay(delayTime);
        rotation.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                //抖动系数越小幅度越大 0-1
                return input;
            }
        });
        return rotation;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public  int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}

