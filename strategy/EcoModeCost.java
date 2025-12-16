package com.klu.tls.strategy;
import com.klu.tls.model.Vehicle;
import com.klu.tls.model.Route;

public class EcoModeCost implements RoutePlanner {
    @Override
    public double calculateCost(Vehicle v, Route r) {
        return v.computeFuelCost(r.getDistance()) * 0.85; 
    }
}