package com.klu.tls.io;

import com.klu.tls.model.*;

import java.io.*;
import java.util.*;

public class VehicleCsv {

    private static final String FILE_NAME = "vehicles.csv";

    
    public static List<Vehicle> loadVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("Vehicles CSV file not found. Returning empty list.");
            return vehicles;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] data = line.split(",");

                if (data.length != 4) {
                    System.out.println("Skipping invalid line " + lineNumber + ": " + line);
                    continue;
                }

                String type = data[0].trim();
                String id = data[1].trim();
                double capacity;
                double costKm;

                
                try {
                    capacity = Double.parseDouble(data[2].trim());
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid capacity at line " + lineNumber + ": " + data[2]);
                    continue; 
                }

                
                try {
                    costKm = Double.parseDouble(data[3].trim());
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid cost per km at line " + lineNumber + ": " + data[3]);
                    continue; 
                }

                
                if (type.equalsIgnoreCase("Truck")) {
                    vehicles.add(new Truck(id, capacity, costKm));
                } else if (type.equalsIgnoreCase("Van")) {
                    vehicles.add(new Van(id, capacity, costKm));
                } else {
                    System.out.println("Unknown vehicle type at line " + lineNumber + ": " + type);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading vehicles CSV: " + e.getMessage());
        }

        return vehicles;
    }

    
    public static void saveVehicles(List<Vehicle> vehicles) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Vehicle v : vehicles) {
                pw.println(
                    v.getClass().getSimpleName() + "," +
                    v.getId() + "," +
                    v.getCapacity() + "," +
                    v.getCostPerKm()
                );
            }
        } catch (IOException e) {
            System.out.println("Error saving vehicles CSV: " + e.getMessage());
        }
    }
}
