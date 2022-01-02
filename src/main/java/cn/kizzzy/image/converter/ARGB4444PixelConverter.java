package cn.kizzzy.image.converter;

public class ARGB4444PixelConverter extends AbstractPixelConverter {
    
    public ARGB4444PixelConverter() {
        super(2, 1, 1);
    }
    
    @Override
    protected int[] readImpl(int[] temp) {
        int value = merge(temp[0], temp[1]);
        int b = calc(value, 0, 4);
        int g = calc(value, 4, 4);
        int r = calc(value, 8, 4);
        int a = calc(value, 12, 4);
        
        return new int[]{merge(b, g, r, a)};
    }
}
