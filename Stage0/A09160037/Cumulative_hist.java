import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;
import ij.IJ;

 public class Cumulative_hist implements PlugInFilter {
	ImagePlus im;

 public int setup(String arg, ImagePlus im) {
	 this.im = im;
	 return DOES_RGB;
 }

 public void run(ImageProcessor ip) {
	 int cumulative = 0;
	 int[] hist = ip.getHistogram();
	 int length = hist.length;
	 int height  = ip.getHeight();
	 int width = ip.getWidth();
	 //IJ.log("height " + height);
	 //IJ.log("width " +width);
	 for(int i=0; i<length; i++){
		 cumulative = hist[i] + cumulative;
		 IJ.log("The cumulative histogram till index " + i + " is " + cumulative); 		 
	 }	
 
  }
 }
