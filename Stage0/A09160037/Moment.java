import ij.IJ;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import ij.plugin.filter.PlugInFilter;
import java.awt.Color;

public abstract class Moment implements PlugInFilter {
	 double moment(ImageProcessor image, int p, int q) {
		 double Mpq = 0.0;
		 for (int v = 0; v < image.getHeight(); v++) {
		 for (int u = 0; u < image.getWidth(); u++) {
			 if (image.getPixel(u, v) > 0) {
				Mpq+= Math.pow(u, p) * Math.pow(v, q);
			 }
			}
		}
		return Mpq;
	}
	public void run(ImageProcessor I) {
		double moment = moment(I, 1, 1);
		IJ.log("The moment is " + this.moment);
	}
	
}
 
