package cn.kizzzy.image.creator;

import cn.kizzzy.image.ImageCreator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.Raster;

public class BufferedImageCallback implements ImageCreator.Callback<BufferedImage> {
    
    @Override
    public BufferedImage invoke(int[] data, int width, int height) throws Exception {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        image.setData(Raster.createRaster(image.getSampleModel(), new DataBufferInt(data, data.length), new Point()));
        return image;
    }
}
