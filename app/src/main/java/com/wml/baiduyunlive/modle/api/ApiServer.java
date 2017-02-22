package com.wml.baiduyunlive.modle.api;

import com.wml.baiduyunlive.modle.BaseBean;
import com.wml.baiduyunlive.modle.bean.HttpResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 王苗亮 on 2017/2/20.
 */


public interface ApiServer<T> {
/**
 * http://v.juhe.cn/joke/randJoke.php?type=%22%22&key=26e9ed96a3ea69201844871a9ef4acec
 * http://v.juhe.cn/joke/randJoke.php?type=%22%22&key=26e9ed96a3ea69201844871a9ef4acec
 *
 */
     @GET("randJoke.php?key=26e9ed96a3ea69201844871a9ef4acec")
    Observable<BaseBean<List<T>>> requestData(@Query("type") String type);
    @POST()
    Observable<BaseBean<T>>  loginService();


}
