import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;
import ij.process.ColorProcessor;
import ij.IJ;

 public class Norm_Cumulative_Hist_RGB implements PlugInFilter {
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
	     double redResult = r/(width*height);
		 double greenResult = g/(width*height);
		 double blueResult = b/(width*height);
		 IJ.log("The normalized cumulative histogram for red is " + redResult);
		 IJ.log("The normalized cumulative histogram for green is " + greenResult);
		 IJ.log("The normlalized cumulative histogram for blue is " + blueResult); 
   }
 }
	
