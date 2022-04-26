package game.items.enemies;

import game.RefLinks;
import game.graphics.Animation;
import game.graphics.Assets;

public class MailedEnemy extends Enemy {
    public MailedEnemy(RefLinks refLink, float x, float y) {
        super(refLink, x, y);
        attackImages = Assets.enemy3_attack_dead;
        moveImages = Assets.enemy3_movement;
        anim = new Animation(5, moveImages[0]);
        anim.updateImage(moveImages[0], 15);
        life = 8;
        speed = 2;
    }
}


