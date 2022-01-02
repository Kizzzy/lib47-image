package cn.kizzzy.image.selector;

import cn.kizzzy.image.PixelConverter;
import cn.kizzzy.image.PixelConverterSelector;

import java.util.Map;

public class DefaultPixelConverterSelector implements PixelConverterSelector {
    
    private final Map<Integer, PixelConverter> kvs;
    
    public DefaultPixelConverterSelector(Map<Integer, PixelConverter> kvs) {
        this.kvs = kvs;
    }
    
    @Override
    public PixelConverter select(int type) {
        return kvs.get(type);
    }
}
