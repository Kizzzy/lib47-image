package cn.kizzzy.image.sizer;

import cn.kizzzy.image.Sizer;

public class DxtSizer implements Sizer {
    
    private int unit;
    
    public DxtSizer(int unit) {
        this.unit = unit;
    }
    
    public int calc(int width, int height) {
        return find2n(width) * find2n(height) * unit;
    }
    
    private int find2n(int target) {
        int temp = target - 1;
        for (int shift : new int[]{
            1,
            2,
            4,
            8,
            16
        }) {
            temp |= temp >> shift;
        }
        return (temp < 0) ? 1 : temp + 1;
    }
}
