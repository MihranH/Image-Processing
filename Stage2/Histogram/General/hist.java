import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;
import ij.IJ;

 public class hist implements PlugInFilter {
	ImagePlus im;

 public int setup(String arg, ImagePlus im) {
	 this.im = im;
	 return DOES_RGB;
 }

 public void run(ImageProcessor ip) {
	 // obtain the histogram of ip:
	 int[] hist = ip.getHistogram();
	 int K = hist.length;
	 for(int i=0; i<K; i++){
		 IJ.log("The histogram of index " + i + " is " + hist[i]); 		 
	 }	
 
  }
 }
