package cn.kizzzy.image.converter;

public class ARGB8888PixelConverter extends AbstractPixelConverter {
    
    public ARGB8888PixelConverter() {
        super(4, 1, 1);
    }
    
    @Override
    protected int[] readImpl(int[] temp) {
        return new int[]{merge(temp)};
    }
}
