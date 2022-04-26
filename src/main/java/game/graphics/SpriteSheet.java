package game.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage spriteSheet;
    private static final int titleWidth = 32;
    private static final int titleHeight = 32;

    public SpriteSheet(BufferedImage buffImg) {
        spriteSheet = buffImg;
    }

    public BufferedImage crop(int x, int y) {
        return spriteSheet.getSubimage(x * titleWidth, y * titleHeight, titleWidth, titleHeight);
    }
    public BufferedImage objectCrop(int x, int y) {
        return spriteSheet.getSubimage(x * 64, y * 64, 64, 64);
    }
    public BufferedImage objectCropx64(int x, int y, float wi, float hi) {
        return spriteSheet.getSubimage(x * 64, y * 64, (int) (64*wi), (int)(64*hi));
    }
    public BufferedImage objectCropx32(int x, int y, float wi, float hi) {
        return spriteSheet.getSubimage(x * 32, y * 32, (int) (32*wi), (int)(32*hi));
    }

}
