package cn.kizzzy.image.helepr;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageHelper {
    
    public static BufferedImage removeBackgroundColorWithAlpha(BufferedImage image, Color bgColor) {
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int a = (rgb >> 24) & 0xFF;
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = (rgb >> 0) & 0xFF;
                
                if (a == 0) {
                    return image;
                }
                
                if (bgColor == null) {
                    bgColor = new Color(r, g, b, a);
                }
                
                int br = bgColor.getRed();
                int bg = bgColor.getGreen();
                int bb = bgColor.getBlue();
                
                // 估算 Alpha
                float alphaR = (r - br) / 255f;
                float alphaG = (g - bg) / 255f;
                float alphaB = (b - bb) / 255f;
                
                float alpha = Math.max(0, Math.min(1, Math.max(Math.max(alphaR, alphaG), alphaB)));
                
                if (alpha < 0.01f) {
                    result.setRGB(x, y, 0x00000000);
                    continue;
                }
                
                // 还原原始颜色
                int or = (int) ((r - (1 - alpha) * br) / alpha);
                int og = (int) ((g - (1 - alpha) * bg) / alpha);
                int ob = (int) ((b - (1 - alpha) * bb) / alpha);
                
                // 限制在合法范围
                or = Math.min(255, Math.max(0, or));
                og = Math.min(255, Math.max(0, og));
                ob = Math.min(255, Math.max(0, ob));
                int oa = (int) (alpha * 255);
                
                int rgba = (oa << 24) | (or << 16) | (og << 8) | ob;
                result.setRGB(x, y, rgba);
            }
        }
        return result;
    }
}
