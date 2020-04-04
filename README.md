# DouYin
仿抖音点击红心效果  

#### 博客讲解地址,欢迎前往查看
[博客讲解地址](https://blog.csdn.net/lin857/article/details/97540440)

### 欢迎大家Star,老铁给鼓励呗  

### 效果图如下:  
<img    src="https://raw.githubusercontent.com/lzjin/DouYin/master/imgfolder/gif.gif">

### 主要功能  
* 支持宽高设置   heart_width/heart_height
* 支持点击图片设置   heart_image_resId

### Jitpack 
 
---
Step 1. Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
	 ...
	 maven { url 'https://jitpack.io' }
    }
}
```
#### Gradle:
Step 2. Add the dependency
```
dependencies {
    //androidX 版本
    implementation 'com.github.lzjin:DouYin:1.0'
}
```

#### 在布局文件中添加 SideBarLayout
```
   <com.lzj.douyin.redheart.library.RedHeartLayout
       android:layout_width="match_parent"
       android:layout_height="match_parent"
       app:heart_width="100"
       app:heart_height="100"
       app:heart_image_resId="@drawable/ic_heart"/>
```

### 老铁都看这了,给个Star再走呗  

#### v1.0 
* Androidx  
* 基本版使用  