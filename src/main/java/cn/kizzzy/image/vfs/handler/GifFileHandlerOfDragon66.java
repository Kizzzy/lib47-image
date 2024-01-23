package cn.kizzzy.image.vfs.handler;

import cn.kizzzy.image.GifFile;
import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;
import com.github.dragon66.AnimatedGIFWriter;

import java.io.OutputStream;

public class GifFileHandlerOfDragon66 implements IFileHandler<GifFile> {
    
    public GifFileHandlerOfDragon66() {
    
    }
    
    @Override
    public GifFile load(IPackage vfs, String path, IFullyReader reader, long size) throws Exception {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean save(IPackage vfs, String path, IFullyWriter writer, GifFile data) throws Exception {
        try (OutputStream os = writer.asOutputStream()) {
            AnimatedGIFWriter gifWriter = new AnimatedGIFWriter(true);
            gifWriter.writeAnimatedGIF(data.frames, data.delays, os);
        }
        return true;
    }
}
