package cn.kizzzy.image.converter;

import cn.kizzzy.image.PixelConverter;
import cn.kizzzy.io.DataInputStreamEx;

import java.io.IOException;

public abstract class AbstractPixelConverter implements PixelConverter {
    private final int count;
    private final int row;
    private final int col;
    
    public AbstractPixelConverter(int count, int row, int col) {
        this.count = count;
        this.row = row;
        this.col = col;
    }
    
    public int[] read(DataInputStreamEx reader) throws IOException {
        int[] temp = new int[count];
        for (int k = 0; k < count; ++k) {
            temp[k] = reader.read();
        }
        return readImpl(temp);
    }
    
    protected abstract int[] readImpl(int[] temp);
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public static int merge(int... bytes) {
        int temp = 0;
        for (int i = 0; i < bytes.length; ++i) {
            temp = temp | (bytes[i] << (i * 8));
        }
        return temp;
    }
    
    protected int merge(int[] bytes, int offset) {
        return bytes[offset + 0] | (bytes[offset + 1] << 8) | (bytes[offset + 2] << 16) | (bytes[offset + 3] << 24);
    }
    
    /*
    public static int merge(int low, int high) {
        return low | (high << 8);
    }
    
    public static int merge(int a, int r, int g, int b) {
        return a << 24 | r << 16 | g << 8 | b;
    }
    */
    public static int calc(int value, int start, int n) {
        return calc(value, start, n, true);
    }
    
    public static int calc(int value, int start, int n, boolean blend) {
        value = value >> start & (1 << n) - 1;
        if (blend) {
            value = (value << (8 - n) | value >> (2 * n - 8));
        }
        return value;
    }
    
    protected void unpacked(int value, int[] parts, int offset) {
        parts[offset + 0] = calc(value, 0, 5);
        parts[offset + 1] = calc(value, 5, 6);
        parts[offset + 2] = calc(value, 11, 5);
        parts[offset + 3] = 255;
    }
    
    protected void unpacked(int[] codes, boolean dxt1Flag) {
        for (int i = 0; i < 3; ++i) {
            int c = codes[i];
            int d = codes[4 + i];
            if (dxt1Flag) {
                codes[8 + i] = ((c + d) / 2);
                codes[12 + i] = 0;
            } else {
                codes[8 + i] = ((2 * c + d) / 3);
                codes[12 + i] = ((c + 2 * d) / 3);
            }
        }
        codes[8 + 3] = 255;
        codes[12 + 3] = ((dxt1Flag) ? 0 : 255);
    }
}
