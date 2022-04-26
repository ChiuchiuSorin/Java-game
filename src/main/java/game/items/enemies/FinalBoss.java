package game.items.enemies;

import game.RefLinks;
import game.graphics.Animation;
import game.graphics.Assets;

public class FinalBoss extends Enemy{
    public FinalBoss(RefLinks refLink, float x, float y) {
        super(refLink, x, y);
        attackImages = Assets.finalBossAttack;
        moveImages = Assets.finalBossMovement;
        anim = new Animation(5, moveImages[0]);
        anim.updateImage(moveImages[0], 15);
        life = 22;
        speed = 2;
        damage = 2;
    }
}
