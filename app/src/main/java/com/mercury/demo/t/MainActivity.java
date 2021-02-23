package com.mercury.demo.t;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

class MainActivity  extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);


//        以使用泛型通配符 ? extends 来使泛型支持协变，但是「只能读取不能修改」，这里的修改仅指对泛型集合添加元素，如果是 remove(int index) 以及 clear 当然是可以的。
//        可以使用泛型通配符 ? super 来使泛型支持逆变，但是「只能修改不能读取」，这里说的不能读取是指不能按照泛型类型读取，你如果按照 Object 读出来再强转当然也是可以的。

        List<Apple> apples = new ArrayList<Apple>();
        List<? extends Furit> fruits = new ArrayList<>();
        Furit fruit = fruits.get(0);
//        报错 只可以获取不能写入
//        fruits.add(new Furit());
//        fruits.add(new Apple())

        List<? super Apple> list = new ArrayList<>();
        List<Furit> furits = new ArrayList<>();
        Furit furit = (Furit) list.get(0);
        Apple a = (Apple) list.get(0);
        furits.add(new Furit());
        list.add(new Apple());
//        报错 ，只能写入 不能获取 要强转 容易报错
//        list.add(new Furit());

    }
}
