package com.anno.exe.goodsviewpagerwithpoint;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import Adapter.GoodsViewAdapter;

public class MainActivity extends AppCompatActivity {

    // 头部viewpager
    private ViewPager vp_goods_introduc;
    private ArrayList<View> pageViews;
    // 包裹小圆点的LinearLayout,图片组和单一的图片
    private ViewGroup group;
    private ImageView[] imageViews;
    private ImageView imageView;
    private int[] productPhotos = new int[]{R.mipmap.d264848,
            R.mipmap.d1199722982,
            R.mipmap.x222358899};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        initGoodsViewPager();
    }

    private void findViewById() {
        vp_goods_introduc = (ViewPager) findViewById(R.id.vp_goods_introduc);
        group = (ViewGroup) findViewById(R.id.layout_dot);
    }


    private void initGoodsViewPager() {

        // 图片
        pageViews = new ArrayList<View>();
        for (int i = 0; i < productPhotos.length; i++) {
            LinearLayout layout = new LinearLayout(this);
            ViewGroup.LayoutParams ltp = new ViewGroup.LayoutParams(
                    ViewPager.LayoutParams.MATCH_PARENT,
                    ViewPager.LayoutParams.MATCH_PARENT);
            // 每张图片展示的方式
            final ImageView imageView = new ImageView(this);
            // 适应整个viewpager，不留白
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            // String uri, ImageView imageView(控件), DisplayImageOptions options
            //在com.nostra13.universalimageloader.core;包里
//            ImageLoader.getInstance()
//                    .displayImage(ServiceApi.urlhostip + productPhotos[i],
//                            imageView, options);
            // addView(View child, ViewGroup.LayoutParams params)
            imageView.setBackgroundDrawable(getResources().getDrawable(productPhotos[i]));
            layout.addView(imageView, ltp);
            pageViews.add(layout);
        }

        /**
         * 有几张图片下面就显示几个小圆点
         */

        imageViews = new ImageView[pageViews.size()];
        LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        // 设置每个小圆点距离左边的间距
        margin.setMargins(15, 0, 0, 0);

        for (int i = 0; i < pageViews.size(); i++) {

            imageView = new ImageView(MainActivity.this);

            // 设置每个小圆点的宽高
            imageView.setLayoutParams(new LinearLayout.LayoutParams(5, 5));
            imageViews[i] = imageView;

            if (i == 0) {

                // 默认选中第一张图片
                imageViews[i]
                        .setBackgroundResource(R.drawable.goods_indicator_focused);
            } else {
                // 其他图片都设置未选中状态
                imageViews[i]
                        .setBackgroundResource(R.drawable.goods_indicator_unfocused);
            }
            group.addView(imageViews[i], margin);
        }
        // 给viewpager设置适配器
        vp_goods_introduc.setAdapter(new GoodsViewAdapter(pageViews));
        // 给viewpager设置监听事件
        vp_goods_introduc
                .setOnPageChangeListener(new GuidePageChangeListener());
    }


    /* 头部viewpager的页面转换监听 */
    private class GuidePageChangeListener implements
            ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {

            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int arg0) {

            // 遍历数组让当前选中图片下的小圆点设置颜色

            for (int i = 0; i < imageViews.length; i++) {

                imageViews[arg0].setBackgroundResource(R.drawable.goods_indicator_focused);
                if (arg0 != i) {
                    imageViews[i].setBackgroundResource(R.drawable.goods_indicator_unfocused);
                }
            }
        }
    }
}
