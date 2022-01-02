package cn.kizzzy.image.converter;

public class DXT3PixelConverter extends AbstractPixelConverter {
    
    public DXT3PixelConverter() {
        super(16, 4, 4);
    }
    
    @Override
    protected int[] readImpl(int[] temp) {
        return dxt3(temp);
    }
    
    private int[] dxt3(int[] arr) {
        int[] codecs = new int[16];
        
        int value_0 = merge(arr[8], arr[9]);
        int value_1 = merge(arr[10], arr[11]);
        
        unpacked(value_0, codecs, 0);
        unpacked(value_1, codecs, 4);
        unpacked(codecs, false);
        
        int[] colors = new int[4];
        for (int i = 0; i < 4; ++i) {
            colors[i] = merge(codecs, i * 4);
        }
        
        int[] temp = new int[16];
        for (int i = 0; i < 16; ++i) {
            int index = (arr[i / 4 + 12] >> (i % 4 * 2)) & 0x3;
            int alpha = ((arr[i / 2] >> (i % 2 * 4)) & 0xF);
            temp[i] = colors[index] & (((alpha | (alpha << 4)) << 24) | 0xffffff);
        }
        return temp;
    }
}
