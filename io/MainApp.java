package com.klu.tls.io;

import com.klu.tls.model.*;
import com.klu.tls.strategy.RoutePlanner;
import com.klu.tls.Factory.PlannerFactory;

import java.util.*;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Vehicle> vehicles = VehicleCsv.loadVehicles();
        List<Route> routes = RoutesCsv.loadRoutes();

        boolean exit = false;

        while (!exit) {

            System.out.println("\n--- Transport Menu ---");
            System.out.println("1. Add Vehicle");
            System.out.println("2. Add Route");
            System.out.println("3. Calculate Cost for specific vehicle & route");
            System.out.println("4. Display all routes cost & find cheapest");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                
                case 1:
                    System.out.print("Enter type (Truck/Van): ");
                    String type = sc.nextLine();

                    System.out.print("Enter ID: ");
                    String vid = sc.nextLine();

                    System.out.print("Enter capacity: ");
                    double capacity = sc.nextDouble();

                    System.out.print("Enter cost per km: ");
                    double costKm = sc.nextDouble();
                    sc.nextLine();

                    if (type.equalsIgnoreCase("Truck"))
                        vehicles.add(new Truck(vid, capacity, costKm));
                    else if (type.equalsIgnoreCase("Van"))
                        vehicles.add(new Van(vid, capacity, costKm));
                    else {
                        System.out.println("Unknown vehicle type!");
                        break;
                    }

                    VehicleCsv.saveVehicles(vehicles);
                    System.out.println("Vehicle added and saved to CSV.");
                    break;

                
                case 2:
                    System.out.print("Enter Route ID: ");
                    String rid = sc.nextLine();

                    System.out.print("Enter distance: ");
                    double dist = sc.nextDouble();

                    System.out.print("Enter duration: ");
                    double dur = sc.nextDouble();

                    System.out.print("Enter traffic level: ");
                    int traffic = sc.nextInt();
                    sc.nextLine();

                    
                    routes.add(new Route(rid, dist, dur, traffic));

                    RoutesCsv.saveRoutes(routes);
                    System.out.println("Route added and saved to CSV.");
                    break;

                
                case 3:
                    System.out.print("Enter Vehicle ID: ");
                    String vId = sc.nextLine();

                    System.out.print("Enter Route ID: ");
                    String rId = sc.nextLine();

                    System.out.print("Enter Planner type (standard/eco/peak): ");
                    String plannerType = sc.nextLine().toLowerCase();

                    Vehicle v = vehicles.stream()
                            .filter(veh -> veh.getId().equals(vId))
                            .findFirst()
                            .orElse(null);

                    Route r = routes.stream()
                            .filter(rt -> rt.getId().equals(rId))
                            .findFirst()
                            .orElse(null);

                    if (v == null || r == null) {
                        System.out.println("Vehicle or Route not found!");
                        break;
                    }

                    RoutePlanner planner = PlannerFactory.getPlanner(plannerType);

                    
                    if (planner == null) {
                        System.out.println("Invalid planner type!");
                        break;
                    }

                    double cost = planner.calculateCost(v, r);
                    System.out.println("Cost using " + plannerType + ": " + cost);
                    break;

               
                case 4:
                    if (vehicles.isEmpty() || routes.isEmpty()) {
                        System.out.println("No vehicles or routes available.");
                        break;
                    }

                    System.out.println("\nAvailable vehicles:");
                    for (Vehicle ve : vehicles) {
                        System.out.println("- " + ve.getId());
                    }

                    System.out.print("Select Vehicle ID: ");
                    String selectedVId = sc.nextLine();

                    Vehicle vehicle = vehicles.stream()
                            .filter(veh -> veh.getId().equals(selectedVId))
                            .findFirst()
                            .orElse(null);

                    if (vehicle == null) {
                        System.out.println("Vehicle not found!");
                        break;
                    }

                    double minCost = Double.MAX_VALUE;
                    Route cheapestRoute = null;
                    String cheapestStrategy = "";

                   
                    String[] strategyTypes = {"standard", "eco", "peak"};

                    for (Route route : routes) {

                        for (String strategyName : strategyTypes) {

                            RoutePlanner rp = PlannerFactory.getPlanner(strategyName);

                            if (rp == null)
                                continue;

                            double c = rp.calculateCost(vehicle, route);

                            if (c < minCost) {
                                minCost = c;
                                cheapestRoute = route;
                                cheapestStrategy = strategyName;
                            }
                        }
                    }

                    System.out.println("\n--- Cheapest Route Overall ---");
                    if (cheapestRoute != null) {
                        System.out.println("Route ID: " + cheapestRoute.getId());
                        System.out.println("Strategy: " + cheapestStrategy);
                        System.out.println("Cost: " + minCost);
                    }
                    break;

         
                case 5:
                    exit = true;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}
