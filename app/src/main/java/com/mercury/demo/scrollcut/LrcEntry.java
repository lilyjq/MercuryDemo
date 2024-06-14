package com.mercury.demo.scrollcut;

import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import com.mercury.demo.util.DisplayUtil;

/**
 * @author: LJQ
 * @date: 2023/12/2 22:27
 *
 * 歌词效果不好，最后项目实现最后还是用了recyclerView +监听滚动实现
 *
 * 上下空白部分，用的heard+foot 根据距离设定，head 屏幕的一半 foot 屏幕的一半减去最后一个item的一半
 * array 记录所有的itemView(歌词)高度
 * itemview addOnLayoutChangeListener 获取高度 然后removeOnLayoutChangeListener
 * 自定义CenterLayoutManager 可以让歌词滚到中间
 *
 * 通过音乐的进度去调整recyclerView 滚动 和滚动到哪里
 *
 *  recyclerview.addOnScrollListener 手指的滚动 通过滚动距离去拿现在最靠近中间的view 变色
 *
 *
 */
public class LrcEntry {

  //歌词所对应的时间
  private long time;
  //StaticLayout，可以自动换行
  private StaticLayout staticLayout;

  private float offset = Float.MIN_VALUE;
  private String text;
  void init(TextPaint paint){
    staticLayout = new StaticLayout(text, paint, (int) DisplayUtil.dp2px(200), Layout.Alignment.ALIGN_CENTER, 1f, 0f, false);
  }


  /**
   * 获取歌词高度
   */
  public int getHeight() {
    if (staticLayout == null) {
      return 0;
    }
    return staticLayout.getHeight();
  }

  public StaticLayout getStaticLayout() {
    return staticLayout;
  }



  public void setOffset(float offset) {
    this.offset = offset;
  }



  public float getOffset() {
    return offset;
  }

  public void setText(String text) {
    this.text = text;
  }
}
