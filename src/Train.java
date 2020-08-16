import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.List;

public class Train extends Actor {

    private int stepsWait = 0;
    private SubwayStation nearSubwayStation;

    private static final int DEFAULT_MAX_PASS_COUNT = 100;
    private final int MAX_PASS_COUNT;
    private final List<Passenger> passengers;

    public Train() {
        this(DEFAULT_MAX_PASS_COUNT);
    }

    public Train(int MAX_PASS_COUNT) {
        this.MAX_PASS_COUNT = MAX_PASS_COUNT;
        this.passengers = new ArrayList<>();
    }

    public void act() {
        findNearSubwayStation();
        if (stepsWait == 0 && nearSubwayStation != null){
            dropPassengers();
            takePassengers();
            stepsWait++;
            nearSubwayStation = null;
            return;
        }

        stepsWait = 0;
        if (this.canMove()) {
            this.move();
        } else {
            this.turn();
        }
    }

    public void move() {
        Grid<Actor> gr = this.getGrid();
        if (gr != null) {
            Location loc = this.getLocation();
            Location next = loc.getAdjacentLocation(this.getDirection());
            if (gr.isValid(next)) {
                this.moveTo(next);
            } else {
                this.removeSelfFromGrid();
            }
        }
    }

    public void turn() {
        this.setDirection(this.getDirection() + 90);
    }

    public boolean canMove() {
        Grid<Actor> gr = this.getGrid();
        if (gr == null) {
            return false;
        } else {
            Location loc = this.getLocation();
            Location next = loc.getAdjacentLocation(this.getDirection());
            if (!gr.isValid(next)) {
                return false;
            } else {
                return gr.get(next) == null;
            }
        }
    }

    private void findNearSubwayStation(){
        Location currentLoc = this.getLocation();
        List<Actor> neighbours = this.getGrid().getNeighbors(currentLoc);

        if (neighbours.size() == 0) {
            return;
        }

        int currentCol = currentLoc.getCol();
        int currentRow = currentLoc.getRow();
        for (Actor actor : neighbours) {
            Location actorLoc = actor.getLocation();
            if (actorLoc.getCol() == currentCol || actorLoc.getRow() == currentRow) {
                if (actor instanceof SubwayStation) {
                    this.nearSubwayStation = (SubwayStation) actor;
                    return;
                }
            }
        }
    }

    private void dropPassengers(){
        List<Passenger> dropped = new ArrayList<>();
        for (Passenger passenger : this.passengers) {
            if (passenger.getDestinationStation() == this.nearSubwayStation.getNumber()) {
                dropped.add(passenger);
            }
        }

        this.passengers.removeAll(dropped);
    }

    private void takePassengers(){
        int countToTake = MAX_PASS_COUNT - this.passengers.size();
        List<Passenger> passengersToTake = this.nearSubwayStation.getPassengers().subList(0, countToTake);
        this.nearSubwayStation.getPassengers().removeAll(passengersToTake);
        this.passengers.addAll(passengersToTake);
    }
}
