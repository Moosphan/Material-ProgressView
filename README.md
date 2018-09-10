# Material-ProgressView

[ ![Download](https://api.bintray.com/packages/moosphon/maven/Material-ProgressView/images/download.svg) ](https://bintray.com/moosphon/maven/Material-ProgressView/_latestVersion) [![GitHub issues](https://img.shields.io/github/issues/Moosphan/Material-ProgressView.svg)](https://github.com/Moosphan/Material-ProgressView/issues) [![GitHub stars](https://img.shields.io/github/stars/Moosphan/Material-ProgressView.svg)](https://github.com/Moosphan/Material-ProgressView/stargazers) [![GitHub forks](https://img.shields.io/github/forks/Moosphan/Material-ProgressView.svg)](https://github.com/Moosphan/Material-ProgressView/network) [![GitHub license](https://img.shields.io/github/license/Moosphan/Material-ProgressView.svg)](https://github.com/Moosphan/Material-ProgressView/blob/master/LICENSE)



A beautiful and simple used progress view for Android.It provides two different styles to display the progress. You can achieve a gradual change by setting the start color and the end color. You can use it like this：

![example](https://github.com/Moosphan/Material-ProgressView/blob/master/MaterialProgressView-master/image/beauty.gif)

More details on CircleProgressView and HorizontalProgressView:

![example](https://github.com/Moosphan/Material-ProgressView/blob/master/MaterialProgressView-master/image/progressview.gif)

## Usage
>**English**|**[中文](https://github.com/Moosphan/Material-ProgressView/blob/master/MaterialProgressView-master/README_CH.md)**
### Quick start

- Gradle:

  1. Project —>build.gradle:

     ```
     allprojects {
         repositories {
             maven { url "https://maven.google.com" }
             jcenter()
         }
     }
     ```

  2. Moudle—>build.gradle:

     ```
     compile 'com.moos:Material-ProgressView:1.0.6'
     ```

     ​

- Maven:

  ```
  <dependency>
    <groupId>com.moos</groupId>
    <artifactId>Material-ProgressView</artifactId>
    <version>1.0.6</version>
    <type>pom</type>
  </dependency>
  ```

- In xml:

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

- In java:

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

- Or if you want to let the progress value text moved with `HorizontalProgressView` progress animation, you can use it like this:

  1. in xml:
  ```
          app:textMovedEnable="true"
  ```
  2. in java:
  ```
          horizontalProgressView.setProgressTextMoved(true);
  ```

  and you will see the effect like this:

  ![example](https://github.com/Moosphan/Material-ProgressView/blob/master/MaterialProgressView-master/image/moved_text.gif)

- If you want to set the  progressView's value in dynamic state, like `downloading`or`uploading` files. You can use method `setProgress`:
```
......

      //downloading or uploading call back
      @Override
      public void onDownloading(float progress) {
          // you don't need to use `startProgressAnimation` method
          horizontalProgressView.setProgress(progress);
      }

......

```


### API

- Properties

1. Common attributes:

   | Attribute              | Description                                                  |
   | ---------------------- | ------------------------------------------------------------ |
   | start_progress         | the progress you wanted to start with                        |
   | end_progress           | the progress you wanted to finish                            |
   | start_color            | the start color of gradient ramp                             |
   | end_color              | the end color of gradient ramp                               |
   | isTracked              | display the progress track or not                            |
   | track_width            | the width of progress stroke border                          |
   | trackColor             | the color of progress background                             |
   | progressTextVisibility | display or hide the progress value text                      |
   | progressTextColor      | the color of progress text color                             |
   | progressTextSize       | size of progress text                                        |
   | progressDuration       | the duration of progress animating from start progress to end progress |
   | animateType            | type of progress animation moving                            |

2. Unique attributes for **CircleProgressView**:

   | Attribute               | Description                                   |
   | ----------------------- | --------------------------------------------- |
   | isFilled                | fill the progress inner space or not          |
   | circleBroken            | the circle has loophole or not(circle or arc) |
   | isGraduated             | Start  the scale zone border mode or not      |
   | scaleZone_width         | Each scale zone's width                       |
   | scaleZone_length        | Each scale zone's length                      |
   | scaleZone_corner_radius | scale zone's rect corner radius               |
   | scaleZone_padding       | the padding between two scale zones           |

3. Unique attributes for **HorizontalProgressView**:

   | Attribute           | Description                                        |
   | ------------------- | -------------------------------------------------- |
   | corner_radius       | the radius of progress four corners                |
   | text_padding_bottom | the distance offset between text and progress view |



- Call back

  ```
  /**
       * you can use this callback to customize your own progress text UI, like pursuit movement.
       */

      circleProgressView.setProgressViewUpdateListener(new CircleProgressView.CircleProgressUpdateListener() {
      
      @Override
      public void onCircleProgressStart(View view) {

      }

      @Override
      public void onCircleProgressUpdate(View view,float progress) {
          /**
         * you can detail with progressViews' animate event and customize their animate order
         */
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
        /**
         * you can detail with progressViews' animate event and customize their animate order
         */
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
  //common methods
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

  //special for CircleProgressView
  void setCircleBroken(boolean isBroken);
  void setFillEnabled(boolean fillEnabled);
  void setGraduatedEnabled(boolean isGraduated);
  void setScaleZoneWidth(float zoneWidth);
  void setScaleZoneLength(float zoneLength);
  void setScaleZonePadding(int zonePadding);
  void setScaleZoneCornerRadius(int cornerRadius);

  //special for HorizontalProgressView
  void setProgressCornerRadius(int radius);
  void setProgressTextPaddingBottom(int offset);

  ```


## Sample

[ProgressView-sample.apk](https://www.pgyer.com/u11w)

## Update log

- **V1.0.1**(2018-03-16): Update the interface of `HorizontalProgressUpdateListener` and `CircleProgressUpdateListener`, add two methods to get back the animation state.
- **V1.0.2**(2018-03-22): let the progress text can move with the view animation.
- **V1.0.3**(2018-04-12): add the graduated effect for circle progress view.
- **V1.0.4**(2018-05-03): update the progress view with `downloading` or `uploading`.
- **V1.0.5**(2018-05-05): sloved the problem that graduated style of `CircleProgressView` init failed, details in [issue](https://github.com/Moosphan/Material-ProgressView/issues/3).
- **V1.0.6**(2018-09-10): solved the crash problem that  `setStartColor` before calling  `setTrackWidth` .

## Thanks to

[HenCoder](http://hencoder.com/)

[VisualCC](https://www.uplabs.com/posts/learning-progress-interface)

## About me

>Welcome to improve it with me and give me some issues.

>E-mail:moosphon@gmail.com

>Twitter:[![Twitter](https://img.shields.io/twitter/url/https/github.com/Moosphan/Material-ProgressView.svg?style=social)](https://twitter.com/intent/tweet?text=Wow:&url=https%3A%2F%2Fgithub.com%2FMoosphan%2FMaterial-ProgressView)

>Buy  me a coffee:



>![coffee](https://github.com/Moosphan/Material-ProgressView/blob/master/MaterialProgressView-master/image/a_coffee.png)


## License

```
Copyright (c) 2018 moosphon

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

