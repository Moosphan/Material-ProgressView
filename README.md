# Material-ProgressView

[ ![Download](https://api.bintray.com/packages/moosphon/maven/Material-ProgressView/images/download.svg) ](https://bintray.com/moosphon/maven/Material-ProgressView/_latestVersion)

A beautiful and simple used progress view for Android.It provides two different styles to display the progress. You can use it like this：



## Quick start

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
     compile 'com.moos:Material-ProgressView:1.0.0'
     ```

     ​

- Maven:

  ```
  <dependency>
    <groupId>com.moos</groupId>
    <artifactId>Material-ProgressView</artifactId>
    <version>1.0.0</version>
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
  ```

  ​

- In java:

  ```
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
  ```



## API

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

   | Attribute    | Description                                   |
   | ------------ | --------------------------------------------- |
   | isFilled     | fill the progress inner space or not          |
   | circleBroken | the circle has loophole or not(circle or arc) |

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
      public void onProgressUpdate(float progress) {
          int progressInt = (int) progress;
          textView.setText(String.valueOf(progress)+"%");
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

  //special for HorizontalProgressView
  void setProgressCornerRadius(int radius);
  void setProgressTextPaddingBottom(int offset);

  ```


## Sample

please wait to update...

## Thanks to

[HenCoder](http://hencoder.com/)

## About me

<http://moos.club/>

Buy  me a coffee:



![coffee](https://github.com/Moosphan/Material-ProgressView/blob/master/MaterialProgressView-master/image/a_coffee.jpg)



## License

```
Copyright 2018 moosphon

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

