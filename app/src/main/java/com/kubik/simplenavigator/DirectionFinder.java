package com.kubik.simplenavigator;

import com.kubik.simplenavigator.pojo.Direction;
import com.kubik.simplenavigator.pojo.Route;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kubik on 17.02.2018.
 */

public class DirectionFinder {

    private final String BASE_URL = "https://maps.googleapis.com/";
    private final String API_KEY = "AIzaSyCWQTw-4LfJo2Zt_m_DD_6LGuli0nGFGFw";

    private String origin;
    private String destination;
    private List<Route> routes = new ArrayList<>();
    private OnDirectionFoundListener listener;

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public DirectionFinder(String origin, String destination, OnDirectionFoundListener listener) {
        this.origin = origin;
        this.destination = destination;
        this.listener = listener;
    }

    public void findDeWei(){
        listener.onDirectionFinderStart();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitFinder service = retrofit.create(RetrofitFinder.class);

        Single<Direction> observable = service.getDirection(origin, destination, API_KEY);
        DisposableSingleObserver<Direction> observer = new DisposableSingleObserver<Direction>() {

            @Override
            public void onSuccess(Direction direction) {
              setRoutes(direction.getRoutes());
              listener.onDirectionFinderSuccess(direction);
            }

            @Override
            public void onError(Throwable e) {

            }
        };

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
}

