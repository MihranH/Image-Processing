import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;
import ij.IJ;

 public class Normalized_hist implements PlugInFilter {
	ImagePlus im;

 public int setup(String arg, ImagePlus im) {
	 this.im = im;
	 return DOES_8G + NO_CHANGES;
 }

 public void run(ImageProcessor ip) {
	 int[] hist = ip.getHistogram();
	 int length = hist.length;
	 int height  = ip.getHeight();
	 int width = ip.getWidth();
	 //IJ.log("height " + height);
	 //IJ.log("width " +width);
	 for(int i=0; i<length; i++){
		 double result = hist[i]/(width*height);
		 IJ.log("The normalized histogram of index " + i + " is " + result); 		 
	 }	
 
  }
 }
