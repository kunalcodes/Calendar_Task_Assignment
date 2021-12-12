# Calendar_Task_Assignment

Add to do list to your calendar.


# Features ✨
* Selcet any date from the calendar
* Add to do tasks on selected dates
* preview your to do list
* delete items from your list

<br/>


# Tech Stack ✨

* Kotlin
* Android Studio
* Rest API
* Retrofit
* Rxjava
* Room Database
* MVVM
* Hilt


# Dependencies 

    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.4.0'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.2'
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

    //hilt
    implementation("com.google.dagger:hilt-android:2.38.1")
    kapt("com.google.dagger:hilt-android-compiler:2.38.1")
    //Room
    implementation 'androidx.room:room-ktx:2.1.0'
    kapt 'androidx.room:room-compiler:2.1.0'

    // ViewModel
    def arch_version = '2.2.0-alpha01'
    implementation "androidx.lifecycle:lifecycle-extensions:$arch_version"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$arch_version"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:$arch_version"

    // Retrofit & OkHttp
    implementation 'com.squareup.retrofit2:retrofit:2.8.1'
    // This library is used by retrofit internally to convert json-pojo and pojo-json
    implementation 'com.squareup.retrofit2:converter-gson:2.6.1'
    //This library is used to observe the API logs, Http status code and the API response
    implementation 'com.squareup.okhttp3:logging-interceptor:4.7.2'
    //rxjava dependency for retrofit
    implementation 'com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0'

    //ViewModels delegation extentensions for activity
    implementation 'androidx.activity:activity-ktx:1.3.1'

    //RXJava and RxAndroid
    implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'
    implementation 'io.reactivex.rxjava2:rxjava:2.1.7'

    // RxJava support for Room
    implementation "androidx.room:room-rxjava2:2.3.0"
