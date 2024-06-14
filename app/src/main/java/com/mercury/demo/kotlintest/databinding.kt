package com.mercury.demo.kotlintest

import androidx.databinding.BaseObservable
import androidx.databinding.Observable
import androidx.databinding.ObservableField

/**
 * @author: LJQ
 * @date:  2023/8/17 14:37
 */
class databinding {
  //xml layout
  // <data>
  // <import type="com.leavesc.databinding_demo.model.User" />
  // <variable
  // name="userInfo"
  // type="User" />
  // </data>

  // @ 单向绑定  @= 双向绑定

  //数据变化自动驱动 UI 刷新的方式有三种：BaseObservable、ObservableField、ObservableCollection
  //        ActivityMain10Binding activityMain10Binding = DataBindingUtil.setContentView(this, R.layout.activity_main10);

  //  ObservableGoods goods = new ObservableGoods("code", "hi", 23);
  //         activityMain10Binding.setGoods(goods);


  // <TextView
  // android:layout_width="match_parent"
  // android:layout_height="wrap_content"
  // android:text='@{"xxx"}'
  // android:textAllCaps="false"/>



  // <TextView
  // android:layout_width="match_parent"
  // android:layout_height="wrap_content"
  // android:text='@={"xxx"}'
  // android:textAllCaps="false"/>

  private var myName :ObservableField<String>  = ObservableField<String>()
  private var myPrice:ObservableField<Int> = ObservableField<Int>()


  fun tst (){
    myName.set("ddmkal")
    myName.get()
  }

  class Goods ( name: String?, price: Long?): BaseObservable() {
    private var  name :String?= null
    private var price:Long? = null

    fun setName(name: String?){
      this.name = name
      // notifyPropertyChanged(com.mercury.demo.kotlintest.databinding.Goods)
    }


    fun setprice(price: Long?){
      this.price = price
      notifyChange()
    }





  }


}