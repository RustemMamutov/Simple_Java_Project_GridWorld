import info.gridworld.actor.Actor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SubwayStation extends Actor {

    private List<Passenger> passengers;

    public SubwayStation() {
        passengers = new ArrayList<>();
        this.setColor(Color.LIGHT_GRAY);
    }

    public SubwayStation(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
