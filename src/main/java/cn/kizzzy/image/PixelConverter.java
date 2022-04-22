package cn.kizzzy.image;

import cn.kizzzy.io.FullyReader;

import java.io.IOException;

public interface PixelConverter {
    
    int getRow();
    
    int getCol();
    
    int[] read(FullyReader reader) throws IOException;
}
