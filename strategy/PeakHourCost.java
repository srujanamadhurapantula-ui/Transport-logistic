package com.klu.tls.strategy;

import com.klu.tls.model.Vehicle;
import com.klu.tls.model.Route;

public class PeakHourCost implements RoutePlanner {

    @Override
    public double calculateCost(Vehicle v, Route r) {
        double baseCost = r.getDistance() * v.getCostPerKm();
        return baseCost + (baseCost * r.getTrafficLevel() * 0.1);
    }
}
