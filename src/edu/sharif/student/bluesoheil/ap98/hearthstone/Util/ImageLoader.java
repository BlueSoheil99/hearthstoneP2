package edu.sharif.student.bluesoheil.ap98.hearthstone.Util;

import sun.nio.ch.FileKey;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    public static BufferedImage loadImage(String path){
        BufferedImage image = null;
        try {
//            image = ImageIO.read(ImageLoader.class.getResource(path));
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
