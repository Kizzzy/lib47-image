package cn.kizzzy.image.converter;

public class DXT1PixelConverter extends AbstractPixelConverter {
    
    public DXT1PixelConverter() {
        super(8, 4, 4);
    }
    
    @Override
    protected int[] readImpl(int[] temp) {
        return dxt1(temp);
    }
    
    private int[] dxt1(int[] arr) {
        int[] codecs = new int[16];
        
        int value_0 = merge(arr[0], arr[1]);
        int value_1 = merge(arr[2], arr[3]);
        
        unpacked(value_0, codecs, 0);
        unpacked(value_1, codecs, 4);
        unpacked(codecs, value_0 <= value_1);
        
        int[] colors = new int[4];
        for (int i = 0; i < 4; ++i) {
            colors[i] = merge(codecs, i * 4);
        }
        
        int[] temp = new int[16];
        for (int i = 0; i < 16; ++i) {
            int index = (arr[i / 4 + 4] >> (i % 4 * 2)) & 0x3;
            temp[i] = colors[index];
        }
        
        return temp;
    }
}
