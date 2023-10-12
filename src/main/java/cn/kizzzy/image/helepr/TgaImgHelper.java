package cn.kizzzy.image.helepr;

import cn.kizzzy.image.ImageCreator;
import cn.kizzzy.image.TgaFile;
import cn.kizzzy.image.creator.BufferedImageCallback;
import cn.kizzzy.image.creator.TgaImageCreator;

import java.awt.image.BufferedImage;

public class TgaImgHelper {
    
    private static final ImageCreator<TgaFile, BufferedImage> creator
        = new TgaImageCreator();
    
    public static BufferedImage toImage(TgaFile tgaFile) throws Exception {
        return creator.Create(tgaFile, new BufferedImageCallback());
    }
}
