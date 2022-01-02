package cn.kizzzy.image.converter;

public class ARGB1555PixelConverter extends AbstractPixelConverter {
    
    public ARGB1555PixelConverter() {
        super(2, 1, 1);
    }
    
    @Override
    protected int[] readImpl(int[] temp) {
        int value = merge(temp[0], temp[1]);
        int b = calc(value, 0, 5);
        int g = calc(value, 5, 5);
        int r = calc(value, 10, 5);
        int a = calc(value, 15, 1, false);
        
        a = (a != 0 ? 255 : 0);
        
        return new int[]{merge(b, g, r, a)};
    }
}
