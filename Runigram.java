// This class uses the Color class, which is part of a package called awt,
// which is part of Java's standard class library.
import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("eyes.ppm");
		print(tinypic);

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] imageOut;

		// Tests the horizontal flipping of an image:
		/* 
		imageOut = flippedHorizontally(tinypic);
		System.out.println("Horizontally: ");
		print(imageOut);
		*/
		/* 
		imageOut = flippedVertically(tinypic);
		System.out.println("Vertically: ");
		print(imageOut);
		*/
		/* 
		imageOut = grayScaled(tinypic);
		System.out.println("GrayScale: ");
		print(imageOut);

		*/
		imageOut = scaled(tinypic,800,500);
		System.out.println("scaled 800 500: ");
		print(imageOut);
		
		//// Write here whatever code you need in order to test your work.
		//// You can reuse / overide the contents of the imageOut array.
	}

	/** Returns a 2D array of Color values, representing the image data
	 * stored in the given PPM file. */
	public static Color[][] read(String fileName) {
		In in = new In(fileName);
		// Reads the file header, ignoring the first and the third lines.
		in.readString();
		int numCols = in.readInt();
		int numRows = in.readInt();
		in.readInt();
		// Creates the image array
		Color[][] image = new Color[numRows][numCols];
		// Reads the RGB values from the file, into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		int red , green, blue;
		for(int i = 0; i < numRows ; i++)
		{
			for(int j = 0; j< numCols; j++)
			{
				red = in.readInt();
				green = in.readInt();
				blue = in.readInt();
				image[i][j] = new Color(red,green,blue);

			}
		}
		return image;
	}

    // Prints the RGB values of a given color.
	private static void print(Color c) {
	    System.out.print("(");
		System.out.printf("%3s,", c.getRed());   // Prints the red component
		System.out.printf("%3s,", c.getGreen()); // Prints the green component
        System.out.printf("%3s",  c.getBlue());  // Prints the blue component
        System.out.print(")  ");
	}

	// Prints the pixels of the given image.
	// Each pixel is printed as a triplet of (r,g,b) values.
	// This function is used for debugging purposes.
	// For example, to check that some image processing function works correctly,
	// we can apply the function and then use this function to print the resulting image.
	private static void print(Color[][] image) {
		//// Replace this comment with your code
		int length = image.length;
		int width = image[0].length;
		for(int i=0 ; i< length; i++)
		{
			for(int j=0 ; j< width ; j++)
			{
				print(image[i][j]); //print the color in that pixel
			}
			System.out.println();
		}
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		//// Replace the following statement with your code
		int rowsize = image.length;
		int colsize = image[1].length;
		Color[][] tempimg = new Color[rowsize][colsize];
		tempimg = image;
		Color[][] flippedimg = new Color[rowsize][colsize];
		for(int i=0; i< rowsize; i++)
		{
			for(int j=1; j <= colsize; j++)
			{
				flippedimg[i][j-1] = tempimg[i][colsize-j];
			}
		}
		return flippedimg;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		//// Replace the following statement with your code
		int rowsize = image.length;
		int colsize = image[1].length;
		Color[][] tempimg = new Color[rowsize][colsize];
		tempimg = image;
		Color[][] flippedimg = new Color[rowsize][colsize];
		for(int i=0; i< colsize; i++)
		{
			for(int j=1; j <= rowsize; j++)
			{
				flippedimg[j-1][i] = tempimg[rowsize-j][i];
			}
		}
		return flippedimg;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	public static Color luminance(Color pixel) {
		//// Replace the following statement with your code
		
		double red = pixel.getRed();
		double green = pixel.getGreen();
		double blue = pixel.getBlue();
		int grayresult = (int) (0.299 * red + 0.587 * green + 0.114 * blue);
		Color graypixel = new Color(grayresult,grayresult,grayresult);
		return graypixel;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		//// Replace the following statement with your code
		int rowsize = image.length;
		int colsize = image[1].length;
		//Color[][] tempimg = new Color[rowsize][colsize];
		//tempimg = image;
		Color[][] grayscaleimg = new Color[rowsize][colsize];
		Color temppixel = new Color(0,0,0);
		for(int i=0; i < rowsize; i++)
		{
			for(int j=0; j < colsize; j++)
			{
				temppixel = luminance(image[i][j]); //take the pixel and save the gray result in another parameter
				grayscaleimg[i][j] = temppixel;
			}
		}
		return grayscaleimg;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		//// Replace the following statement with your code
		int originalrowsize = image.length;
		int originalcolsize = image[1].length;
		Color[][] scaledimg = new Color[height][width];
		double newWidth = (double) (originalcolsize) / width;
    	double newHeight = (double) (originalrowsize) / height;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				scaledimg[i][j] = image[(int) (i * newHeight)][(int) (j * newWidth)];
			}
		}

		return scaledimg;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		//// Replace the following statement with your code
		int newRed = (int) (c1.getRed() * alpha  + c2.getRed() * (1-alpha)) ;
		int newGreen = (int) (c1.getGreen() * alpha  + c2.getGreen() * (1-alpha));
		int newBlue = (int) (c1.getBlue() * alpha + c2.getBlue() * (1-alpha));
		Color blendpixel = new Color (newRed,newGreen,newBlue);

		return blendpixel;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		//// Replace the following statement with your code
		int rowsize = image1.length;
		int colsize = image1[1].length;
		Color[][] tempimg = new Color[rowsize][colsize];
		for(int i=0; i < rowsize; i++)
		{
			for(int j=0; j < colsize; j++)
			{
				tempimg[i][j] = blend(image1[i][j], image2[i][j], alpha);
			}
		}
		

		return tempimg;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		//// Replace this comment with your code
		int sourcerowsize = source.length; //all of this elements not really neccesery but its make the code more readable
		int sourcecolsize = source[1].length;
		int targetrowsize = target.length;
		int targetcolsize = target[1].length;
		Color[][] result = new Color[sourcerowsize][sourcecolsize];
		Color[][] targetscaled = new Color[sourcerowsize][sourcecolsize];
		if((sourcerowsize != targetrowsize) || (sourcecolsize != targetcolsize)) //fix the scales if the images dont have the same dimensions 
		{
				targetscaled = scaled(target, sourcecolsize, sourcerowsize);
		}
		else{
			targetscaled = target;
		}
		for(int k = 0 ; k <= n ; k++)
		{
			double alpha = (double) ((double)(n-k)/ (double) n); //set the alpha for this part of blending
			result = blend(source, targetscaled, alpha);
			Runigram.setCanvas(result);
			Runigram.display(result);
			StdDraw.pause(500);

		}
	} 
	
	/** Creates a canvas for the given image. */
	public static void setCanvas(Color[][] image) {
		StdDraw.setTitle("Runigram 2023");
		int height = image.length;
		int width = image[0].length;
		StdDraw.setCanvasSize(height, width);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
        // Enables drawing graphics in memory and showing it on the screen only when
		// the StdDraw.show function is called.
		StdDraw.enableDoubleBuffering();
	}

	/** Displays the given image on the current canvas. */
	public static void display(Color[][] image) {
		int height = image.length;
		int width = image[0].length;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the pixel color
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}
