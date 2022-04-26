package game.items;

import game.RefLinks;
import game.collision.Collision;
import game.graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Health extends Item {
    private BufferedImage image;
    public boolean used = false;
    public boolean spawn = false;

    public Health(RefLinks refLink, float x, float y, int width, int height) {
        super(refLink, x, y, width, height);
        image = Assets.lifePowerup;
    }

    @Override
    public void Update() {

    }

    public void pickedUp(Player player) {
        if (Collision.AABB(player.getBounds(), bounds) && !used && Player.playerLife < 9) {
            used = true;
            x = -300;
            bounds.x = -300;
            Player.playerLife += 1;
            System.out.println("life picked up");
        }
    }

    @Override
    public void Draw(Graphics g) {
        if (!used)
            g.drawImage(image, (int) x, (int) y, width, height, null);

    }
}
