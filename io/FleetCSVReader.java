package com.klu.tls.io;

import com.klu.tls.model.Vehicle;
import com.klu.tls.model.Truck;
import com.klu.tls.model.Van;
import java.util.ArrayList;
import java.util.List;

public class FleetCSVReader {

    public static List<Vehicle> readFleet() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Truck("T1", 4.5, 10000)); 
        vehicles.add(new Van("V1", 6.0, 2000));    
        return vehicles;
    }
}
