package com.telen.uiwidgets;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.telen.library.widgets.topbar.SwipeDismissBehavior;
import com.telen.library.widgets.topbar.TopSnackbar;

public class SampleActivity extends AppCompatActivity {

    private static final String TAG = "SampleActivity";

    private Button mButtonTopSnackBarHorizontal;
    private Button mButtonTopSnackBarVertical;
    private CoordinatorLayout mainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        mainContent=(CoordinatorLayout) findViewById(R.id.main_content) ;

        mButtonTopSnackBarHorizontal=(Button)findViewById(R.id.trigger_horizontal_topSnackBar);
        mButtonTopSnackBarVertical=(Button)findViewById(R.id.trigger_vertical_topSnackBar);


        mButtonTopSnackBarHorizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TestContent test = new TestContent(SampleActivity.this);
                TopSnackbar snackbar = TopSnackbar.make(mainContent,test, TopSnackbar.LENGTH_INDEFINITE);
                snackbar.show();
            }
        });

        mButtonTopSnackBarVertical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TestContent test = new TestContent(SampleActivity.this);
                TopSnackbar snackbar = TopSnackbar.make(mainContent,test, TopSnackbar.LENGTH_INDEFINITE, SwipeDismissBehavior.SWIPE_DIRECTION_BOTTOM_TO_TOP);
                snackbar.show();
            }
        });

    }

    public class TestContent extends ConstraintLayout {
        public TestContent(Context context) {
            super(context);
            LayoutInflater.from(context).inflate(R.layout.topbar_center_title,this);
        }
    }
}
