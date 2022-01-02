package cn.kizzzy.image.converter;

public class DXT5PixelConverter extends AbstractPixelConverter {
    
    public DXT5PixelConverter() {
        super(16, 4, 4);
    }
    
    @Override
    protected int[] readImpl(int[] temp) {
        return dxt5(temp);
    }
    
    private int[] dxt5(int[] arr) {
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
        
        int alpha_0 = arr[0];
        int alpha_1 = arr[1];
        int alpha_2 = alpha_0 > alpha_1 ? ((6 * alpha_0 + 1 * alpha_1 + 3) / 7) : ((4 * alpha_0 + 1 * alpha_1 + 2) / 5);
        int alpha_3 = alpha_0 > alpha_1 ? ((5 * alpha_0 + 2 * alpha_1 + 3) / 7) : ((3 * alpha_0 + 2 * alpha_1 + 2) / 5);
        int alpha_4 = alpha_0 > alpha_1 ? ((4 * alpha_0 + 3 * alpha_1 + 3) / 7) : ((2 * alpha_0 + 3 * alpha_1 + 2) / 5);
        int alpha_5 = alpha_0 > alpha_1 ? ((3 * alpha_0 + 4 * alpha_1 + 3) / 7) : ((1 * alpha_0 + 4 * alpha_1 + 2) / 5);
        int alpha_6 = alpha_0 > alpha_1 ? ((2 * alpha_0 + 5 * alpha_1 + 3) / 7) : 0;
        int alpha_7 = alpha_0 > alpha_1 ? ((1 * alpha_0 + 6 * alpha_1 + 3) / 7) : 255;
        
        int[] alphas = new int[]{
            alpha_0,
            alpha_1,
            alpha_2,
            alpha_3,
            alpha_4,
            alpha_5,
            alpha_6,
            alpha_7,
        };
        
        int[] temp = new int[16];
        int x = 2, k = 0;
        for (int i = 0; i < 16; ++i) {
            int index = (arr[i / 4 + 12] >> (i % 4 * 2)) & 0x3;
            int alpha = ((arr[x] >> (k))) & 0x7;
            k += 3;
            if (k >= 8) {
                x++;
                k = k - 8;
                alpha = alpha | (arr[x] & ((1 << (k)) - 1));
            }
            temp[i] = colors[index] & ((alphas[alpha] << 24) | 0xffffff);
        }
        return temp;
    }
}
