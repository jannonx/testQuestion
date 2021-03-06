package com.example.mvvmlibrary.base.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvvmlibrary.R;

/**
 * @description:
 * @author: Jannonx
 * @since: 2020/11/24 23:56
 */
public abstract class BaseActionBarActivity extends BaseActivity {
    protected ImageView back_iv;
    protected TextView title_tv;
    protected TextView right_tv;
    protected ImageView right_iv;

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initFragment(savedInstanceState);
    }

    private void initView() {
        back_iv = findViewById(R.id.back_iv);
        title_tv = findViewById(R.id.title_tv);
        right_tv = findViewById(R.id.right_tv);
        right_iv = findViewById(R.id.right_iv);
    }

    protected abstract void initFragment(Bundle savedInstanceState);
    protected void setTitleCenter(String title) {
        title_tv.setText(title);
    }
}
