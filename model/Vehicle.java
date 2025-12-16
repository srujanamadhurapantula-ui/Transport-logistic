package com.klu.tls.model;

public abstract class Vehicle {

    protected String id;
    protected double capacity;
    protected double costPerKm;

    public Vehicle(String id, double capacity, double costPerKm) {
        this.id = id;
        this.capacity = capacity;
        this.costPerKm = costPerKm;
    }

    public String getId() {
        return id;
    }

    public double getCostPerKm() {
        return costPerKm;
    }

   
    public double computeFuelCost(double distance) {
        return distance * costPerKm;
    }

	public double getCapacity() {
		
		return capacity;
	}
}
