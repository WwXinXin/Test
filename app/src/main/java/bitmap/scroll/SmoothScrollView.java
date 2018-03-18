package bitmap.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by admin on 2018/1/3.
 */
public class SmoothScrollView extends LinearLayout {

    Scroller mScroller ;
    int startX;
    int startY;
    public SmoothScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //创建Scroller实例
        mScroller = new Scroller(context);
    }


    public void smoothScroll(int dx,int dy,int duration){
        //获取滑动起点坐标
        startX = getScrollX();
        startY = getScrollY();
        //设置滑动参数
        mScroller.startScroll(startX,startY,dx,dy,duration);
        //重新绘制View
        invalidate();
    }

    @Override
    public void computeScroll() {
        // TODO Auto-generated method stub
        super.computeScroll();
        boolean flag = mScroller.computeScrollOffset();
        //递归终止条件:滑动结束
        if(flag == false){
            return;
        }else{
            //mScroller.getCurrX(),mScroller.getCurrY()记录的是此刻要滑动达到的目标坐标
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
        }
        //递归调用
        invalidate();//或者postInvalidate()
    }
}
