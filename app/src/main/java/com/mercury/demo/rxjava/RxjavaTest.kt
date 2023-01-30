package com.mercury.demo.rxjava

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit.MILLISECONDS

/**
 * @author: LJQ
 * @date: 2023/5/5 4:43 PM
 */
class RxjavaTest {
  //不想发射信号用Completable
  //delay操作符会切换到cpu 密集线程  delay、window、Observable.interval()
  fun rxjava() {
    val disposable = Completable.complete().delay(100, MILLISECONDS)
      .subscribe { }
    Single.fromCallable<String> { null }
    Single.create<String> { emitter ->
      if (!emitter.isDisposed) {
      }
    }

//PublishSubject 只能收到订阅之后的信号
    //BehaviorSubject 只能收到最近的信号
    val subject = PublishSubject.create<String>()
    subject.debounce(1500,MILLISECONDS)
      .subscribe{}
    subject.onNext("")



  }
}