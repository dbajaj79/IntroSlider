package com.example.introslider.ui;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;
import com.example.R;
import com.example.databinding.ActivityMainScreenBinding;
import com.example.introslider.MainActivity;
import com.example.introslider.ui.adapter.MyPageAdapterImplementation;
import com.example.introslider.ui.adapter.MyViewPagerAdapter;

public class MainScreen extends AppCompatActivity {

    PreferenceManager preferenceManager;
    TextView[] bottomBars;
    int[] screens;
    MyViewPagerAdapter myvpAdapter;
    MyPageAdapterImplementation myPageAdapterImplementation;
    ActivityMainScreenBinding activityMainScreenBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_screen);
        screens = new int[]{
                R.layout.intro_screen1,
                R.layout.intro_screen2,
                R.layout.intro_screen3,
        };
        myvpAdapter = new MyViewPagerAdapter(this, screens);
        myPageAdapterImplementation = new MyPageAdapterImplementation(screens, this);
        activityMainScreenBinding.setAdapter(myPageAdapterImplementation);
        preferenceManager = new PreferenceManager(this);
        activityMainScreenBinding.viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        if (!preferenceManager.FirstLaunch()) {
            launchMain();
            finish();
        }
        ColoredBars(0);
    }

    public void next(View v) {
        int i = getItem(+1);
        if (i < screens.length) {
            activityMainScreenBinding.viewPager.setCurrentItem(i);
        } else {
            launchMain();
        }
    }



    public void skip(View view) {
        launchMain();
    }

    private void ColoredBars(int thisScreen) {
        int[] colorsInactive = getResources().getIntArray(R.array.dot_on_page_not_active);
        int[] colorsActive = getResources().getIntArray(R.array.dot_on_page_active);
        bottomBars = new TextView[screens.length];
        activityMainScreenBinding.layoutBars.removeAllViews();
        for (int i = 0; i < bottomBars.length; i++) {
            bottomBars[i] = new TextView(this);
            bottomBars[i].setTextSize(100);
            bottomBars[i].setText(Html.fromHtml("&#175"));
            activityMainScreenBinding.layoutBars.addView(bottomBars[i]);
            bottomBars[i].setTextColor(colorsInactive[thisScreen]);
        }
        if (bottomBars.length > 0)
            bottomBars[thisScreen].setTextColor(colorsActive[thisScreen]);
    }

    private int getItem(int i) {
        return activityMainScreenBinding.viewPager.getCurrentItem() + i;
    }

    private void launchMain() {
        preferenceManager.setFirstTimeLaunch(false);
        startActivity(new Intent(MainScreen.this, MainActivity.class));
        finish();
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            ColoredBars(position);
            if (position == screens.length - 1) {
                activityMainScreenBinding.next.setText("start");
                activityMainScreenBinding.skip.setVisibility(View.GONE);
            } else {
                activityMainScreenBinding.next.setText(getString(R.string.next));
                activityMainScreenBinding.skip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };


}