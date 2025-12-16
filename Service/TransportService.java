package com.klu.tls.Service;

import com.klu.tls.Factory.PlannerFactory;
import com.klu.tls.strategy.RoutePlanner;
import com.klu.tls.model.Vehicle;
import com.klu.tls.model.Route;
import com.klu.tls.exceptions.RouteNotFoundException;
import java.util.*;

public class TransportService {

    private Map<String, Vehicle> vehicleMap = new HashMap<>();
    private Map<String, Route> routeMap = new HashMap<>();

    public void addVehicle(String id, Vehicle vehicle) {
        vehicleMap.put(id, vehicle);
    }

    public void addRoute(String id, Route route) {
        routeMap.put(id, route);
    }

    public double calculateCost(String vehicleId, String routeId, String strategyType)
            throws RouteNotFoundException {

        Vehicle v = vehicleMap.get(vehicleId);
        Route r = routeMap.get(routeId);

        if (v == null)
            throw new RouteNotFoundException("Vehicle not found: " + vehicleId);

        if (r == null)
            throw new RouteNotFoundException("Route not found: " + routeId);

        RoutePlanner planner = PlannerFactory.getPlanner(strategyType);

        if (planner == null)
            throw new IllegalArgumentException("Invalid strategy type: " + strategyType);

        return planner.calculateCost(v, r);
    }

    public Route findCheapestRoute(String vehicleId, String strategyType)
            throws RouteNotFoundException {

        Vehicle v = vehicleMap.get(vehicleId);

        if (v == null)
            throw new RouteNotFoundException("Vehicle not found: " + vehicleId);

        RoutePlanner planner = PlannerFactory.getPlanner(strategyType);

        if (planner == null)
            throw new IllegalArgumentException("Invalid strategy type: " + strategyType);

        PriorityQueue<Route> pq = new PriorityQueue<>(
            (a, b) -> Double.compare(
                planner.calculateCost(v, a),
                planner.calculateCost(v, b)
            )
        );

        pq.addAll(routeMap.values());

        return pq.peek();
    }
}
