package game.graphics;

import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage[] left = new BufferedImage[9];
    public static BufferedImage[] right = new BufferedImage[9];
    public static BufferedImage[] up = new BufferedImage[9];
    public static BufferedImage[] down = new BufferedImage[9];
    public static BufferedImage[][] attack = new BufferedImage[4][6];
    public static BufferedImage[] idle = new BufferedImage[3];

    public static BufferedImage[][] enemy1_movement = new BufferedImage[2][9];
    public static BufferedImage[][] enemy1_attack_dead = new BufferedImage[3][6];
    public static BufferedImage[][] enemy3_movement = new BufferedImage[2][9];
    public static BufferedImage[][] enemy3_attack_dead = new BufferedImage[3][6];
    public static BufferedImage[][] axeOrcMovement = new BufferedImage[2][9];
    public static BufferedImage[][] axeOrcAttackDead = new BufferedImage[3][6];
    public static BufferedImage[][] finalBossMovement = new BufferedImage[2][9];
    public static BufferedImage[][] finalBossAttack = new BufferedImage[3][6];


    public static BufferedImage[] gate = new BufferedImage[6];
    public static BufferedImage outterWall;
    public static BufferedImage emptySpot;
    public static BufferedImage[] floor = new BufferedImage[10];
    public static BufferedImage[] grassFloor = new BufferedImage[18];
    public static BufferedImage bed;
    public static BufferedImage table_and_chairs;
    public static BufferedImage[] chest = new BufferedImage[2];
    public static BufferedImage[] fireplace = new BufferedImage[8];
    public static BufferedImage damagePower;
    public static BufferedImage damagePowerDecoration;
    public static BufferedImage arrowTrap;
    public static BufferedImage arrow;
    public static BufferedImage lifeSymbol;
    public static BufferedImage lifePowerup;
    public static BufferedImage key;
    public static BufferedImage skeleton;
    public static BufferedImage table;
    public static BufferedImage bloodFountain;
    public static BufferedImage[] bonePiles = new BufferedImage[2];
    public static BufferedImage[] numbers = new BufferedImage[10];
    public static BufferedImage[] blood = new BufferedImage[4];
    public static BufferedImage[] cauldron = new BufferedImage[4];

    public static BufferedImage[] startButton = new BufferedImage[2];
    public static BufferedImage[] saveButton = new BufferedImage[2];
    public static BufferedImage[] loadButton = new BufferedImage[2];
    public static BufferedImage endGamePhoto;
    public static BufferedImage endGameDeadPhoto;

    public static void Init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/mapSheet.png"));
        int i, j;
        bloodFountain = sheet.crop(1, 0);
        floor[0] = sheet.crop(33, 2);
        floor[1] = sheet.crop(35, 2);
        for (i = 25, j = 0; j < 18; i++, j++) {
            grassFloor[j] = sheet.crop(i, 3);
        }
        for (i = 39, j = 2; i < 56 && j < 7; i = i + 2, j++) {
            floor[j] = sheet.crop(i, 2);
        }

        for (i = 51, j = 7; i < 56 && j < 10; i = i + 2, j++) {
            floor[j] = sheet.crop(i, 2);
        }
        for (i = 47, j = 0; i < 55; i++, j++) {
            fireplace[j] = sheet.crop(i, 0);
        }
        for (i = 54, j = 0; i < 60; i++, j++) {
            gate[j] = sheet.crop(i, 1);
        }

        arrow = sheet.crop(13, 23);
        arrowTrap = sheet.crop(5, 13);
        damagePowerDecoration = sheet.crop(48, 12);
        damagePower = sheet.crop(44, 15);
        emptySpot = sheet.crop(0, 0);
        outterWall = sheet.crop(11, 14);
        chest[0] = sheet.crop(7, 0);
        chest[1] = sheet.crop(8, 0);


        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/player.png"));
        for (i = 0; i < 9; i++) {
            left[i] = sheet.objectCrop(i, 9);
            up[i] = sheet.objectCrop(i, 8);
            down[i] = sheet.objectCrop(i, 10);
            right[i] = sheet.objectCrop(i, 11);
        }
        for (i = 0; i < 6; i++) {
            attack[0][i] = sheet.objectCrop(i, 12);
            attack[1][i] = sheet.objectCrop(i, 13);
            attack[2][i] = sheet.objectCrop(i, 14);
            attack[3][i] = sheet.objectCrop(i, 15);
        }
        for (i = 0; i < 3; i++) {
            idle[i] = sheet.objectCrop(i, 2);
        }
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/dungeonex.png"));
        bed = sheet.objectCropx64(0, 1, 1, 1.5F);
        table_and_chairs = sheet.objectCropx32(2, 1, 3, 1);
        table = sheet.objectCropx32(5, 0, 3, 2);
        skeleton = sheet.objectCropx32(0, 8, 1, 2);
        bonePiles[0] = sheet.crop(1, 9);
        bonePiles[1] = sheet.crop(2, 9);

        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/enemy1.png"));
        for (i = 0; i < 9; i++) {
            enemy1_movement[0][i] = sheet.objectCrop(i, 9);
            enemy1_movement[1][i] = sheet.objectCrop(i, 11);
        }
        for (i = 0; i < 6; i++) {
            enemy1_attack_dead[0][i] = sheet.objectCrop(i, 13);
            enemy1_attack_dead[1][i] = sheet.objectCrop(i, 15);
            enemy1_attack_dead[2][i] = sheet.objectCrop(i, 20);

        }

        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/enemy3.png"));
        for (i = 0; i < 9; i++) {
            enemy3_movement[0][i] = sheet.objectCrop(i, 9);
            enemy3_movement[1][i] = sheet.objectCrop(i, 11);
        }
        for (i = 0; i < 6; i++) {
            enemy3_attack_dead[0][i] = sheet.objectCrop(i, 13);
            enemy3_attack_dead[1][i] = sheet.objectCrop(i, 15);
            enemy3_attack_dead[2][i] = sheet.objectCrop(i, 20);
        }

        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/axeOrc.png"));
        for (i = 0; i < 9; i++) {
            axeOrcMovement[0][i] = sheet.objectCrop(i, 9);
            axeOrcMovement[1][i] = sheet.objectCrop(i, 11);
        }
        for (i = 0; i < 6; i++) {
            axeOrcAttackDead[0][i] = sheet.objectCrop(i, 13);
            axeOrcAttackDead[1][i] = sheet.objectCrop(i, 15);
            axeOrcAttackDead[2][i] = sheet.objectCrop(i, 20);
        }

        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/finalBoss.png"));
        for (i = 0; i < 9; i++) {
            finalBossMovement[0][i] = sheet.objectCrop(i, 9);
            finalBossMovement[1][i] = sheet.objectCrop(i, 11);
        }
        for (i = 0; i < 6; i++) {
            finalBossAttack[0][i] = sheet.objectCrop(i, 13);
            finalBossAttack[1][i] = sheet.objectCrop(i, 15);
            finalBossAttack[2][i] = sheet.objectCrop(i, 20);
        }


        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/miscellaneous.png"));
        blood[0] = sheet.crop(11, 2);
        blood[1] = sheet.crop(16, 2);
        blood[2] = sheet.crop(21, 2);
        blood[3] = sheet.crop(12, 2);
        lifeSymbol = sheet.crop(0, 6);
        lifePowerup = sheet.crop(48, 4);

        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/allTextures.png"));
        for (i = 35, j = 0; i < 46 && j < 10; i++, j++) {
            numbers[j] = sheet.crop(i, 58);
        }
        key = sheet.crop(55, 40);


        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/boil.png"));
        for (i = 0; i < 4; i++) {
            cauldron[i] = sheet.crop(0, i);
        }

        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/StartNotPressed.png"));
        startButton[0] = sheet.objectCropx64(0,0,3,2);
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Start.png"));
        startButton[1] = sheet.objectCropx64(0,0,3,2);

        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Save.png"));
        saveButton[0] = sheet.objectCropx64(0,0,3,2);
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/SavePressed.png"));
        saveButton[1] = sheet.objectCropx64(0,0,3,2);

        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Load.png"));
        loadButton[0] = sheet.objectCropx64(0,0,3,2);
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/LoadPressed.png"));
        loadButton[1] = sheet.objectCropx64(0,0,3,2);
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/endGame.png"));
        endGamePhoto = sheet.objectCropx32(0,0,25,15);
        sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/endGameDead.png"));
        endGameDeadPhoto = sheet.objectCropx32(0,0,25,15);
    }
}


