package com.sample.uiwidgets;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.sample.uiwidgets.widgets.topbar.BaseTransientBottomBar;
import com.sample.uiwidgets.widgets.topbar.TopSnackbar;

public class SampleActivity extends AppCompatActivity {

    private Button mButtonTopSnackBar;
    private CoordinatorLayout mainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        mButtonTopSnackBar=(Button)findViewById(R.id.trigger_topSnackBar);
        mainContent=(CoordinatorLayout) findViewById(R.id.main_content) ;
        mButtonTopSnackBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TestContent test = new TestContent(SampleActivity.this);
                TopSnackbar snackbar = TopSnackbar.make(mainContent,test, test, TopSnackbar.LENGTH_INDEFINITE);
                snackbar.show();
            }
        });

    }

    public class TestContent extends ConstraintLayout implements BaseTransientBottomBar.ContentViewCallback {
        public TestContent(Context context) {
            super(context);
            LayoutInflater.from(context).inflate(R.layout.topbar_center_title,this);
        }

        @Override
        public void animateContentIn(int delay, int duration) {

        }

        @Override
        public void animateContentOut(int delay, int duration) {

        }
    }
}
