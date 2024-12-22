import java.awt.Color;

// Morphs an image into its grescaled version.
public class Editor4 {
	public static void main (String[] args) {
		String source = args[0];
		Color[][] sourceImage = Runigram.read(source);
		Color[][] target = Runigram.grayScaled(sourceImage);
		int n = Integer.parseInt(args[1]);
		Runigram.setCanvas(sourceImage);
		Runigram.morph(sourceImage, target, n);
	}
}
