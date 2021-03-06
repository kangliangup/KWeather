package com.example.weather.http;


import com.example.weather.bean.ChapterData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Date：2018/2/7
 * Author：小康de生活
 * Signature:好好学习，天天向上
 * Description:
 */

public interface HttpService {
//    @GET("/cs/mobile/login")
//    Observable<HttpResultData<LoginResult>> getLoginResult
//            (@Query("nickname") String userName, @Query("password") String password);
//
//    @GET("/cs/mobile/preSyncVisitTask")
//    Observable<HttpResultList<TaskResult>> getTasks
//            (@Query("_appTicket") String cookie, @Query("taskIds") List<String> taskIds);
//
////    );
//    @GET("/cs/mobile/syncVisitTask")
////    Observable<HttpResultList<TaskDetailResult>> getTaskDetail(
////            @Query(value = "_appTicket") String cookie,
////            @Query(value = "taskId") String taskId
////    );
@GET("/wxarticle/chapters/json")
Observable<HttpResultData<List<ChapterData>>> getChapters();
}

