package com.kubik.simplenavigator;

import com.kubik.simplenavigator.pojo.Direction;
import com.kubik.simplenavigator.pojo.Route;

import java.util.List;

/**
 * Created by Kubik on 17.02.2018.
 */

public interface OnDirectionFoundListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(Direction direction);
}
