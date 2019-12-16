import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;
import ij.process.ColorProcessor;
import ij.IJ;
import java.awt.Color;

 public class Norm_Cumulative_Hist_HSV implements PlugInFilter {
	ImagePlus im;

 public int setup(String arg, ImagePlus im) {
	 this.im = im;
	 return DOES_RGB;
 }

 public void run(ImageProcessor ip) {
	 int r=0;
	 int g=0;
	 int b=0;
	 int height  = ip.getHeight();
	 int width = ip.getWidth();
	 int[] pixels = (int[]) ip.getPixels();
	for(int i=0; i<pixels.length; i++){
		 int color = pixels[i];
		 int red = (color & 0xff0000) >> 16;
		 r = r + red;
		 int green = (color & 0x00ff00) >> 8;
		 g = g + green;
		 int blue = (color & 0x0000ff);
		 b = b + blue;		 
	    }
	     float[] hsv = new float[3];
		 hsv = Color.RGBtoHSB (r, g, b, hsv);
		 float h = hsv[0];
		 float s = hsv[1];
		 float v = hsv[2];
	     double hResult = h/(width*height);
		 double sResult = s/(width*height);
		 double vResult = v/(width*height);
		 IJ.log("The normalized cumulative histogram for h is " + hResult);
		 IJ.log("The normalized cumulative histogram for s is " + sResult);
		 IJ.log("The normlalized cumulative histogram for v is " + vResult); 
   }
 }
	
