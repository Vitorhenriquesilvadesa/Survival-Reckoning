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
    public SpriteSheet(String path, int row,int tileSize,int offSet) {
        BufferedImage image = null;
        sprites = new ArrayList<>();
        try {
            image = ImageIO.read(new FileInputStream(path + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        width = image.getWidth();
        height = image.getHeight();

        int spriteW = 0;
        int spriteH = row;

        assert width % tileSize == 0;
        assert height % tileSize == 0;
        while(!spriteSheetEnd){
            if(!isTransparent(image,spriteW + tileSize, spriteH + tileSize, tileSize)){
                createSprite(spriteW,spriteH,tileSize,image, offSet);
                spriteW += tileSize;
                if(spriteW == width){
                    spriteSheetEnd = true;
                }
                if(spriteW  >= width){
                    spriteSheetEnd = true;
                }
            }else{
                spriteSheetEnd = true;
            }
        }

    }

    private void createSprite(int spriteWidth,int spriteHeight,int tileSize,BufferedImage image,int offSet){
        sprites.add(new Sprite(Engine.fromService(GraphicsAPI.class).loadTexture(image, ImageFilter.POINT, spriteWidth, spriteHeight, tileSize)));
    }
    /**
     * Verify if the image is transparent
     * **/
    public boolean isTransparent(BufferedImage image, int spriteWidth,int spriteHeight,int tileSize){
        for (int y = spriteHeight - tileSize; y < spriteHeight; y++) {
            for (int x = spriteWidth - tileSize; x < spriteWidth; x++) {
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
