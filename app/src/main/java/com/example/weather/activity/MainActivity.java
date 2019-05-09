package com.example.weather.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.weather.R;
import com.example.weather.adapter.WeatherFragmentAdapter;
import com.example.weather.base.MvpBaseActivity;
import com.example.weather.base.StatusBarHelper;
import com.example.weather.contract.MainContract;
import com.example.weather.fragment.WeatherFragment;
import com.example.weather.presenter.MainPresenterImpl;
import com.tmall.ultraviewpager.UltraViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import interfaces.heweather.com.interfacesmodule.bean.weather.now.Now;


public class MainActivity extends MvpBaseActivity<MainContract.View, MainPresenterImpl> implements MainContract.View {

    @BindView(R.id.rl_top)
    LinearLayout rlTop;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.iv_add_city)
    ImageView ivAddCity;
    @BindView(R.id.iv_location)
    ImageView ivLocation;

    private List<String> cities = new ArrayList<>();
    private List<WeatherFragment> fragments = new ArrayList<>();

    @Override
    public MainPresenterImpl initPresenter() {
        return new MainPresenterImpl(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        StatusBarHelper.setStatusTranslucent(this, rlTop);
//        StatusBarHelper.setStatusColor(this,Color.RED);

//        mPresenter.getWeatherNow("");
        getData();
        tvCity.setText(cities.get(0));
        initViewPager();


    }

    private void getData() {
        cities.add("武汉");
        cities.add("孝感");
        cities.add("汉川");

        fragments.add(WeatherFragment.newInstance(cities.get(0)));
        fragments.add(WeatherFragment.newInstance(cities.get(1)));
        fragments.add(WeatherFragment.newInstance(cities.get(2)));
    }

    private void initViewPager() {

        UltraViewPager ultraViewPager = findViewById(R.id.ultra_viewpager);
        ultraViewPager.setAdapter(new WeatherFragmentAdapter(getSupportFragmentManager(), fragments));

        ultraViewPager.initIndicator();
        ultraViewPager.getIndicator()
                .setFocusColor(Color.GREEN)
                .setNormalColor(Color.WHITE)
//                .setIndicatorPadding(50)
//                .setMargin(0,50,0,0)
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
        ultraViewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
        ultraViewPager.getIndicator().build();
        ultraViewPager.setOnPageChangeListener(pageChangeListener);


    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            tvCity.setText(cities.get(position));
            ivLocation.setVisibility(position==0?View.VISIBLE:View.GONE);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @Override
    public void showWeatherNow(Now now) {

    }


    @Override
    protected int setStatusBarState() {
        return 5;
    }
}
