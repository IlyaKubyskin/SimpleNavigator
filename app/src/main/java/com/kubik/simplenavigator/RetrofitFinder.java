package com.kubik.simplenavigator;

import com.kubik.simplenavigator.pojo.Direction;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Kubik on 17.02.2018.
 */

public interface RetrofitFinder {
    @GET("maps/api/directions/json")
    Single<Direction> getDirection(@Query("origin") String origin,
                                      @Query("destination") String destination, @Query("key") String apiKey);
}
