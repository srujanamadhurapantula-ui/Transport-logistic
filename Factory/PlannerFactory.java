package com.klu.tls.Factory;

import com.klu.tls.strategy.*;

public class PlannerFactory {

    public static RoutePlanner getPlanner(String type) {

        if (type == null)
            return null;

        switch (type.toLowerCase()) {

            case "distance":
            case "standard":
                return new StandardCost();

            case "time":
            case "traffic":
            case "peak":
            case "peakhour":
                return new PeakHourCost();

            case "eco":
                return new EcoModeCost();

            default:
                return null;
        }
    }
}
