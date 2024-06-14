package com.mercury.demo.behavior

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.google.android.material.appbar.AppBarLayout
import com.mercury.demo.R
import com.mercury.demo.R.id
import com.mercury.demo.R.layout
import com.mercury.demo.viewpager.ListItemAdapter

class CoordinateActivity : AppCompatActivity() {
  //https://juejin.cn/post/7002139018812981261
  //https://blog.csdn.net/baidu_33331545/article/details/126389524   MotionLayout 与 NestedScrollView 滑动的问题
  //https://blog.csdn.net/eyishion/article/details/121436522  Android CoordinatorLayout之自定义Behavior
  //https://juejin.cn/post/6844903984931536904  CoordinatorLayout 学习(三) - 通过自定义Behavior解决AppBarLayout 不能Fling的问题
 //https://blog.51cto.com/u_15375308/5073098  一起动才够嗨！Android CoordinatorLayout 自定义 Behavior 原创


  //在做自定义behavoir的时候发现一个基础的view绘制的问题，如果外部减少viewGroup 的高度那么将影响wrapContent的childView 的展示，
  // 解决方案1：直接写死view的高度
  // 解决方案2：自定义viewGroup OnMeaure里直接用Un.... 测量 这样应该也有问题 就是那么GroupView 在Wrapcontent时的高度又有问题，得固定viewGroup 的高度了
  //在项目里实现的时候，topView 的某一些部分 可能有可能没有，因此刚开始是gone的状态 在拿到数据后手动增加了GroupView 的高度


  //coordinateLayout +AppBarLayout 在top1(滚动)+top2（不滚动）+top3（滚动）的情况下 top3是不会滚动的
  // 猜测是循环获取可滚动的view一旦有一个不滚动 后续的view就不处理了
  var recyclerView: RecyclerView? = null

  /*
    //TextView textView ;

    */

  var appBarLayout:AppBarLayout? = null

  lateinit var top:View
  lateinit var bottom:View
 var  topH = 0
  var boH = 0

  var state = 0 //展开，1 收起

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.coordinate7)

    //findViewById(R.id.iv).setOnClickListener(new View.OnClickListener() {
    //    @Override public void onClick(View v) {
    //        Toast.makeText(CoordinateActivity.this,"dddddd",Toast.LENGTH_SHORT).show();
    //    }
    //});
    // top = findViewById(R.id.tv_top)
    // bottom = findViewById(R.id.tv_bottom)
    recyclerView = findViewById(id.recyclerview)
    recyclerView!!.setLayoutManager(LinearLayoutManager(this))
    recyclerView!!.setAdapter(ListItemAdapter(this))
    recyclerView!!.addOnScrollListener(object : OnScrollListener() {
      override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
      }

      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        Log.i("eeeeee", "onScrolled: $dy")
        // if(state == 0 && dy>0){
        //   hideAll()
        // }else if(state == 1 && dy<0){
        //   showAll()
        // }


      }
    })





    //
    // recyclerView!!.viewTreeObserver.addOnGlobalLayoutListener(object :ViewTreeObserver.OnGlobalLayoutListener {
    //   override fun onGlobalLayout() {
    //     topH = top.measuredHeight
    //     boH = bottom.measuredHeight
    //   }
    // })

    //
    //
    //
    //
    //
    //
    ////textView = findViewById(R.id.tv_scrool);
    // appBarLayout = findViewById(R.id.appbar);
    // appBarLayout!!.addOnOffsetChangedListener(object:AppBarLayout.OnOffsetChangedListener{
    //   override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
    //     Log.i("eeeee", "onOffsetChanged: "+verticalOffset)
    //   }
    // }
    // );

  }








}