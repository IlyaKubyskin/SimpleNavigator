package com.kubik.simplenavigator.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kubik on 17.02.2018.
 */
public class OverviewPolyline {
    @SerializedName("points")
    @Expose
    public String points;

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
