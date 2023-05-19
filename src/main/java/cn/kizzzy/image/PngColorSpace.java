package cn.kizzzy.image;

public enum PngColorSpace {
    GrayScale(0),
    TrueColor(2),
    Indexed(3),
    AlphaGrayScale(4),
    AlphaTrueColor(6),
    ;
    
    private final int type;
    
    PngColorSpace(int type) {
        this.type = type;
    }
    
    public int getType() {
        return type;
    }
    
    public static PngColorSpace valueOf(int type) {
        for (PngColorSpace colorSpace : values()) {
            if (type == colorSpace.type) {
                return colorSpace;
            }
        }
        throw new IllegalArgumentException("Unknown Type: " + type);
    }
}
