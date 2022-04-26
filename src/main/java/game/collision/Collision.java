package game.collision;

import java.awt.*;

public class Collision {
    public static boolean AABB(Rectangle rect1, Rectangle rect2){
        if (rect1.y >= rect2.y + rect2.height) {
            return false;
        }
        if (rect1.x >= rect2.x + rect2.width) {
            return false;
        }
        if (rect1.y + rect1.height <= rect2.y) {
            return false;
        }
        return rect1.x + rect1.width > rect2.x;
    }
}
