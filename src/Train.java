import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.List;

public class Train extends Actor {

    private int stepsWait = 0;
    private SubwayStation nearSubwayStation;

    public void act() {
        findNearSubwayStation();
        if (stepsWait == 0 && nearSubwayStation != null){
            doSomethingWithPassengers();
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

    private void doSomethingWithPassengers(){
        System.out.println("hello world");
    }
}
