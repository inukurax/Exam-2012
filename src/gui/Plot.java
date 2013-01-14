package gui;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/** A class for drawing plots.
 */
public final class Plot {

  private final BufferedImage image;
  private final Graphics graphics;

  /** Constructs a new RGB image.
   */
  public Plot(final int width, final int height) {
    this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    this.graphics = this.image.getGraphics();
  }

  /** Attempts to save the image as a png file.
   *
   * @param path The desired filename, including extension.
   */
  public void save(final String path) throws IOException {
    ImageIO.write(this.image, "png", new File(path));
  }
  
  public Graphics getGraphics() {
    return this.graphics;
  }

}
