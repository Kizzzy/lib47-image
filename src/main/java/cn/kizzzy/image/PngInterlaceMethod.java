package cn.kizzzy.image;

public enum PngInterlaceMethod {
    NoInterlace(0),
    Adam7Interlace(1),
    ;
    
    private final int type;
    
    PngInterlaceMethod(int type) {
        this.type = type;
    }
    
    public int getType() {
        return type;
    }
    
    public static PngInterlaceMethod valueOf(int type) {
        for (PngInterlaceMethod interlaceMethod : values()) {
            if (type == interlaceMethod.type) {
                return interlaceMethod;
            }
        }
        throw new IllegalArgumentException("Unknown Type: " + type);
    }
}
