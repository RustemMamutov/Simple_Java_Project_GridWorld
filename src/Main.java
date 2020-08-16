import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import static java.awt.Color.BLUE;

public class Main {
    public static void main(String[] args) {
        int rows = 9;
        int cols = 17;
        Grid<Actor> grid = new BoundedGrid<>(rows, cols);
        ActorWorld world = new ActorWorld(grid);
        Location location = new Location(0,0);

        world.add(location, new Bug(BLUE));
        world.add(new Location(rows/2, 1), new SubwayStation());
        world.add(new Location(rows/2, cols - 2), new SubwayStation());
        world.add(new Location(1, cols/2), new SubwayStation());
        world.add(new Location(rows - 2, cols/2), new SubwayStation());
        world.show();
    }
}
