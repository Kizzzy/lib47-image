package cn.kizzzy.image;

public enum PngFilterMethod {
    AdaptiveFiltering(0),
    ;
    
    private final int type;
    
    PngFilterMethod(int type) {
        this.type = type;
    }
    
    public int getType() {
        return type;
    }
    
    public static PngFilterMethod valueOf(int type) {
        for (PngFilterMethod filterMethod : values()) {
            if (type == filterMethod.type) {
                return filterMethod;
            }
        }
        throw new IllegalArgumentException("Unknown Type: " + type);
    }
}
