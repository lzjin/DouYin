package com.lzj.douyin;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;

/**
 * 测试
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImmersionBar.with(this).transparentStatusBar().init();
    }
}
