import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;
import ij.process.ColorProcessor;
import ij.IJ;
import java.awt.Color;

 public class Matching_H implements PlugInFilter {	
	ImageProcessor imageStandard;
	 double[] redObj = new double[256];
	 double[] blueObj= new double[256];
	 double[] greenObj= new double[256];
	 double[] redObjSt= new double[256];
	 double[] blueObjSt= new double[256];
	 double[] greenObjSt= new double[256];
	 int r=0;
	 int g=0;
	 int b=0;
	 int[] pixels;
	 int[] pixelsStandard;
	 int count = 0;
	 int redChange[] = new int[256];
     int greenChange[] = new int[256];
     int blueChange[] = new int[256];
	 
 public int setup(String arg, ImagePlus im) {
	 return DOES_RGB;
 }

 public void run(ImageProcessor ip) {
	 
	 imageStandard = IJ.openImage().getProcessor(); 
	 pixels = (int[]) ip.getPixels();
	 pixelsStandard = (int[]) imageStandard.getPixels();
	 calculateHistogram(pixels, false);
	 calculateHistogram(pixelsStandard, true);
	
	 match(redObj,redObjSt);
 	 match(greenObj,greenObjSt);
	 match(blueObj,blueObjSt);
	 putChanged(ip);
		
   }
   
   public void match(double[] array1, double[] array2){	   
	   
	    for(int i=0; i<array1.length; i++){
			   for(int j=0; j<array2.length; j++){				  
				   if(array1[i] > array2[j]){
						if(count==0){
							redChange[i] = j;
						}
						else if(count==1){
							greenChange[i] = j;
						}
						else{
							blueChange[i] = j;
						}
			    }
			  }
		  }
		  count++;	   
	   
   }
   
   public void putChanged(ImageProcessor ip){
	   for(int i=0; i<ip.getWidth(); i++){
		   for(int j=0; j<ip.getHeight(); j++){			   
				     Color color = new Color(ip.getPixel(i, j));
					 int red = color.getRed();
				     int green = color.getGreen();
					 int blue = color.getBlue();
					 int[] tobeCalibrated = new int[3];
					 tobeCalibrated[0] = redChange[red];
					 tobeCalibrated[1] = greenChange[green];
					 tobeCalibrated[2] = blueChange[blue];
					 ip.putPixel(i, j, tobeCalibrated);
			   
		   }
	   }
   }
   
   public void calculateHistogram(int[] pixels, boolean standard){
	   for(int j=0; j<256; j++){
		 int color = pixels[j];
		 
		 int red = (color & 0xff0000) >> 16;
		 r = r + red;
		 if(!standard){
			 redObj[j] = r;
		 }
		 else {
			  redObjSt[j] = r;	
		 }
		 		 
		 int green = (color & 0x00ff00) >> 8;
		 g = g + green;
		 if(!standard){
			 greenObj[j] = g;
		 }			 
		 else {
			  greenObjSt[j] =g;	
		 }
		
		 int blue = (color & 0x0000ff);
		 b = b + blue;
		 if(!standard){
			  blueObj[j] = b;			  
		 }		
		 else {
			  blueObjSt[j] = b;	
		 }		 
		 
	}
	r=0;
	g=0;
	b=0;
   }
   
 }
	
