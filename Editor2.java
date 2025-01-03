import java.awt.Color;

/**
 * Demonstrates the scaling (resizing) operation featured by Runigram.java. 
 * The program recieves three command-line arguments: a string representing the name
 * of the PPM file of a source image, and two integers that specify the width and the
 * height of the scaled, output image. For example, to scale/resize ironman.ppm to a width
 * of 100 pixels and a height of 900 pixels, use: java Editor2 ironman.ppm 100 900
 */
public class Editor2 {

	public static void main (String[] args) {
		//// Replace this comment with your code.
		String fileName = args[0];
		int imageWid = Integer.parseInt(args[1]);
		int imageLen = Integer.parseInt(args[2]);
		// Reads the input image and creates an empty output image
		Color[][] imageIn = Runigram.read(fileName);	

		// Applies the specified image processing function
		Color[][] imageOut = Runigram.scaled(imageIn, imageWid, imageLen);

		// Creates a canvas in which both images will be displayed, one after the other.
		// Next, displays the input image, and pauses for a few seconds. 
		// Finally, displays the output image.
		// (Notice that the images have different dimensions).
		Runigram.setCanvas(imageIn);
		Runigram.display(imageIn);
		StdDraw.pause(3000); 
		Runigram.setCanvas(imageOut);
		Runigram.display(imageOut);			
	}
}
