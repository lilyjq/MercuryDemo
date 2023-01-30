package com.mercury.demo.t

/**
 * @author: LJQ
 * @date:  2023/5/5 5:45 PM
 * 密封类 主要功能为限制继承 枚举类的扩展
 */
sealed class Bread {
  class Iop(value: Int)
  class Idp(value: String)
}


fun ins(value: Bread){
  when(value){

    else -> {}
  }
}



enum class Color(value:Int){
  Red(1),
  Blue(2)
}

