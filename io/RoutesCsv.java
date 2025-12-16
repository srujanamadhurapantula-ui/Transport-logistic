package com.klu.tls.io;

import com.klu.tls.model.Route;

import java.io.*;
import java.util.*;

public class RoutesCsv {

    private static final String FILE_NAME = "routes.csv";

    
    public static List<Route> loadRoutes() {
        List<Route> routes = new ArrayList<>();

        File file = new File(FILE_NAME);
        if (!file.exists())
            return routes;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                String id = data[0];
                double distance = Double.parseDouble(data[1]);
                double duration = Double.parseDouble(data[2]);
                int traffic = Integer.parseInt(data[3]);

                routes.add(new Route(id, distance, duration, traffic));
            }

        } catch (Exception e) {
            System.out.println("Error reading routes CSV: " + e.getMessage());
        }

        return routes;
    }

   
    public static void saveRoutes(List<Route> routes) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {

            for (Route r : routes) {
                pw.println(
                    r.getId() + "," +
                    r.getDistance() + "," +
                    r.getDuration() + "," +
                    r.getTrafficLevel()
                );
            }

        } catch (Exception e) {
            System.out.println("Error saving routes CSV: " + e.getMessage());
        }
    }
}
