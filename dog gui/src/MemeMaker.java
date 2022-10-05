import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;

public class MemeMaker extends JPanel 
{
  private static Image im;
  private static String[] dogUrls = DogGUI.getDogUrls();
  private static URL current;
  private BufferedImage dogImage;
  private String[] promptAnswers;

    /**
     * Stores the data from prompt for top
     * and bottom text.
     * @param text the array from prompting method
     */
  public MemeMaker(String[] text) 
  {
      //storing the text[] from MemeGui
    promptAnswers = new String[2];
    promptAnswers[0] = text[0];
    promptAnswers[1] = text[1]; 
  }

  /**
   * Updates the image with the text given by user.
   */
  @Override
  public  void paintComponent(Graphics g) 
  {
    super.paintComponent(g);
    try 
    {
      current = new URL(dogUrls[DogGUI.getSelectedIndex()]);
      try 
      {
        dogImage = ImageIO.read(current);
      } catch (IOException e) 
      {
        e.printStackTrace();
      }
    } catch (MalformedURLException e) 
    {
      e.printStackTrace();
    }
        
    im = dogImage.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
    Font font = new Font("Verdana", Font.BOLD, 40);
    //sets color of text
    g.setColor(Color.WHITE); 
    g.drawImage(im, 0, 0, null);

    //draws both texts in the center of the given areas of the image
    drawCenteredString(g, promptAnswers[0], new Rectangle(500,80), font);
    drawCenteredString(g, promptAnswers[1], new Rectangle(0,420,500,80), font);
  }

    /**
    * Draw a String centered in the middle of a Rectangle.
    *
    * @param g The Graphics instance.
    * @param text The String to draw.
    * @param rect The Rectangle to center the text in.
    * @param font is the font present for the meme text.
    */
  public void drawCenteredString(Graphics g, String text, 
            Rectangle rect, Font font) 
  {
        // Get the FontMetrics
    FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
    g.setFont(font);
        // Draw the String
    g.drawString(text, x, y);
  }

  @Override
  public Dimension getPreferredSize() 
  {   
    return new Dimension(500, 500);
  }
    
}
