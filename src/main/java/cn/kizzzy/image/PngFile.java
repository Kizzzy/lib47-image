package cn.kizzzy.image;

import cn.kizzzy.vfs.IInputStreamGetter;
import cn.kizzzy.vfs.stream.HolderInputStreamGetter;

import java.util.LinkedList;
import java.util.List;

public class PngFile extends HolderInputStreamGetter {
    
    public byte[] signature;
    
    public List<Chunk> chunks = new LinkedList<>();
    
    public static class Chunk {
        
        public int length;
        
        public int type;
        
        public IInputStreamGetter data;
        
        public long crc;
    }
    
    public static class IHDR extends Chunk {
        
        public int width;
        
        public int height;
        
        public byte bits;
        
        public byte colorType;
        
        public byte compressMethod;
        
        public byte filterMethod;
        
        public byte interlaceMethod;
    }
    
    public static class IDAT extends Chunk {
    
    }
    
    public static class IEND extends Chunk {
    
    }
    
    public static class gAMA extends Chunk {
    
    }
    
    public static class sRGB extends Chunk {
    
    }
    
    public static class cHRM extends Chunk {
    
    }
    
    public static class bKGD extends Chunk {
    
    }
    
    public static class pHYs extends Chunk {
    
    }
}
