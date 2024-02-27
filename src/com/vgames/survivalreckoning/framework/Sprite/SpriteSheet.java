package com.vgames.survivalreckoning.framework.Sprite;

import com.vgames.survivalreckoning.framework.engine.Engine;
import com.vgames.survivalreckoning.framework.math.Vector2;
import com.vgames.survivalreckoning.framework.math.Vector3;
import com.vgames.survivalreckoning.framework.service.rendering.GraphicsAPI;
import com.vgames.survivalreckoning.framework.service.rendering.element.loader.ImageFilter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpriteSheet {
    private List<Sprite> sprites;
    private int width,height;
    private boolean spriteSheetEnd = false;
    /**
     * @param row "The number of the line that the image need to be reading"
     * **/
    public SpriteSheet(String path, int row,int spriteSize,int xOffSet,int yOffSet)
    {
        BufferedImage image = createImage(path);
        createSpriteSheet(image,0,row,spriteSize,xOffSet,yOffSet,image.getWidth()/spriteSize);
    }
    public SpriteSheet(String path, int row,int widthPosition,int spriteSize ,int frameQuantity,int xOffSet,int yOffSet) {
        BufferedImage image = createImage(path);
        row *= spriteSize + yOffSet;
        widthPosition *= (spriteSize + xOffSet);

        createSpriteSheet(image,widthPosition,row,spriteSize,xOffSet,yOffSet,frameQuantity);
    }
    private BufferedImage createImage(String path)
    {
        BufferedImage image = null;
        sprites = new ArrayList<>();
        try {
            image = ImageIO.read(new FileInputStream(path + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }
    private void createSpriteSheet(BufferedImage image,int sheetW, int sheetH,int spriteSize,int xOffSet,int yOffSet,int frameQuantity){
        width = image.getWidth();
        height = image.getHeight();

        int spriteW = sheetW;
        int spriteH = sheetH;

        assert width % spriteSize == 0;
        assert height % spriteSize == 0;

        while(!spriteSheetEnd){
            if(!isTransparent(image,spriteW + spriteSize, spriteH + spriteSize, spriteSize)){
                createSprite(spriteW, spriteH, spriteSize, image);
                spriteW += spriteSize + xOffSet;
                if(spriteW >= width || sprites.size() == frameQuantity) {
                    spriteSheetEnd = true;
                }
            }else{
                spriteSheetEnd = true;
            }
        }
    }

    private void createSprite(int spriteWidth,int spriteHeight,int spriteSize,BufferedImage image){
        sprites.add(new Sprite(Engine.fromService(GraphicsAPI.class).loadTexture(image, ImageFilter.POINT, spriteWidth, spriteHeight, spriteSize)));
    }
    /**
     * Verify if the image is transparent
     * **/
    public boolean isTransparent(BufferedImage image, int spriteWidth,int spriteHeight,int spriteSize){
        for (int y = spriteHeight - spriteSize; y < spriteHeight; y++) {
            for (int x = spriteWidth - spriteSize; x < spriteWidth; x++) {
                int pixel = image.getRGB(x, y);
                if ((pixel >> 24) != 0x00) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Sprite> getSprites() {
        return sprites;
    }
}
