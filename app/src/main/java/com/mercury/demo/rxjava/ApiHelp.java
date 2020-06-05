package com.mercury.demo.rxjava;

public class ApiHelp {
    static  TestService testService;

    String baseurl = "https://www.baidu.com/";
    public static TestService getTestService(){
        if(testService == null){
            synchronized (TestService.class){
                if(testService == null){
                    testService = RetrofitHelp.getHelper().retrofit().create(TestService.class);
                }
            }
        }
        return testService;
    }
}
