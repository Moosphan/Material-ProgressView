### 概述

本开源主要实现了一款精美、优雅的加载控件。她目前有两种类型：弧形加载（CircleProgressView）和水平加载（HorizontalProgressView），同时，你可以为她设置颜色渐变效果。让我们先来看看效果：

![总体效果](http://upload-images.jianshu.io/upload_images/5256969-c0d41e609a8d6f91.jpg?imageMogr2/auto-orient/strip)


传送门地址：<https://github.com/Moosphan/Material-ProgressView>

欢迎大家献上宝贵的star和issue，我将继续努力完善它，也欢迎大家和我一起来优化它的功能，有很多处理不当的地方希望指出来，谢谢🙏，后续将提升它的定制性和动画等效果。

再来看看细节的效果图：

![细节效果](http://upload-images.jianshu.io/upload_images/5256969-e2e823ddf512fbf8.jpg?imageMogr2/auto-orient/strip)



下面我们来看看该如何使用这款控件。

### 快速开始

- Gradle:

  build.gradle:

  ```
  compile 'com.moos:Material-ProgressView:1.0.5'
  ```

  ​

- Maven:

  ```
  <dependency>
    <groupId>com.moos</groupId>
    <artifactId>Material-ProgressView</artifactId>
    <version>1.0.5</version>
    <type>pom</type>
  </dependency>
  ```


- 通过xml文件来设置:

  ```
  <com.moos.library.CircleProgressView
          android:id="@+id/progressView_circle"
          android:layout_width="240dp"
          android:layout_height="240dp"
          android:layout_marginTop="12dp"
          app:start_progress="0"
          app:end_progress="60"
          app:start_color="@color/purple_end"
          app:end_color="@color/purple_start"
          app:circleBroken="true"
          app:isTracked="true"
          app:track_width="26dp"/>

  <com.moos.library.HorizontalProgressView
        android:id="@+id/progressView_horizontal"
        android:layout_width="320dp"
        android:layout_height="100dp"
        android:layout_marginBottom="40dp"
        android:layout_marginTop="36dp"
        app:start_color="@color/red_start"
        app:end_color="@color/red_end"
        app:track_width="12dp"
        app:end_progress="60"
        app:progressTextColor="#696969"
        app:corner_radius="12dp"
        app:isTracked="true"
        app:trackColor="#f4f4f4"/>
  ```

  ​

- 通过java代码动态初始化属性:

  ```

          //CircleProgressView
          CircleProgressView circleProgressView = (CircleProgressView) view.findViewById(R.id.progressView_circle);
          circleProgressView.setStartProgress(0);
          circleProgressView.setEndProgress(80);
          circleProgressView.setStartColor(Color.parseColor("#FF8F5D"));
          circleProgressView.setEndColor(Color.parseColor("#F54EA2"));
          circleProgressView.setCircleBroken(true);
          circleProgressView.setTrackWidth(20);
          circleProgressView.setProgressDuration(2000);
          circleProgressView.setTrackEnabled(true);
          circleProgressView.setFillEnabled(false);
          circleProgressView.startProgressAnimation();
         
          //HorizontalProgressView
          HorizontalProgressView circleProgressView = (HorizontalProgressView)              view.findViewById(R.id.progressView_horizontal);
          horizontalProgressView.setStartProgress(0);
          horizontalProgressView.setEndProgress(80);
          horizontalProgressView.setStartColor(Color.parseColor("#FF8F5D"));
          horizontalProgressView.setEndColor(Color.parseColor("#F54EA2"));
          horizontalProgressView.setTrackWidth(30);
          horizontalProgressView.setProgressDuration(2000);
          horizontalProgressView.setTrackEnabled(true);
          horizontalProgressView.setProgressCornerRadius(20);
          horizontalProgressView.setProgressTextPaddingBottom(12);
          horizontalProgressView.startProgressAnimation();

  ```

- 此外，如果你想要让水平加载控件上方的百分比数值跟随其运动，可以这样设置：

  1. 通过xml设置:

  ```
          app:textMovedEnable="true"
  ```
  2. 通过java代码:
  ```
          horizontalProgressView.setProgressTextMoved(true);
  ```

  然后你会看到如下效果：

  ​

  ![文字跟随](http://upload-images.jianshu.io/upload_images/5256969-0393839ea54f7bbd.jpg?imageMogr2/auto-orient/strip)

- 如果想要根据给定值来动态设置加载控件的进度值（如加载进度和上传进度等），可以通过方法`setProgress`，如：

  ```

  ......
        //上传或者下载的回调
        @Override
        public void onDownloading(float progress) {
            // 由于是动态设置进度值，无需再调用`startProgressAnimation`方法来启动进度的刷新了
            horizontalProgressView.setProgress(progress);
        }

  ......
  ```

  ​

### 相关文档

- 控件属性

1. 共有属性:

   | Attribute              | Description                                    |
   | ---------------------- | ---------------------------------------------- |
   | start_progress         | 起始进度                                       |
   | end_progress           | 终止进度                                       |
   | start_color            | 渐变效果的起始颜色                             |
   | end_color              | 渐变效果的终止颜色                             |
   | isTracked              | 是否显示轨迹背景                               |
   | track_width            | 进度条的宽度（边界宽度）                       |
   | trackColor             | 轨迹背景的颜色                                 |
   | progressTextVisibility | 是否显示进度值文本                             |
   | progressTextColor      | 进度值的颜色                                   |
   | progressTextSize       | 进度值的文本字体大小                           |
   | progressDuration       | 动画时长                                       |
   | animateType            | 动画类型（可以参考属性动画的TimeInterpolator） |

2. **CircleProgressView**的特有属性:

   | Attribute    | Description              |
   | ------------ | ------------------------ |
   | isFilled     | 是否内部填充             |
   | circleBroken | 是选择圆形还是弧形进度条 |

3. **HorizontalProgressView**的特有属性:

   | Attribute           | Description                    |
   | ------------------- | ------------------------------ |
   | corner_radius       | 圆角半径                       |
   | text_padding_bottom | 文字距离view的padding          |
   | textMovedEnable     | 设置进度值是否跟随控件动画移动 |



- 回调

  ```
      /**
       * 你可以利用该回调来定制自己的progress值的UI展示
       */

      circleProgressView.setProgressViewUpdateListener(new CircleProgressView.CircleProgressUpdateListener() {
      
      @Override
      public void onCircleProgressStart(View view) {

      }

      @Override
      public void onCircleProgressUpdate(View view,float progress) {
   
          int progressInt = (int) progress;
          textView.setText(String.valueOf(progress)+"%");
      }

      @Override
      public void onCircleProgressFinished(View view) {

      }
  });

  horizontalProgressView.setProgressViewUpdateListener(new HorizontalProgressView.HorizontalProgressUpdateListener() {
      
      @Override
      public void onHorizontalProgressStart(View view) {

      }

      @Override
      public void onHorizontalProgressUpdate(View view,float progress) {
       
          int progressInt = (int) progress;
          textView.setText(String.valueOf(progress)+"%");
      }

      @Override
      public void onHorizontalProgressFinished(View view) {

      }
  });
  ```

- Methods

  ```
  //共有的方法
  void setAnimateType(@AnimateType int type);
  void setStartProgress(float startProgress);
  void setEndProgress(float endProgress);
  void setStartColor(@ColorInt int startColor);
  void setEndColor(@ColorInt int endColor);
  void setTrackWidth(int width);
  void setProgressTextSize(int size);
  void setProgressTextColor(@ColorInt int textColor);
  void setProgressDuration(int duration);
  void setTrackEnabled(boolean trackAble);
  void setTrackColor(@ColorInt int color);
  void setProgressTextVisibility(boolean visibility);
  void startProgressAnimation();
  void stopProgressAnimation();

  //CircleProgressView的特有方法
  void setCircleBroken(boolean isBroken);
  void setFillEnabled(boolean fillEnabled);

  //HorizontalProgressView的特有方法
  void setProgressCornerRadius(int radius);
  void setProgressTextPaddingBottom(int offset);

  ```


### demo下载地址

[ProgressView-sample.apk](https://www.pgyer.com/u11w)

### 更新记录

- **V1.0.1**(2018-03-16):为加载控件提供进度回调
- **V1.0.2**(2018-03-22):为水平进度条提供进度值的跟随动画
- **V1.0.3**(2018-04-12):增加弧形加载控件的刻度效果
- **V1.0.4**(2018-05-03):支持动态设置加载控件的进度值(下载/上传进度等)
- **V1.0.5**(2018-05-05):解决弧形加载控件的刻度模式初始化失效问题，详情见：[issue](https://github.com/Moosphan/Material-ProgressView/issues/3)

### 特别感谢

[HenCoder](http://hencoder.com/)

[VisualCC](https://www.uplabs.com/posts/learning-progress-interface)

### 关于我

欢迎大家提出改进建议。

Blog:<http://moos.club/>

E-mail:moosphon@gmail.com

QQ群：601924443