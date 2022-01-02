package cn.kizzzy.image;

public interface PixelConverterSelector {
    
    PixelConverter select(int type);
}
