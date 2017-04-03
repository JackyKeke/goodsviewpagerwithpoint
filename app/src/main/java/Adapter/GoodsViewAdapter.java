/**
 * Created by Administrator on 2016/9/18 at 16:24.
 */

package Adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 商品的大图片
 */
public class GoodsViewAdapter extends PagerAdapter {


    private final ArrayList<View> list;

    public GoodsViewAdapter(ArrayList<View> list) {
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = list.get(position);
        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent != null) {
            parent.removeAllViews();
        }
        container.addView(v);
        return v;
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(list.get(position));

        return list.get(position);

    }

    //根据指定下标移除视图组中view对象
        /*
         * ViewGroup container:视图组
         * int position：指定的下标
         * Object object：instantiateItem返回的Object对象
         */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //要使滑到最后一张程序不崩，需注释掉此行
        //super.destroyItem(container, position, object);
        //从视图组中移除指定下标的view对象
        container.removeView(list.get(position));

    }

    //表示当前ViewPager展示的view对象是否是instantiateItem创建的对象（即是否是同一对象）
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

}
