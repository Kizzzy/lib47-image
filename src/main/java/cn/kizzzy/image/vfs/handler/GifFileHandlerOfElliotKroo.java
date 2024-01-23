package cn.kizzzy.image.vfs.handler;

import cn.kizzzy.image.GifFile;
import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;
import net.kroo.elliot.GifSequenceWriter;

import java.io.OutputStream;

public class GifFileHandlerOfElliotKroo implements IFileHandler<GifFile> {
    
    public GifFileHandlerOfElliotKroo() {
    
    }
    
    @Override
    public GifFile load(IPackage vfs, String path, IFullyReader reader, long size) throws Exception {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean save(IPackage vfs, String path, IFullyWriter writer, GifFile data) throws Exception {
        try (OutputStream os = writer.asOutputStream()) {
            GifSequenceWriter gifWriter = null;
            for (int i = 0, n = data.frames.length; i < n; ++i) {
                if (i == 0) {
                    gifWriter = new GifSequenceWriter(null, data.frames[i].getType(), data.delays[i], data.loop == 0);
                }
                gifWriter.writeToSequence(data.frames[i]);
            }
            gifWriter.close();
        }
        return true;
    }
}
