import ij.ImagePlus;
import ij.process.ImageProcessor;
import ij.plugin.filter.PlugInFilter;
import java.awt.Color;

public class Lips_Detection implements PlugInFilter {
	
	public int setup(String args, ImagePlus im) {
		return DOES_RGB;
	}
	public void run(ImageProcessor ip) {
		int width = ip.getWidth();
		int height = ip.getHeight();
        int	pixel, r, g, b;
		double rb;
		Color color;
		for (int row = 0; row < height; row++){
			for (int col = 0; col < width; col++) {
				color = new Color(ip.getPixel(col, row));
				r = color.getRed();
				g = color.getGreen();
				b = color.getBlue();
				int[] value = new int[3];
				if(r>g*2.3 && r>b*2.3 && col<(width/2)+40 && col>(width/2)-40 && row>(height/2)-100 &&  row<(height/2)+80){
					value[0]=r;
					value[1]=g;
					value[2]=b;
				}
				else {
					value[0]=1000;
					value[1]=1000;
					value[2]=1000;
				}
				
				ip.putPixel(col, row, value); 
			
			}
		}
	}
}
