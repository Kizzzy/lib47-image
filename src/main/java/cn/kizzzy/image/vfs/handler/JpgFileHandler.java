package cn.kizzzy.image.vfs.handler;

import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class JpgFileHandler implements IFileHandler<BufferedImage> {
    
    private final float quality;
    
    public JpgFileHandler(float quality) {
        this.quality = quality;
    }
    
    @Override
    public BufferedImage load(IPackage vfs, String path, IFullyReader reader, long size) throws Exception {
        return ImageIO.read(reader.asInputStream());
    }
    
    @Override
    public boolean save(IPackage vfs, String path, IFullyWriter writer, BufferedImage data) throws Exception {
        try {
            JPEGImageEncoder jencoder = JPEGCodec.createJPEGEncoder(writer.asOutputStream());
            JPEGEncodeParam param = jencoder.getDefaultJPEGEncodeParam(data);
            param.setQuality(quality, true);
            jencoder.setJPEGEncodeParam(param);
            jencoder.encode(data);
            return true;
        } catch (ImageFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
