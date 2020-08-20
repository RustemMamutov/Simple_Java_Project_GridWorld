import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int rows = 9;
        int cols = 17;
        Grid<Actor> grid = new BoundedGrid<>(rows, cols);
        ActorWorld world = new ActorWorld(grid);
        Location location = new Location(0,0);

        world.add(location, new Train());
        List<Integer> stationList = Arrays.asList(1, 2, 3, 4);
        world.add(new Location(1, cols/2),
                new SubwayStation(1, Utils.generateRandomCountOfPassengers(100, 1, stationList)));
        world.add(new Location(rows/2, cols - 2),
                new SubwayStation(2, Utils.generateRandomCountOfPassengers(100, 2, stationList)));
        world.add(new Location(rows - 2, cols/2),
                new SubwayStation(3, Utils.generateRandomCountOfPassengers(100, 3, stationList)));
        world.add(new Location(rows/2, 1),
                new SubwayStation(4, Utils.generateRandomCountOfPassengers(100, 4, stationList)));
        world.show();
    }
}
