# Environment

* Android Studio 2.x
* SDK Manager 需要安装 Android Support Repository

# Steps

* 新建一個Android專案
* 不需要Activity (Add No Activity)
* 配置build.gradle (Module: app)

```
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:23.3.0'
    //引入uiautomator
    androidTestCompile 'com.android.support.test.uiautomator:uiautomator-v18:2.1.0'
}
```

* 修改gradle後，需要同步一下，才能把uiautomator導入
* 在src/androidTest/java目錄下創建測試類


