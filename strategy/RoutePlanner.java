package com.klu.tls.strategy;

import com.klu.tls.model.Vehicle;
import com.klu.tls.model.Route;

public interface RoutePlanner {
    double calculateCost(Vehicle v, Route r);
}
