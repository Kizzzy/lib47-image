package cn.kizzzy.image;

public interface ImageCreator<T> {
    
    interface Callback<T> {
        
        T invoke(int[] data, int width, int height) throws Exception;
    }
    
    <R> R Create(T item, Callback<R> callback) throws Exception;
}
