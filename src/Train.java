import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Train extends Bug {

    @Override
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

    @Override
    public void turn() {
        this.setDirection(this.getDirection() + 90);
    }
}
