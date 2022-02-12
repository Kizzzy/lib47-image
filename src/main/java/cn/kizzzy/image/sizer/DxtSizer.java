package cn.kizzzy.image.sizer;

import cn.kizzzy.image.Sizer;

public class DxtSizer implements Sizer {
    
    private int unit;
    
    public DxtSizer(int unit) {
        this.unit = unit;
    }
    
    public int calc(int width, int height) {
        int blockX = (int) Math.ceil(width / 4f);
        int blockY = (int) Math.ceil(height / 4f);
        return blockX * blockY * 16 / unit;
    }
}
