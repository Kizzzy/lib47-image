package cn.kizzzy.image;

import cn.kizzzy.vfs.stream.HolderInputStreamGetter;

import java.awt.image.BufferedImage;

public class GifFile extends HolderInputStreamGetter {
    
    public int loop;
    
    public int[] delays;
    
    public BufferedImage[] frames;
}
