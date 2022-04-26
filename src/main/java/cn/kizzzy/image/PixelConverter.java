package cn.kizzzy.image;

import cn.kizzzy.io.IFullyReader;

import java.io.IOException;

public interface PixelConverter {
    
    int getRow();
    
    int getCol();
    
    int[] read(IFullyReader reader) throws IOException;
}
