package com.mercury.demo.t;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mercury.demo.R;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

class MainActivity extends AppCompatActivity {



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

        MySyncTask task = new MySyncTask();
        task.execute("sss");
        setContentView(R.layout.acrivity_watermask);
    }




    class MySyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }


    private void heapSort(int[] array) {
        for(int i = (array.length-2)/2;i>=0;i++){
            downAdjust(array,i,array.length);
        }

    }

    void downAdjust(int[] array, int parentIndex, int length) {
        int temp = array[parentIndex];
        int chileIndex = 2 * parentIndex + 1;
        while (chileIndex < length) {
            if (chileIndex + 1 < length && array[chileIndex + 1] > array[chileIndex]) {
                chileIndex++;
            }

            if (temp >= array[chileIndex])
                break;
            array[parentIndex] = array[chileIndex];
            parentIndex = chileIndex;
            chileIndex = chileIndex * 2 + 1;
        }
        array[parentIndex] = temp;
    }



}

