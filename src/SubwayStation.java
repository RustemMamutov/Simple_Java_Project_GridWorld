import info.gridworld.actor.Actor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SubwayStation extends Actor {

    private final int number;
    private List<Passenger> passengers;

    public SubwayStation(int number) {
        this(number, new ArrayList<>());
        this.setColor(Color.LIGHT_GRAY);
    }

    public SubwayStation(int number, List<Passenger> passengers) {
        this.number = number;
        this.passengers = passengers;
    }

    @Override
    public void act() {
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public int getNumber() {
        return number;
    }
}
