package cn.kizzzy.image.vfs.handler;

import cn.kizzzy.image.GifFile;
import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;
import com.madgag.gif.fmsware.AnimatedGifEncoder;
import com.madgag.gif.fmsware.GifDecoder;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.OutputStream;

public class GifFileHandlerOfMadgag implements IFileHandler<GifFile> {
    
    public GifFileHandlerOfMadgag() {
    
    }
    
    @Override
    public GifFile load(IPackage vfs, String path, IFullyReader reader, long size) throws Exception {
        try (InputStream in = reader.asInputStream()) {
            GifDecoder d = new GifDecoder();
            d.read(reader.asInputStream());
            
            GifFile file = new GifFile();
            file.delays = new int[d.getFrameCount()];
            file.frames = new BufferedImage[d.getFrameCount()];
            for (int i = 0, n = d.getFrameCount(); i < n; i++) {
                file.delays[i] = d.getDelay(i);
                file.frames[i] = d.getFrame(i);
            }
            return file;
        }
    }
    
    @Override
    public boolean save(IPackage vfs, String path, IFullyWriter writer, GifFile data) throws Exception {
        try (OutputStream os = writer.asOutputStream()) {
            AnimatedGifEncoder encoder = new AnimatedGifEncoder();
            encoder.start(writer.asOutputStream());
            encoder.setRepeat(data.loop);
            for (int i = 0, n = data.frames.length; i < n; ++i) {
                if (i == 0) {
                    encoder.setDelay(data.delays[i]);
                }
                encoder.addFrame(data.frames[i]);
            }
            encoder.finish();
        }
        return true;
    }
}
