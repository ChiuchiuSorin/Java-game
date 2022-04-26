package game.items.enemies;

import game.RefLinks;
import game.graphics.Animation;
import game.graphics.Assets;

public class AxeOrc extends Enemy {

    public AxeOrc(RefLinks refLink, float x, float y) {
        super(refLink, x, y);
        attackImages = Assets.axeOrcAttackDead;
        moveImages = Assets.axeOrcMovement;
        anim = new Animation(5, moveImages[0]);
        anim.updateImage(moveImages[0], 15);
        life = 1;
        speed = 2;
        damage = 2;
    }
}
