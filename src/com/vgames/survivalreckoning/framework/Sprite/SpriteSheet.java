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
    private Vector2 spriteCoord[] = new Vector2[4];
    /**
     * @param row "The number of the line that the image need to be reading"
     * **/
    public SpriteSheet(String path, int sheetWidth, int sheetHeight, int row,int spriteWidth,int spriteHeight,int tileSize) {
        BufferedImage image = null;
        sprites = new ArrayList<>();
        try {
            image = ImageIO.read(new FileInputStream(path + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        width = image.getWidth();
        height = image.getHeight();

        assert width % sheetWidth == 0;
        assert height % sheetHeight == 0;

        sprites.add(new Sprite(Engine.fromService(GraphicsAPI.class).loadTexture(image,ImageFilter.POINT,spriteWidth, spriteHeight, tileSize)));
        createSprite(spriteWidth,spriteHeight,image);
//        parseSprite(image,row);
    }

    private void createSprite(int spriteWidth,int spriteHeight,BufferedImage image){
        int u_normalized = spriteWidth / image.getWidth();
        int v_normalized= spriteHeight / image.getHeight();
    }
//
//    private void parseSprite(BufferedImage image, int row){
//        int y  = row * height;
//        int column = 0;
//
//        while(width * column < image.getWidth()){
//            BufferedImage subImage = image.getSubimage(width * column,y, width,height);
//            if(!isBoundingBoxEmpty(subImage)){
//                Sprite sprite = new Sprite(Engine.fromService(GraphicsAPI.class).loadTexture(subImage,ImageFilter.LINEAR));
//                sprites.add(sprite);
//            }
//            column ++;
//        }
//    }

    private boolean isBoundingBoxEmpty(BufferedImage image){
        for(int i = 0; i < image.getWidth(); i ++){
            for(int j = 0; j < image.getHeight(); j ++){
                if((image.getRGB(i,j) & 0xFF000000) != 0X00){
                    return  false;
                }
            }
        }
        return true;
    }

    public List<Sprite> getSprites() {
        return sprites;
    }
}
