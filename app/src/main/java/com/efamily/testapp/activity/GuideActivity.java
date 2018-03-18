package com.efamily.testapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.efamily.testapp.MainActivity;
import com.efamily.testapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    private final static int[] DRAWABLE_IDS = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3,
            R.mipmap.guide_4};

    private ViewPager m_vpGuide;
    private List<View> m_guides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }

    private void initList() {
        m_guides = new ArrayList<>();

        //- 1
        for (int i = 0; i < DRAWABLE_IDS.length; i++) {

            ImageView imageView = new ImageView(GuideActivity.this);
            imageView.setImageResource(DRAWABLE_IDS[i]);

            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            m_guides.add(imageView);
        }

//        ImageView lastImage = new ImageView(GuideActivity.this);
//        lastImage.setImageResource(DRAWABLE_IDS[DRAWABLE_IDS.length - 1]);
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT);
//        lastImage.setLayoutParams(params);
//        lastImage.setScaleType(ImageView.ScaleType.FIT_XY);
//        lastImage.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(GuideActivity.this, MainActivity.class));
//                GuideActivity.this.finish();
//            }
//        });
//        m_guides.add(lastImage);
    }

    private void initView() {
        m_vpGuide = (ViewPager) findViewById(R.id.viewpager);
        initList();
        m_vpGuide.setPageTransformer(true, new DepthPageTransformer());
        m_vpGuide.setAdapter(new GuideAdpater());
        m_vpGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == m_guides.size() - 1) {
                    m_guides.get(position).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(GuideActivity.this, MainActivity.class));
                            GuideActivity.this.finish();
                        }
                    });
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.5f;

        @Override
        public void transformPage(View view, float position) {

            int pageWidth = view.getWidth();
            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);
            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when
                // moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);
            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);
                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);
                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);

            }
        }

    }

    private class GuideAdpater extends PagerAdapter {

        @Override
        public int getCount() {
            return m_guides.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(m_guides.get(position));
            return m_guides.get(position);

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(m_guides.get(position));
        }
    }
}

