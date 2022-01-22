package cn.kizzzy.image.sizer;

import cn.kizzzy.image.Sizer;

public class ARGBSizer implements Sizer {
    
    private int unit;
    
    public ARGBSizer(int unit) {
        this.unit = unit;
    }
    
    @Override
    public int calc(int width, int height) {
        return width * height * unit;
    }
}
