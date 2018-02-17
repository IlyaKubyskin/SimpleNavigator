package com.kubik.simplenavigator.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kubik on 17.02.2018.
 */

public class GeocodedWaypoint {


    @SerializedName("geocoder_status")
    @Expose
    public String geocoderStatus;
    @SerializedName("place_id")
    @Expose
    public String placeId;
    @SerializedName("types")
    @Expose
    public List<String> types = null;
    @SerializedName("partial_match")
    @Expose
    public Boolean partialMatch;

    public String getGeocoderStatus() {
        return geocoderStatus;
    }

    public void setGeocoderStatus(String geocoderStatus) {
        this.geocoderStatus = geocoderStatus;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public Boolean getPartialMatch() {
        return partialMatch;
    }

    public void setPartialMatch(Boolean partialMatch) {
        this.partialMatch = partialMatch;
    }
}
