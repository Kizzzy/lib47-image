package cn.kizzzy.image.creator;

import cn.kizzzy.image.ImageCreator;

public class ARGBRawDataCallback implements ImageCreator.Callback<int[]> {
    
    @Override
    public int[] invoke(int[] data, int width, int height) throws Exception {
        return data;
    }
}
