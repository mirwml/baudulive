package com.wml.baiduyunlive.modle.api;

import android.app.Activity;

import com.wml.baiduyunlive.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王苗亮 on 2017/2/20.
 * 存放所有activity的集合管理类
 */

public class ActivityCollections {
    /**
     * 创建添加activity的集合
     */
    public static List<Activity> activities = new ArrayList<Activity>();
    /**
     * 添加activity
     * @param activity
     */
    public static void addActivity(Activity activity) {
        L.d("TAG" ,"activity的数量"+activity+activities.size());
        activities.add(activity);
    }
    /**
     * 移除activity
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }
    /**
     * 当要退出程序里，遍历集合，把所有的activity进行关闭，释放内存
     */
    public static void finishAll(){
        for (Activity a : activities) {
            if(!a.isFinishing()){
                a.finish();
            }
        }
    }


}
