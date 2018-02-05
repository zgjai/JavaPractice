package LintCode.SystemDesign.MessageSystem_RateLimiter;

/**
 * Design a parking lot.
 * 
 * see CC150 OO Design for details. 1) n levels, each level has m rows of spots and each row has k spots.So each level
 * has m x k spots. 2) The parking lot can park motorcycles, cars and buses 3) The parking lot has motorcycle spots,
 * compact spots, and large spots 4) Each row, motorcycle spots id is in range [0,k/4)(0 is included, k/4 is not
 * included), compact spots id is in range [k/4,k/4*3) and large spots id is in range [k/4*3,k). 5) A motorcycle can
 * park in any spot 6) A car park in single compact spot or large spot 7) A bus can park in five large spots that are
 * consecutive and within same row. it can not park in small spots
 */
// enum type for Vehicle
enum VehicleSize {
    Motorcycle, Compact, Large,
}

abstract class Vehicle {
    // Write your code here
}

class Motorcycle extends Vehicle {
    // Write your code here
}

class Car extends Vehicle {
    // Write your code here
}

class Bus extends Vehicle {
    // Write your code here
}

/* Represents a level in a parking garage */
class Level {
    // Write your code here
}

public class ParkingLot {

    // @param n number of leves
    // @param num_rows each level has num_rows rows of spots
    // @param spots_per_row each row has spots_per_row spots
    public ParkingLot(int n, int num_rows, int spots_per_row) {
        // Write your code here
    }

    // Park the vehicle in a spot (or multiple spots)
    // Return false if failed
    public boolean parkVehicle(Vehicle vehicle) {
        // Write your code here
        return false;
    }

    // unPark the vehicle
    public void unParkVehicle(Vehicle vehicle) {
        // Write your code here
    }
}
