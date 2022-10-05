import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;


import javax.imageio.ImageIO;

/**
 * Opens up a new page with the memes.
 */
public class MemeGUI extends JPanel
{
  

  private static BufferedImage dogImage;
  private static URL current;
  private static String[] dogUrls = DogGUI.getDogUrls();

  /**
   * Creates a custom meme using selected
   * dog image.
   * @param index the selected dog breed
   */
  public static void openMeme(int index)
  {
    JFrame frame = new JFrame("Da Memes");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    try 
    {
      current = new URL(dogUrls[index]);
      dogImage = ImageIO.read(current);
      if (dogImage != null) 
      {
        String[] text = askUserForText();
        //passes the text to the image editor if 
        //ok button is pushed
        if(text != null)
        {
          MemeMaker memer = new MemeMaker(text);
          //makes sure meme window can not be resized
          frame.setResizable(false);
          frame.add(memer);
          frame.pack();
          frame.setVisible(true);
          
          JFileChooser fileChooser = new JFileChooser();
          fileChooser.setDialogTitle("Specify a file to save");   
           
          int userSelection = fileChooser.showSaveDialog(frame);
          String pathToSave = "";
          if (userSelection == JFileChooser.APPROVE_OPTION) {
              File fileToSave = fileChooser.getSelectedFile();
              pathToSave = fileToSave.getCanonicalPath();
              System.out.println("Save as file: " + fileToSave.getAbsolutePath());
          }
          
          makePanelImage(memer, pathToSave);

        }
      }
    } catch (IOException e) 
    {
      e.printStackTrace();
    }
    

  }
  
  private static void makePanelImage(Component panel, String path)
  {
      BufferedImage image = new BufferedImage(
                  500, 500, BufferedImage.TYPE_INT_RGB);
      Graphics2D g2 = image.createGraphics();
      panel.paint(g2);
      try
      {
    	  //use JFileChooser to find where user wants to save image
          ImageIO.write(image, "png", new File(path+".png"));
          System.out.println("Panel saved as Image.");
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
  }

  /**
   * Prompts user for top text and bottom text.
   * 
   * @return an array of both answers
   */
  private static String[] askUserForText() 
  {
    String[] text = new String[2];

    //creates answering fields
    JTextField topField = new JTextField(5);
    JTextField bottomField = new JTextField(5);

    JPanel myPanel = new JPanel();
    myPanel.add(new JLabel("top text:"));
    myPanel.add(topField);
    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
    myPanel.add(new JLabel("bottome text:"));
    myPanel.add(bottomField);

    //stores answer selected after typing text(ok or cancel button)
    int result = JOptionPane.showConfirmDialog(null, myPanel,
        "Please Enter top text and bottom text", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION)
    {
      return null;
    }    
    text[0] = topField.getText(); //stores top text field
    text[1] = bottomField.getText(); // stores bottom text field
    

    return text;
  }
    
}
