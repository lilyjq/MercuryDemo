package com.mercury.demo.kotlintest

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * @author: LJQ
 * @date:  2023/8/7 18:07
 * https://mp.weixin.qq.com/s/SA2_DsSzXmWtLHf_6so7VA
 */
class FlowTest {

  suspend fun flowTest(){
  val flow = flow<Int> {
    emit(10)
   }

    flow.collect{
      println(it)
    }


    val result = mutableListOf<String>()
    val flow2 = flow<String>{
      emit("hello word ${Thread.currentThread()}")
    }
    flow2.collect {
      println(it)
      result.add(it)
    }


    val result1 = mutableListOf<String>()
    flow{
      emit("hello word")
    }.toList(result1)


    flow{
      emit(1)
    }.transform {
      emit("firstNum: "+it)
    }.transform<String,String> {
      emit(it+"end")
    }.collect {
      println(it)
    }

    flow{
      emit(1)
    }.transform<Int,Int> {
      emit(Math.random().compareTo(it))
    }.transform<Int,String> {
      emit("$it 1")
    }.transform {
      emit("$it 2")
    }.collect {
      println(it)
    }



    flow{
      emit(1)
    }.map {
      it+1
    }.collect {
      println(it)
    }


    flow{
      emit("hello")
      emit("how are you ")
      emit("i dont think so")
    }.filter {
      it.contains("h")
    }.collect {
      println(it)
    }


    flow{
      println("${Thread.currentThread()}")
      emit("hello")
    }.flowOn(Dispatchers.IO).collect {
      println(it)
      println("${Thread.currentThread()}")
    }


    //背压






  }

  fun test06(){
    runBlocking {
      val time = measureTimeMillis {
        flow {
          println("emit ${Thread.currentThread()}")
          emit("heo")
          kotlinx.coroutines.delay(1000)
          emit("ee hh")
        }.buffer().collect {
          delay(2000)
          println(it)
          println("collect ${Thread.currentThread()}")
        }
      }
      println("use time: $time")
    }
  }

  /**
   * 上游覆盖旧数据，相当于使用一个只能容纳一个数据额buffer,新的数据将会覆盖旧的数据
   */
  fun test07(){
    runBlocking {
      flow {
        repeat(5){
          delay(100)
          emit("emit $it")
        }
      }.conflate().collect {
        delay(500)
        println(it)
      }
    }
  }


  fun test08(){
    runBlocking {
    flow {
      repeat(5){
      emit(it)
      }
    } .transformLatest {
      delay(200)
      emit("$it fish")
    } .collect {
      println(it)
    }

    }
  }

  /**
   * 收集最新的数据
   */
  fun test09(){
    runBlocking {
      flow {
        repeat(5){
          emit(it)
        }
      } .mapLatest {
        delay(200)
        "$it fish"
      } .collect {
        println(it)
      }

    }
  }

  fun test014(){
    runBlocking {
     val time = measureTimeMillis {
       val flow = flow<Int> {
         repeat(100){
           emit(it+1)
         }
       }
       flow.collectLatest {
         delay(20)
         println(it)
       }
     }
      println(time)

    }
  }

  fun test10(){
    runBlocking {
      val flow1 = flow {
        emit("stuInfo")
      }
      flow1.flatMapConcat {
        flow { emit("$it teachInfo") }
      }.collect {
        println("collect $it")
      }
    }
  }

  fun test11(){
    runBlocking {
      val time = measureTimeMillis {
        val flow1 = flow {
          emit("stu 1")
          emit("stu 2")
          emit("stu 3")
        }
        flow1.flatMapConcat {
          flow {
            delay(1000)
            emit("$it teach")
          }
        }.collect {
          println("collect $it")
        }
      }
    }
  }

  fun test1(){
    runBlocking {
      val time = measureTimeMillis {
        val flow1 = flow {
          emit("stu 1")
          emit("stu 2")
          emit("stu 3")
        }
        flow1.flatMapMerge(4) {
          flow {
            delay(1000)
            emit("$it teach")
          }
        }.collect {
          println("collect $it")
        }
      }
    }
  }

  //flatMapLatest

  //combin
  /**
   * 短的一方会等长的一方结束才结束
   */
  fun test15(){
    runBlocking {
      val time = measureTimeMillis {
        val flow1 = flow {
          emit("stu 1")
          emit("stu 2")
          emit("stu 3")
        }

        val flow2 = flow {
          emit("stu sbuject")
        }
        flow1.combine(flow2){
          sex,sub ->
          "$sex --> $sub"
        }.collect {
          println(it)
        }
      }
      println("use time:$time")
    }
  }

  /**
   * zip 就是会将两个数据链接起来但是多余的数据会被丢弃
   */
  fun test17(){
    runBlocking {
      val time = measureTimeMillis {
        val flow1 = flow {
          emit("stu 1")
          emit("stu 2")
          emit("stu 3")
        }

        val flow2 = flow {
          emit("stu sbuject")
        }

        flow1.zip(flow2){
            sex,sub ->
          "$sex --> $sub"
        }.collect {
          println(it)
        }
      }
      println("use time:$time")
    }
  }

  //async在 coroutineScope 构建器或在其他协程创建的协程中抛出的异常不会被 try/catch 捕获！
  //  类似于常规的Job，唯一不同的是SupervisorJob的取消只会向下传播，一个子协程的运行失败不会影响到其他协程。
}