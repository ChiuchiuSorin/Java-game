package game.items;

import game.graphics.*;
import game.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Character {
    private BufferedImage[] image;
    public static float xRoom;
    public static float yRoom;
    public static float playerX;
    public static float playerY;
    public static int NOKeys = 1;
    public static int playerLife = 7;
    public static int damage = 1;
    public static int score = 0;

    private String lastInput = "";

    public Player(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);

        image = Assets.idle;
        anim = new Animation(5, image);
        normalBounds.x = 16;
        normalBounds.y = 16;
        normalBounds.width = 16;
        normalBounds.height = 32;

        xRoom = getX();
        yRoom = getY();

        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 80;
        attackBounds.height = 60;
        anim.updateImage(image, 15);
    }

    public void resetPos(){
        x = 100;
        y = 100;
        playerX = 100;
        playerY = 100;
        xRoom = 100;
        yRoom = 100;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void verifyBounds(){
        getInput();
        bounds.x = (int) x + (int) xMove;
        bounds.y = (int) yMove + (int) y;
    }

    @Override
    public void Update() {
        //verifyBounds();
        if(anim.cycleOver)
            keyboardAnimation();
        anim.runAnimation();
    }

    private void keyboardAnimation() {
        if (refLink.getKeyManager().attack && lastInput.equals("left")) {
            anim.cycleOver = false;
            image = Assets.attack[1];
            anim.updateImage(image, 2);
            attackBounds.x = (int) x - 45;
            attackBounds.y = (int) y;

        }
        else if (refLink.getKeyManager().left) {
            Move();
            image = Assets.left;
            anim.updateImage(image);
            System.out.println("("+xRoom+", "+yRoom+")");
        }
        else if (refLink.getKeyManager().attack && lastInput.equals("right")) {
            anim.cycleOver = false;
            attackBounds.y = (int) y;
            attackBounds.x = (int) x + 45;
            image = Assets.attack[3];
            anim.updateImage(image, 2);
        }
        else if (refLink.getKeyManager().right) {
            Move();
            image = Assets.right;
            anim.updateImage(image);
            System.out.println("("+xRoom+", "+yRoom+")");
        }
        else if (refLink.getKeyManager().attack && lastInput.equals("down")) {
            anim.cycleOver = false;
            attackBounds.y = (int) y + 60;
            attackBounds.x = (int) x;
            image = Assets.attack[2];
            anim.updateImage(image, 2);
        }
        else if (refLink.getKeyManager().down) {
            Move();
            image = Assets.down;
            anim.updateImage(image);
            System.out.println("("+xRoom+", "+yRoom+")");
        }
        else if (refLink.getKeyManager().attack && lastInput.equals("up")) {
            anim.cycleOver = false;
            attackBounds.y = (int) y - 30;
            attackBounds.x = (int) x;
            image = Assets.attack[0];
            anim.updateImage(image,2);
        } else if (refLink.getKeyManager().up) {
            Move();
            image = Assets.up;
            anim.updateImage(image);
            System.out.println("(" + xRoom + ", " + yRoom + ")");
        } else if(refLink.getMouseManager().isLeftPressed() || refLink.getMouseManager().isRightPressed()){
            refLink.getGame().getWnd().getWndFrame().requestFocus();
        }
        else {
            Move();
            attackBounds.y = -200;
            attackBounds.x = -200;
            image = Assets.idle;
            anim.updateImage(image, 150);
        }

    }

    public void setX(float x){
        this.x += x;
    }
    public void setY(float y){
        this.y += y;
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        if (refLink.getKeyManager().up) {
            yMove = -speed;
            lastInput = "up";
        }
        if (refLink.getKeyManager().down) {
            yMove = speed;
            lastInput = "down";
        }
        if (refLink.getKeyManager().left) {
            xMove = -speed;
            lastInput = "left";
        }
        if (refLink.getKeyManager().right) {
            xMove = speed;
            lastInput = "right";
        }
    }

    @Override
    public void Draw(Graphics g) {
        anim.drawAnimation(g, (int) x, (int) y, width, height);
//        if(!anim.cycleOver)
//            g.drawRect(attackBounds.x, attackBounds.y, attackBounds.width, attackBounds.height);
    }
}
