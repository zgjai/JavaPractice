package LintCode.SystemDesign.LBS;

/**
 * Support two basic uber features:
 * 
 * Drivers report their locations. Rider request a uber, return a matched driver. When rider request a uber, match a
 * closest available driver with him, then mark the driver not available.
 * 
 * When next time this matched driver report his location, return with the rider's information.
 * 
 * You can implement it with the following instructions:
 * 
 * report(driver_id, lat, lng) 1) return null if no matched rider. 2) return matched trip information.
 * 
 * request(rider_id, lat, lng) 1) create a trip with rider's information. 2) find a closest driver, mark this driver not
 * available. 3) fill driver_id into this trip. 4) return trip
 */
public class MiniUber {
    public MiniUber() {
        // initialize your data structure here.
    }

    // @param driver_id an integer
    // @param lat, lng driver's location
    // return matched trip information if there have matched rider or null
    public Trip report(int driver_id, double lat, double lng) {
        // Write your code here
        return null;
    }

    // @param rider_id an integer
    // @param lat, lng rider's location
    // return a trip
    public Trip request(int rider_id, double lat, double lng) {
        // Write your code here
        return null;
    }
}

class Trip {
    public int id; // trip's id, primary key
    public int driver_id, rider_id; // foreign key
    public double lat, lng; // pick up location

    public Trip(int rider_id, double lat, double lng) {

    }
}

class Helper {
    public static double get_distance(double lat1, double lng1, double lat2, double lng2) {
        // return distance between (lat1, lng1) and (lat2, lng2)
        return 0;
    }
}