package cn.kizzzy.image.creator;

import cn.kizzzy.image.TgaFile;
import cn.kizzzy.image.converter.ARGB8888PixelConverter;
import cn.kizzzy.io.IFullyReader;

public class TgaImageCreator extends ImageCreatorAdapter<TgaFile> {
    
    public TgaImageCreator() {
        super(null);
    }
    
    @Override
    protected <R> R CreateImpl(TgaFile item, Callback<R> callback) throws Exception {
        if (item.iocmd != null && item.iocmd.imageData != null) {
            int width = item.header.imageSpecification.width;
            int height = item.header.imageSpecification.height;
            try (IFullyReader reader = item.iocmd.imageData.getInput()) {
                int[] pixels = readPixel(reader, new ARGB8888PixelConverter(), width, height);
                return callback.invoke(pixels, width, height);
            }
        }
        throw new IllegalArgumentException("Unknown Type: " + item.iocmd);
    }
}
