package game.maps.rooms;

import lombok.Getter;

@Getter
public class RoomBoundaries {
    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;

    RoomBoundaries(int xmin, int xmax, int ymin, int ymax){
        xMin = xmin;
        xMax = xmax;
        yMin = ymin;
        yMax = ymax;
    }

}
