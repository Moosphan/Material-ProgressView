package com.moos.library;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

/**
 * Created by moos on 2018/3/16.
 * Material progress view in 'circle' style
 */

public class CircleProgressView extends View {

    @IntDef({ACCELERATE_DECELERATE_INTERPOLATOR, LINEAR_INTERPOLATOR, ACCELERATE_INTERPOLATOR, DECELERATE_INTERPOLATOR, OVERSHOOT_INTERPOLATOR })
    private @interface AnimateType{

    }

    /**
     * animation types supported
     */
    public static final int ACCELERATE_DECELERATE_INTERPOLATOR = 0;
    public static final int LINEAR_INTERPOLATOR = 1;
    public static final int ACCELERATE_INTERPOLATOR = 2;
    public static final int DECELERATE_INTERPOLATOR = 3;
    public static final int OVERSHOOT_INTERPOLATOR = 4;

    private static final String TAG = "Moos-Progress-View";

    /**
     * properties needed
     */
    private Context mContext;
    /**
     * the type of animation
     */
    private int mAnimateType = 0;
    /**
     * the progress of start point
     */
    private float mStartProgress = 0;
    /**
     * the progress of end point
     */
    private float mEndProgress = 60;
    /**
     * the color of start progress
     */
    private int mStartColor = getResources().getColor(R.color.light_orange);
    /**
     * the color of end progress
     */
    private int mEndColor = getResources().getColor(R.color.dark_orange);
    /**
     * has track of moving or not
     */
    private boolean trackEnabled = false;
    /**
     * filling the inner space or not
     */
    private boolean fillEnabled = false;
    /**
     * the stroke width of progress
     */
    private int mTrackWidth = 6;
    /**
     * the size of inner text
     */
    private int mProgressTextSize = 48;
    /**
     * the color of inner text
     */
    private int mProgressTextColor = getResources().getColor(R.color.colorAccent);
    /**
     * the circle of progress broken or not
     */
    private boolean circleBroken = true;
    /**
     * the color of progress track
     */
    private int mTrackColor = getResources().getColor(R.color.default_track_color);
    /**
     * the duration of progress moving
     */
    private int mProgressDuration = 1200;
    /**
     * show the inner text or not
     */
    private boolean textVisibility = true;

    /**
     * the animator of progress moving
     */
    private ObjectAnimator progressAnimator;
    /**
     * the progress of moving
     */
    private float moveProgress = 0;
    /**
     * the paint of drawing progress
     */
    private Paint progressPaint;
    /**
     * the gradient of color
     */
    private LinearGradient mShader;
    /**
     * the oval's rect shape
     */
    private RectF mOval;

    private Paint mTextPaint;
    private Interpolator mInterpolator;
    private CircleProgressUpdateListener updateListener;

    /**
     * the path of scale zone
     */
    private Path mScaleZonePath;
    /**
     * the width of each scale zone
     */
    private float mScaleZoneWidth = 6;
    /**
     * the length of each scale zone
     */
    private float mScaleZoneLength = 22;
    /**
     * the padding of scale zones
     */
    private int mScaleZonePadding = 18;
    /**
     * the shape of scale zone
     */
    private RectF mScaleZoneRect;
    /**
     * open draw the scale zone or not
     */
    private boolean isGraduated = false;
    /**
     * the radius of scale zone corner
     */
    private int mScaleZoneCornerRadius = 0;

    PathEffect pathEffect;


    public CircleProgressView(Context context) {
        super(context);
        this.mContext = context;
        obtainAttrs(context,null);
        init();
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        obtainAttrs(context,attrs);
        init();
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        obtainAttrs(context,attrs);
        init();
    }

    private void obtainAttrs(Context context, AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);

        mStartProgress = typedArray.getInt(R.styleable.CircleProgressView_start_progress,0);
        mEndProgress = typedArray.getInt(R.styleable.CircleProgressView_end_progress,60);
        mStartColor = typedArray.getColor(R.styleable.CircleProgressView_start_color, getResources().getColor(R.color.light_orange));
        mEndColor = typedArray.getColor(R.styleable.CircleProgressView_end_color,getResources().getColor(R.color.dark_orange));
        fillEnabled = typedArray.getBoolean(R.styleable.CircleProgressView_isFilled, false);
        trackEnabled = typedArray.getBoolean(R.styleable.CircleProgressView_isTracked, false);
        circleBroken = typedArray.getBoolean(R.styleable.CircleProgressView_circleBroken, true);
        mProgressTextColor = typedArray.getColor(R.styleable.CircleProgressView_progressTextColor,getResources().getColor(R.color.colorAccent));
        mProgressTextSize = typedArray.getDimensionPixelSize(R.styleable.CircleProgressView_progressTextSize,getResources().getDimensionPixelSize(R.dimen.default_progress_text_size));
        mTrackWidth = typedArray.getDimensionPixelSize(R.styleable.CircleProgressView_track_width, getResources().getDimensionPixelSize(R.dimen.default_trace_width));
        mAnimateType = typedArray.getInt(R.styleable.CircleProgressView_animateType, ACCELERATE_DECELERATE_INTERPOLATOR);
        mTrackColor = typedArray.getColor(R.styleable.CircleProgressView_trackColor, getResources().getColor(R.color.default_track_color));
        textVisibility = typedArray.getBoolean(R.styleable.CircleProgressView_progressTextVisibility, true);
        mProgressDuration = typedArray.getInt(R.styleable.CircleProgressView_progressDuration, 1200);
        mScaleZoneLength = typedArray.getDimensionPixelSize(R.styleable.CircleProgressView_scaleZone_length, getResources().getDimensionPixelSize(R.dimen.default_zone_length));
        mScaleZoneWidth = typedArray.getDimensionPixelSize(R.styleable.CircleProgressView_scaleZone_width, getResources().getDimensionPixelSize(R.dimen.default_zone_width));
        mScaleZonePadding = typedArray.getDimensionPixelSize(R.styleable.CircleProgressView_scaleZone_padding, getResources().getDimensionPixelSize(R.dimen.default_zone_padding));
        mScaleZoneCornerRadius = typedArray.getDimensionPixelSize(R.styleable.CircleProgressView_scaleZone_corner_radius, getResources().getDimensionPixelSize(R.dimen.default_zone_corner_radius));
        isGraduated = typedArray.getBoolean(R.styleable.CircleProgressView_isGraduated, false);
        moveProgress = mStartProgress;

        typedArray.recycle();
    }

    private void init(){
        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setStrokeWidth(mTrackWidth);
        mScaleZonePath = new Path();

        /**
         * if set the scale zone mode for progress view, should not let the circle be filled
         */
        drawScaleZones(isGraduated);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        drawTrack(canvas);

        //mShader = new LinearGradient(mOval.left,mOval.top,mOval.right,mOval.bottom,mStartColor,mEndColor, Shader.TileMode.CLAMP);
        progressPaint.setShader(mShader);
        updateTheTrack();
        if(fillEnabled) {
            progressPaint.setStyle(Paint.Style.FILL);
            initProgressDrawing(canvas, true);
        }else {
            progressPaint.setStyle(Paint.Style.STROKE);
            initProgressDrawing(canvas, false);

        }

        drawProgressText(canvas);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        updateTheTrack();

        mShader = new LinearGradient(mOval.left-200, mOval.top-200, mOval.right+20, mOval.bottom+20,
                mStartColor, mEndColor, Shader.TileMode.CLAMP);
        /**
         * draw the scale zone shape
         */
        mScaleZoneRect = new RectF(0,0, mScaleZoneWidth, mScaleZoneLength);
        mScaleZonePath.addRoundRect(mScaleZoneRect, mScaleZoneCornerRadius, mScaleZoneCornerRadius, Path.Direction.CW);



    }

    /**
     * draw the track(moving background)
     * @param canvas mCanvas
     */
    private void drawTrack(Canvas canvas){
        if(trackEnabled){
            progressPaint.setShader(null);
            progressPaint.setColor(mTrackColor);
            if(fillEnabled) {
                progressPaint.setStyle(Paint.Style.FILL);
                initTrack(canvas, true);
            }else {
                progressPaint.setStyle(Paint.Style.STROKE);
                initTrack(canvas, false);

            }
        }
    }

    /**
     * draw the each scale zone, you can set round corner fot it
     * @link
     */
    private void drawScaleZones(boolean isGraduated){

        Log.e(TAG, "init======isGraduated>>>>>"+isGraduated );
        if(isGraduated){
            if(pathEffect == null){

                pathEffect = new PathDashPathEffect(mScaleZonePath, mScaleZonePadding,0,PathDashPathEffect.Style.ROTATE);
            }
            progressPaint.setPathEffect(pathEffect);
        }else {
            pathEffect = null;
            progressPaint.setPathEffect(null);
        }

    }

    /**
     * init for track view
     * @param canvas mCanvas
     * @param isFilled whether filled or not
     */
    private void initTrack(Canvas canvas, boolean isFilled){
        if(circleBroken){
            canvas.drawArc(mOval, 135, 270, isFilled, progressPaint);
        }else {
            canvas.drawArc(mOval, 90, 360, isFilled, progressPaint);
        }
    }

    /**
     * draw the progress text
     * @param canvas mCanvas
     */
    private void drawProgressText(Canvas canvas){

        if(textVisibility){
            mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mTextPaint.setStyle(Paint.Style.FILL);
            mTextPaint.setTextSize(mProgressTextSize);
            mTextPaint.setColor(mProgressTextColor);
            mTextPaint.setTextAlign(Paint.Align.CENTER);

            String progressText = ((int) moveProgress) + "%";
            float x = (getWidth() + getPaddingLeft() - getPaddingRight()) / 2;
            float y = (getHeight() + getPaddingTop() - getPaddingBottom() - (mTextPaint.descent() + mTextPaint.ascent())) / 2;
            canvas.drawText(progressText, x , y, mTextPaint);

        }

    }

    /**
     * set progress animate type
     * @param type anim type
     */
    public void setAnimateType(@AnimateType int type){
        this.mAnimateType = type;
        setObjectAnimatorType(type);
    }

    /**
     * set object animation type by received
     * @param animatorType object anim type
     */
    private void setObjectAnimatorType(int animatorType){
        Log.e(TAG, "AnimatorType>>>>>>"+animatorType );
        switch (animatorType){
            case ACCELERATE_DECELERATE_INTERPOLATOR:

                if(mInterpolator!=null){
                    mInterpolator = null;
                }

                mInterpolator = new AccelerateDecelerateInterpolator();
                //progressAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                break;

            case LINEAR_INTERPOLATOR:

                if(mInterpolator!=null){
                    mInterpolator = null;
                }
                mInterpolator = new LinearInterpolator();
                //progressAnimator.setInterpolator(new LinearInterpolator());
                break;

            case ACCELERATE_INTERPOLATOR:

                if(mInterpolator!=null){
                    mInterpolator = null;
                    mInterpolator = new AccelerateInterpolator();
                }
                //progressAnimator.setInterpolator(new AccelerateInterpolator());
                break;

            case DECELERATE_INTERPOLATOR:

                if(mInterpolator!=null){
                    mInterpolator = null;
                }
                mInterpolator = new DecelerateInterpolator();
                //progressAnimator.setInterpolator(new DecelerateInterpolator());
                break;

            case OVERSHOOT_INTERPOLATOR:

                if(mInterpolator!=null){
                    mInterpolator = null;
                }
                mInterpolator = new OvershootInterpolator();
                //progressAnimator.setInterpolator(new OvershootInterpolator());
                break;
        }
    }

    /**
     * set move progress
     * @param progress progress of moving
     */
    public void setProgress(float progress){
        this.moveProgress = progress;
        refreshTheView();
    }

    public float getProgress(){
        return this.moveProgress;
    }

    /**
     * set start progress
     * @param startProgress start progress
     */
    public void setStartProgress(float startProgress){
        if(startProgress < 0 || startProgress > 100){
            throw new IllegalArgumentException("Illegal progress value, please change it!");
        }
        this.mStartProgress = startProgress;
        this.moveProgress = mStartProgress;
        refreshTheView();
    }

    /**
     * set end progress
     * @param endProgress end progress
     */
    public void setEndProgress(float endProgress){
        if(endProgress < 0 || endProgress > 100){
            throw new IllegalArgumentException("Illegal progress value, please change it!");
        }
        this.mEndProgress = endProgress;
        refreshTheView();

    }

    /**
     * set start color
     * @param startColor start point color
     */
    public void setStartColor(@ColorInt int startColor){
        this.mStartColor = startColor;
        updateTheTrack();
        mShader = new LinearGradient(mOval.left-200, mOval.top-200, mOval.right+20, mOval.bottom+20,
                mStartColor, mEndColor, Shader.TileMode.CLAMP);
        refreshTheView();
    }

    /**
     * set end color
     * @param endColor end point color
     */
    public void setEndColor(@ColorInt int endColor){
        this.mEndColor = endColor;
        updateTheTrack();
        mShader = new LinearGradient(mOval.left-200, mOval.top-200, mOval.right+20, mOval.bottom+20,
                mStartColor, mEndColor, Shader.TileMode.CLAMP);
        refreshTheView();
    }

    /**
     * set the width of progress stroke
     * @param width stroke
     */
    public void setTrackWidth(int width){
        this.mTrackWidth = Utils.dp2px(mContext, width);
        progressPaint.setStrokeWidth(width);
        updateTheTrack();
        refreshTheView();
    }

    /**
     * set text size for inner text
     * @param size text size
     */
    public void setProgressTextSize(int size){
        mProgressTextSize = Utils.sp2px(mContext, size);
        refreshTheView();
    }

    /**
     * set text color for progress text
     * @param textColor
     */
    public void setProgressTextColor(@ColorInt int textColor){
        this.mProgressTextColor = textColor;
    }

    /**
     * set duration of progress moving
     * @param duration
     */
    public void setProgressDuration(int duration){
        this.mProgressDuration = duration;
    }

    /**
     * set track for progress
     * @param trackAble whether track or not
     */
    public void setTrackEnabled(boolean trackAble){
        this.trackEnabled = trackAble;
        refreshTheView();
    }

    /**
     * set track color for progress background
     * @param color bg color
     */
    public void setTrackColor(@ColorInt int color){
        this.mTrackColor = color;
        refreshTheView();
    }

    /**
     * set content for progress inner space
     * @param fillEnabled whether filled or not
     */
    public void setFillEnabled(boolean fillEnabled){
        this.fillEnabled = fillEnabled;
        refreshTheView();
    }

    /**
     * set the broken circle for progress
     * @param isBroken the circle broken or not
     */
    public void setCircleBroken(boolean isBroken){
        this.circleBroken = isBroken;
        refreshTheView();
    }

    /**
     * set the scale zone type for progress view
     * @param isGraduated have scale zone or not
     * todo:
     * 1. deal with the multi views can not works situation
     * 2. deal the init not work   [solved]
     */
    public void setGraduatedEnabled(final boolean isGraduated){
        this.isGraduated = isGraduated;
        post(new Runnable() {
            @Override
            public void run() {
                drawScaleZones(isGraduated);
            }
        });

    }

    /**
     * set the scale zone width for it
     * @param zoneWidth each zone 's width
     */
    public void setScaleZoneWidth(float zoneWidth){
        this.mScaleZoneWidth = Utils.dp2px(mContext, zoneWidth);
    }

    /**
     * set the scale zone length for it
     * @param zoneLength each zone 's length
     */
    public void setScaleZoneLength(float zoneLength){
        this.mScaleZoneLength = Utils.dp2px(mContext, zoneLength);
    }

    /**
     * set each zone's distance
     * @param zonePadding distance
     */
    public void setScaleZonePadding(int zonePadding){
        this.mScaleZonePadding = Utils.dp2px(mContext, zonePadding);
    }

    /**
     * set corner radius for each zone
     * @param cornerRadius round rect zone's corner
     */
    public void setScaleZoneCornerRadius(int cornerRadius){
        this.mScaleZoneCornerRadius = Utils.dp2px(mContext, cornerRadius);
    }

    /**
     * set the visibility for progress inner text
     * @param visibility text visible or not
     */
    public void setProgressTextVisibility(boolean visibility){
        this.textVisibility = visibility;
    }

    /**
     * start the progress's moving
     */
    public void startProgressAnimation(){
        progressAnimator = ObjectAnimator.ofFloat(this,"progress",mStartProgress, mEndProgress);
        Log.e(TAG, "progressDuration: "+ mProgressDuration);
        progressAnimator.setInterpolator(mInterpolator);
        progressAnimator.setDuration(mProgressDuration);
        progressAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue("progress");
                if(updateListener != null){
                    updateListener.onCircleProgressUpdate(CircleProgressView.this, progress);
                }

            }
        });
        progressAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if(updateListener != null){
                    updateListener.onCircleProgressStart(CircleProgressView.this);
                }
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if(updateListener != null){
                    updateListener.onCircleProgressFinished(CircleProgressView.this);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        progressAnimator.start();
    }

    /**
     * stop the progress moving
     */
    public void stopProgressAnimation(){
        if(progressAnimator!=null){
            progressAnimator.cancel();
            progressAnimator = null;
        }
    }

    /**
     * refresh the layout
     */
    private void refreshTheView() {
        invalidate();
        requestLayout();
    }

    /**
     * update the oval progress track
     */
    private void updateTheTrack() {
        if(mOval != null){
            mOval = null;
        }
        mOval = new RectF(getPaddingLeft() + mTrackWidth, getPaddingTop() + mTrackWidth,
                getWidth() - getPaddingRight() - mTrackWidth,
                getHeight() - getPaddingBottom() - mTrackWidth);

    }

    /**
     * init the circle progress drawing
     * @param canvas mCanvas
     * @param isFilled filled or not
     */
    private void initProgressDrawing(Canvas canvas, boolean isFilled){

        if(circleBroken){
            Log.e(TAG, "circleBroken>>>>>>"+"yes" );
            canvas.drawArc(mOval, 135 + mStartProgress*2.7f, (moveProgress - mStartProgress) * 2.7f, isFilled, progressPaint);

        }else {
            Log.e(TAG, "circleBroken>>>>>>"+"no" );
            canvas.drawArc(mOval, 270 + mStartProgress*3.6f , (moveProgress - mStartProgress) * 3.6f, isFilled, progressPaint);
        }

    }

    /**
     * the interface to help get the value of progress moving
     */
    public interface CircleProgressUpdateListener{
        void onCircleProgressStart(View view);
        void onCircleProgressUpdate(View view, float progress);
        void onCircleProgressFinished(View view);
    }

    /**
     * set the progress update listener for progress view
     * @param listener update listener
     */
    public void setProgressViewUpdateListener(CircleProgressUpdateListener listener){
        this.updateListener = listener;
    }






}
