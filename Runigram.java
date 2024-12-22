import java.awt.Color;

/** A library of image processing functions. */
public class Runigram {

	public static void main(String[] args) {
	    
		//// Hide / change / add to the testing code below, as needed.
		
		// Tests the reading and printing of an image:	
		Color[][] tinypic = read("tinypic.ppm");
		print(tinypic);

		// Creates an image which will be the result of various 
		// image processing operations:
		Color[][] image;

		// Tests the horizontal flipping of an image:
		image = flippedHorizontally(tinypic);
		System.out.println();
		print(image);
		
		//// Write here whatever code you need in order to test your work.
		//// You can continue using the image array.
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
		// Reads the RGB values from the file into the image array. 
		// For each pixel (i,j), reads 3 values from the file,
		// creates from the 3 colors a new Color object, and 
		// makes pixel (i,j) refer to that object.
		//// Replace the following statement with your code.
		for (int i = 0; i < image.length; i ++) {
			for (int j = 0; j < image[i].length; j ++) {
				// recieving each color of the pixel
				int red = in.readInt();
				int green = in.readInt();
				int blue = in.readInt();
				
				// creating and inserting the new pixel
				Color pixel = new Color(red, green, blue);
				image[i][j] = pixel;
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
		//// Notice that all you have to so is print every element (i,j) of the array using the print(Color) function.
		for (int i = 0; i < image.length; i ++) {
			// create new array to load pixels values & get ready to print the row

			for (int j = 0; j < image[i].length; j ++) {
				// print every color cell
				print(image[i][j]);
			}

			// enter a new line
			System.out.println("");
		}
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		//// Replace the following statement with your code
		int hRows = image.length;
		int hCols = image[0].length;
		Color[][] horImage = new Color[hRows][hCols];

		for (int i = 0; i < hRows; i ++) {
			for (int j = 0; j < hCols; j ++) {
				horImage[i][j] = image[i][hRows - j];
			}
		}
		return horImage;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 */
	public static Color[][] flippedVertically(Color[][] image){
		//// Replace the following statement with your code
		int vRows = image.length;
		int vCols = image[0].length;
		Color[][] verImage = new Color[vRows][vCols];

		for (int j = 0; j < vCols; j ++){
			for (int i = 0; i < vRows; i ++) {
				verImage[i][j] = image[vCols - i][j];
			}
		}
		return verImage;
	}
	
	// Computes the luminance of the RGB values of the given pixel, using the formula 
	// lum = 0.299 * r + 0.587 * g + 0.114 * b, and returns a Color object consisting
	// the three values r = lum, g = lum, b = lum.
	private static Color luminance(Color pixel) {
		//// Replace the following statement with your code
		int lumiRed = (int) (pixel.getRed() * 0.299);
		int lumiGreen = (int) (pixel.getGreen() * 0.587);
		int lumiBlue = (int) (pixel.getBlue() * 0.114);
		Color lumiColors = new Color(lumiRed, lumiGreen, lumiBlue);
 		return lumiColors;
	}
	
	/**
	 * Returns an image which is the grayscaled version of the given image.
	 */
	public static Color[][] grayScaled(Color[][] image) {
		//// Replace the following statement with your code
		int lumRows = image.length;
		int lumCols = image[0].length;
		Color[][] lumImage = new Color[lumRows][lumCols];
		
		for (int i = 0; i < image.length; i ++) {
			for (int j = 0; j < image[i].length; j ++) {
				// luminates every color cell
				lumImage[i][j] = luminance(image[i][j]);
			}
		}

		return lumImage;
	}	
	
	/**
	 * Returns an image which is the scaled version of the given image. 
	 * The image is scaled (resized) to have the given width and height.
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		//// Replace the following statement with your code
		int orgRows = image.length;
		int orgCols = image[0].length;
		Color[][] scaledImage = new Color[width][height];
		
		for (int i = 0; i < scaledImage.length; i ++) {
			for (int j = 0; j < scaledImage[i].length; j ++) {
				// scaling every color cell
				scaledImage[i][j] = image[i*(orgRows/width)][j*(orgCols/height)];
			}
		}
		return scaledImage;
	}
	
	/**
	 * Computes and returns a blended color which is a linear combination of the two given
	 * colors. Each r, g, b, value v in the returned color is calculated using the formula 
	 * v = alpha * v1 + (1 - alpha) * v2, where v1 and v2 are the corresponding r, g, b
	 * values in the two input color.
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		//// Replace the following statement with your code
		int blendRed = (int) (alpha*c1.getRed() + (1 - alpha)*c2.getRed());
		int blendGreen = (int) (alpha*c1.getGreen() + (1 - alpha)*c2.getGreen());
		int blendBlue = (int) (alpha*c1.getBlue() + (1 - alpha)*c2.getBlue());

		// creates new pixel with the calculated values
		Color c3 = new Color(blendRed, blendGreen, blendBlue);
		return c3;
	}
	
	/**
	 * Cosntructs and returns an image which is the blending of the two given images.
	 * The blended image is the linear combination of (alpha) part of the first image
	 * and (1 - alpha) part the second image.
	 * The two images must have the same dimensions.
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		//// Replace the following statement with your code
		Color[][] firstImage = image1;
		Color[][] secondImage = image2;
		Color[][] blendedImage = new Color[image1.length][image1[0].length];
		
		for (int i = 0; i < blendedImage.length; i ++) {
			for (int j = 0; j < blendedImage[i].length; j ++) {
				// blends every color cell
				blendedImage[i][j] = blend(firstImage[i][j], secondImage[i][j], alpha);
			}
		}

		return blendedImage;
	}

	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * Before starting the process, scales the target image to the dimensions
	 * of the source image.
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		//// setting permanent vals
		Color[][] morphedImage = new Color[source.length][source[0].length];
		
		// adjusting target size to source
		if (source.length != target.length || source[0].length != target[0].length) {
			target = scaled(target, source.length, source[0].length);
		}

		// iterates steps
		for (int k = 0; k <= n; k ++);
			int step = 0;
			// starting the morphing sequence
			for (int i = 0; i < morphedImage.length; i ++) {
				for (int j = 0; j < morphedImage[i].length; j ++) {
					// blends every color cell
					morphedImage[i][j] = blend(source[i][j], target[i][j], (n - step));
					step ++;

			}

			// presnting total step
			Runigram.setCanvas(morphedImage); // TBD - needed? 
			Runigram.display(morphedImage);
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

