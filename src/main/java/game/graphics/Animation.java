package game.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {
    private int speed;
    private int frames;

    public boolean cycleOver = true;
    private int index = 0;
    private int count = 0;

    private BufferedImage[] images;
    private BufferedImage currentImg;


    public Animation(int speed, BufferedImage[] args) {
        this.speed = speed;
        images = new BufferedImage[args.length];
        for (int i = 0; i < args.length; i++) {
            images[i] = args[i];
        }
        frames = args.length;
    }

    public void updateImage(BufferedImage[] args){
        updateImage(args, 5);
    }

    public void updateImage(BufferedImage[] args, int speed){
        images = new BufferedImage[args.length];
        for (int i = 0; i < args.length; i++) {
            images[i] = args[i];
        }
        frames = args.length;
        this.speed = speed;
    }

    public void runAnimation() {
        index++;
        if(index > speed) {
            nextFrame();
            index = 0;
        }
    }

    public void nextFrame() {
        for (int i = 0; i < frames; i++) {
            if (count == i)
                currentImg = images[i];
        }
        count++;
        if (count > frames) {
            count = 0;
            cycleOver = true;
        }
    }
    public void runOneCycle() {
        index++;
        if(index > speed) {
            for (int i = 0; i < frames; i++) {
                if (count == i)
                    currentImg = images[i];
            }
            count++;
            if(count>frames)
                cycleOver = true;
            index = 0;
        }
    }

    public void drawAnimation(Graphics g, int x, int y) {
        g.drawImage(currentImg, x, y, null);
    }

    public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY) {
        g.drawImage(currentImg, x, y, scaleX, scaleY, null);
    }
}
