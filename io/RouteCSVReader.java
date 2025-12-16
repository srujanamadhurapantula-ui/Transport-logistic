package com.klu.tls.io;

import com.klu.tls.model.Route;
import java.util.ArrayList;
import java.util.List;

public class RouteCSVReader {

    public static List<Route> readRoutes() {
        List<Route> routes = new ArrayList<>();

       
        routes.add(new Route("R1", 120, 5.0, 3));
        routes.add(new Route("R2", 200, 3.5, 4));
        routes.add(new Route("R3", 150, 4.0, 5));

        return routes;
    }
}
