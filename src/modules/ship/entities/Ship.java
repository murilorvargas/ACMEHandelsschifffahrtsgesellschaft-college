package modules.ship.entities;

import java.util.UUID;
import modules.ship.entities.interfaces.IShipReadable;

public class Ship implements Comparable<Ship>, IShipReadable {

    private String id;
    private String name;
    private double speed;
    private double autonomy;
    private double costPerMile;

    public Ship(String name, double speed, double autonomy, double costPerMile) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.speed = speed;
        this.autonomy = autonomy;
        this.costPerMile = costPerMile;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getAutonomy() {
        return autonomy;
    }

    public double getCostPerMile() {
        return costPerMile;
    }

    @Override
    public int compareTo(Ship other) {
        return this.name.compareTo(other.name);
    }

}
