package com.mercury.demo.aty;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mercury.demo.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HookTestActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hooktest);
        textView = findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HookTestActivity.this, "origin click", Toast.LENGTH_SHORT).show();
            }
        });
        hookOnClickListener(textView);
    }

    /**
     * 通过对 Android 平台的虚拟机注入与 Java 反射的方式，来改变 Android 虚拟机调用函数的方式（ClassLoader），
     * 从而达到 Java 函数重定向的目的，这里我们将此类操作称为 Java API Hook。
     *
     * https://www.jianshu.com/p/4f6d20076922
     *
     * 总结一下：
     *
     * Hook 的选择点：静态变量和单例，因为一旦创建对象，它们不容易变化，非常容易定位。
     * Hook 过程：
     * 寻找 Hook 点，原则是静态变量或者单例对象，尽量 Hook public 的对象和方法。
     * 选择合适的代理方式，如果是接口可以用动态代理。
     * 偷梁换柱——用代理对象替换原始对象。
     * Android 的 API 版本比较多，方法和类可能不一样，所以要做好 API 的兼容工作。

     */
    private void hookOnClickListener(View view){
            try {
                Method getListenrInfo = View.class.getDeclaredMethod("getListenerInfo");
                getListenrInfo.setAccessible(true);
                Object listenerInfo = getListenrInfo.invoke(view);
                Class<?> listenerInfoClz = Class.forName("android.view.View$ListenerInfo");
                Field mOnclickListener = listenerInfoClz.getDeclaredField("mOnClickListener");
                mOnclickListener.setAccessible(true);
                View.OnClickListener originListener = (View.OnClickListener) mOnclickListener.get(listenerInfo);
                View.OnClickListener hookedListener = new HookedOnClickListener(originListener);
                mOnclickListener.set(listenerInfo,hookedListener);

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }

    }

    class HookedOnClickListener implements View.OnClickListener {
        private View.OnClickListener origin;

        HookedOnClickListener(View.OnClickListener origin) {
            this.origin = origin;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(HookTestActivity.this, "hook click", Toast.LENGTH_SHORT).show();
            Log.i("Hook","Before click, do what you want to to.");
            if (origin != null) {
                origin.onClick(v);
            }
            Log.i("Hook","After click, do what you want to to.");
        }
    }


    private void hookNotificationManager(final Context context) throws Exception {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Method getService = NotificationManager.class.getDeclaredMethod("getService");
        getService.setAccessible(true);
        final Object sOriginService = getService.invoke(notificationManager);
        Class iNotofication = Class.forName("android.app.INotificationManager");
        Object proxyNothing = Proxy.newProxyInstance(context.getClass().getClassLoader(), new Class[]{iNotofication}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String name = method.getName();
                if(args != null && args.length>0){
                    for(Object arg:args){
                        Log.d("Hook","invock arg:"+arg);
                    }
                }
                Toast.makeText(context.getApplicationContext(),"检测有人发通知",Toast.LENGTH_LONG).show();
//操作交由 sOriginService 处理，不拦截通知
                return method.invoke(sOriginService,args);
                // 拦截通知，什么也不做
                //                    return null;
                // 或者是根据通知的 Tag 和 ID 进行筛选
            }
        });
        // 第三步：偷梁换柱，使用 proxyNotiMng 替换系统的 sService
        Field sServiceField = NotificationManager.class.getDeclaredField("sService");
        sServiceField.setAccessible(true);
        sServiceField.set(notificationManager, proxyNothing);

        //：https://blog.csdn.net/gdutxiaoxu/article/details/81459830
    }

    //getMethod(), getMethods(),getDeclaredMethod(),getDelcaredMethods()
}
