package cn.kizzzy.image;

public interface ImageCreator<T, R> {
    
    interface Callback<T> {
        
        T invoke(int[] data, int width, int height) throws Exception;
    }
    
    R Create(T item, Callback<R> callback);
}
