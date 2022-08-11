package com.ztx.dynamicdbsource.config;

public class ThreadLocalDataSource {
    private static ThreadLocal<String> local=new ThreadLocal<String>();

    public static void setDataSource(String dataSource){
        local.set(dataSource);
    }

    public static String  getDataSource(){
        return local.get();
    }
}
