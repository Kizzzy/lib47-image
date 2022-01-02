package cn.kizzzy.image;

import cn.kizzzy.io.DataInputStreamEx;

import java.io.IOException;

public interface PixelConverter {
    
    int getRow();
    
    int getCol();
    
    int[] read(DataInputStreamEx reader) throws IOException;
}
