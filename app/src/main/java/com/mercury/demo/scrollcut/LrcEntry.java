package com.mercury.demo.scrollcut;

import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import com.mercury.demo.util.DisplayUtil;

/**
 * @author: LJQ
 * @date: 2023/12/2 22:27
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
