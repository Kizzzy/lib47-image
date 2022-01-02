package cn.kizzzy.image.converter;

public class ARGB0565PixelConverter extends AbstractPixelConverter {
    
    public ARGB0565PixelConverter() {
        super(2, 1, 1);
    }
    
    @Override
    protected int[] readImpl(int[] temp) {
        int value = merge(temp[0], temp[1]);
        int b = calc(value, 0, 5);
        int g = calc(value, 5, 6);
        int r = calc(value, 11, 5);
        int a = 255;
        
        return new int[]{merge(b, g, r, a)};
    }
}
