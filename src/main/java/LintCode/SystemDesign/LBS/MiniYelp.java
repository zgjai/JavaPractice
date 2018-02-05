package LintCode.SystemDesign.LBS;

import java.util.List;

/**
 * Design a simple yelp system. Support the following features:
 *
 * Add a restaurant with name and location. Remove a restaurant with id. Search the nearby restaurants by given
 * location. A location is represented by latitude and longitude, both in double. A Location class is given in code.
 *
 * Nearby is defined by distance smaller than k Km .
 *
 * Restaurant class is already provided and you can directly call Restaurant.create() to create a new object. Also, a
 * Helper class is provided to calculate the distance between two location, use Helper.get_distance(location1,
 * location2).
 *
 * A GeoHash class is provided to convert a location to a string. Try GeoHash.encode(location) to convert location to a
 * geohash string and GeoHash.decode(string) to convert a string to location.
 */

class Location {
    public double latitude, longitude;

    public static Location create(double lati, double longi) {
        // This will create a new location object
        return null;
    }
}

class Restaurant {
    public int id;
    public String name;
    public Location location;

    public static Restaurant create(String name, Location location) {
        // This will create a new restaurant object,
        // and auto fill id
        return null;
    }
}

/*
 * class Helper { public static void get_distance(Location location1, Location location2) { // return distance between
 * location1 and location2. } }
 */

class GeoHash {
    public static String encode(Location location) {
        // return convert location to a GeoHash string
        return null;
    }

    public static Location decode(String hashcode) {
        // return convert a GeoHash string to location
        return null;
    }
}

public class MiniYelp {
    public MiniYelp() {
        // initialize your data structure here.
    }

    // @param name a string
    // @param location a Location
    // @return an integer, restaurant's id
    public int addRestaurant(String name, Location location) {
        // Write your code here
        return 0;
    }

    // @param restaurant_id an integer
    public void removeRestaurant(int restaurant_id) {
        // Write your code here
    }

    // @param location a Location
    // @param k an integer, distance smaller than k miles
    // @return a list of restaurant's name and sort by
    // distance from near to far.
    public List<String> neighbors(Location location, double k) {
        // Write your code here
        return null;
    }
}
