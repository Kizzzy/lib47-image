package cn.kizzzy.image.creator;

import cn.kizzzy.helper.LogHelper;
import cn.kizzzy.image.ImageCreator;
import cn.kizzzy.image.PixelConverter;
import cn.kizzzy.image.PixelConverterSelector;
import cn.kizzzy.io.DataInputStreamEx;

public abstract class ImageCreatorAdapter<T, R> implements ImageCreator<T, R> {
    
    protected PixelConverterSelector selector;
    
    public ImageCreatorAdapter(PixelConverterSelector selector) {
        this.selector = selector;
    }
    
    @Override
    public R Create(T item, Callback<R> callback) {
        try {
            return CreateImpl(item, callback);
        } catch (Exception e) {
            LogHelper.error("Convert Image Data Error: ", e);
            return null;
        }
    }
    
    protected abstract R CreateImpl(T item, Callback<R> callback) throws Exception;
    
    public static int[] readPixel(DataInputStreamEx reader, PixelConverter converter, int width, int height) throws Exception {
        int row = converter.getRow();
        int col = converter.getCol();
        
        int[] images = new int[width * height];
        
        for (int i = 0; i < height; i += row) {
            for (int j = 0; j < width; j += col) {
                int[] temp = converter.read(reader);
                for (int m = 0; m < row; ++m) {
                    for (int n = 0; n < col; ++n) {
                        if (i + m < height && j + n < width) {
                            int ii = (i + m) * width + (j + n);
                            int it = m * col + n;
                            
                            images[ii] = temp[it];
                        }
                    }
                }
            }
        }
        return images;
    }
}
