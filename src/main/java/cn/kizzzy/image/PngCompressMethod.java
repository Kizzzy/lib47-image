package cn.kizzzy.image;

public enum PngCompressMethod {
    Deflate(0),
    ;
    
    private final int type;
    
    PngCompressMethod(int type) {
        this.type = type;
    }
    
    public int getType() {
        return type;
    }
    
    public static PngCompressMethod valueOf(int type) {
        for (PngCompressMethod compressMethod : values()) {
            if (type == compressMethod.type) {
                return compressMethod;
            }
        }
        throw new IllegalArgumentException("Unknown Type: " + type);
    }
}
