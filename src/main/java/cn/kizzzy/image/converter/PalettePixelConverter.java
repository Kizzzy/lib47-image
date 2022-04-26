package cn.kizzzy.image.converter;

public class PalettePixelConverter extends AbstractPixelConverter {
    
    private final byte[] paletteData;
    
    public PalettePixelConverter(byte[] paletteData) {
        super(1, 1, 1);
        this.paletteData = paletteData;
    }
    
    @Override
    protected int[] readImpl(int[] temp) {
        int index = temp[0] * 4;
        byte b = paletteData[index + 2];
        byte g = paletteData[index + 1];
        byte r = paletteData[index + 0];
        byte a = paletteData[index + 3];
        return new int[]{merge(b, g, r, a)};
    }
}
