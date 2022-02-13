package cn.kizzzy.image.sizer;

import cn.kizzzy.image.Sizer;

public class AbstractSizer implements Sizer {
    
    protected final int count;
    
    protected final int row;
    
    protected final int col;
    
    public AbstractSizer(int count, int row, int col) {
        this.count = count;
        this.row = row;
        this.col = col;
    }
    
    @Override
    public int calc(int width, int height) {
        int blockX = (int) Math.ceil(1f * width / row);
        int blockY = (int) Math.ceil(1f * height / col);
        return blockX * blockY * count;
    }
}
