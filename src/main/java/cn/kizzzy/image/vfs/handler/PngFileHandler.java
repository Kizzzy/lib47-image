package cn.kizzzy.image.vfs.handler;

import cn.kizzzy.helper.ByteHelper;
import cn.kizzzy.image.PngFile;
import cn.kizzzy.io.IFullyReader;
import cn.kizzzy.io.IFullyWriter;
import cn.kizzzy.vfs.IFileHandler;
import cn.kizzzy.vfs.IPackage;
import cn.kizzzy.vfs.stream.SliceInputStreamGetter;

public class PngFileHandler implements IFileHandler<PngFile> {
    
    private static final byte[] SIGNATURE = new byte[]{(byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A};
    
    @Override
    public PngFile load(IPackage vfs, String path, IFullyReader reader, long size) throws Exception {
        PngFile pngFile = new PngFile();
        pngFile.signature = reader.readBytes(8);
        if (!ByteHelper.equals(pngFile.signature, SIGNATURE)) {
            return null;
        }
        while (reader.position() < size) {
            PngFile.Chunk chunk = new PngFile.Chunk();
            chunk.length = reader.readInt();
            chunk.type = reader.readInt();
            chunk.data = new SliceInputStreamGetter(pngFile, reader.position(), chunk.length);
            chunk.crc = reader.readUnsignedInt();
        }
        return pngFile;
    }
    
    @Override
    public boolean save(IPackage vfs, String path, IFullyWriter writer, PngFile data) throws Exception {
        writer.writeBytes(SIGNATURE);
        for (PngFile.Chunk chunk : data.chunks) {
            writer.writeInt(chunk.length);
            writer.writeInt(chunk.type);
            if (chunk.data != null) {
                try (IFullyReader reader = chunk.data.getInput()) {
                    reader.copyTo(writer);
                }
            }
            
            writer.writeUnsignedInt(chunk.crc);
        }
        return true;
    }
}
