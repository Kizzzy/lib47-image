package cn.kizzzy.image;

import cn.kizzzy.vfs.IInputStreamGetter;
import cn.kizzzy.vfs.stream.HolderInputStreamGetter;

public class TgaFile extends HolderInputStreamGetter {
    
    public Header header;
    
    public ImageOrColorMapData iocmd;
    
    public Footer footer;
    
    public static class Header {
        
        public int idLength;
        
        public byte colorMapType;
        
        public byte imageType;
        
        public ColorMapSpecification colorMapSpecification;
        
        public ImageSpecification imageSpecification;
    }
    
    public static class ColorMapSpecification {
        
        public int firstIndexEntry;
        
        public int colorMapLength;
        
        public int colorMapEntrySize;
    }
    
    public static class ImageSpecification {
        
        public int xOrigin;
        
        public int yOrigin;
        
        public int width;
        
        public int height;
        
        public int pixelDepth;
        
        public int imageDescriptor;
    }
    
    public static class ImageOrColorMapData {
        
        public byte[] imageIds;
        
        public IInputStreamGetter colorMapData;
        
        public IInputStreamGetter imageData;
    }
    
    public static class Footer {
        
        public long extensionAreaOffset;
        
        public long developerDirectoryOffset;
        
        public String signature;
        
        public byte endCharacter;
        
        public byte eof;
    }
}
