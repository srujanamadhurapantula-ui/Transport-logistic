package com.klu.tls.model;

public class Route {

    private String id;
    private double distance;
    private double duration;
    private int trafficLevel;

   
    public Route(String id, double distance, double duration, int trafficLevel) {
        this.id = id;
        this.distance = distance;
        this.duration = duration;
        this.trafficLevel = trafficLevel;
    }

    
    public String getId() {
        return id;
    }

    public double getDistance() {
        return distance;
    }

    public double getDuration() {
        return duration;
    }

    public int getTrafficLevel() {
        return trafficLevel;
    }
}
