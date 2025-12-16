package com.klu.tls.strategy;

import com.klu.tls.model.Vehicle;
import com.klu.tls.model.Route;

public class StandardCost implements RoutePlanner {

    @Override
    public double calculateCost(Vehicle v, Route r) {
        // cost = distance × vehicle cost per km
        return r.getDistance() * v.getCostPerKm();
    }
}
