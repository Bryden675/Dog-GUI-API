import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//import org.w3c.dom.stylesheets.StyleSheet;


/**
 * DogGUI class displays GUI for program.
 * 
 */
@SuppressWarnings("serial")
public class DogGUI extends JPanel 
{
  private static JLabel picture;
  private static boolean DogPicShown;
  private static BufferedImage dogImage;
  private static JList<String> list;
  private static Image im;
  private static String[] dogUrls;
  private static URL current;
  private static List<dog> dogList;
  private static JLabel label1;
  private static JLabel label2;
  private static JLabel label3;
  private static JLabel hyperlink;
  private static JButton button;
  private static JPanel picturePanel;
  private static JFrame frame;
  private static int randomURLIndex;
  private JSplitPane splitPane;
  private static JSplitPane pane1;
  private static JSplitPane pane2;
  private static JSplitPane pane3;
  private static JSplitPane pane4;
  private static JSplitPane pane5;
  private static JPanel linkLabel;
  private static JPanel memeLabel;
  private String[] breeds;

  

  /**
   * Dog GUI object that creates a new GUI.
   * 
   * @throws IOException
   */
  public DogGUI() throws IOException 
  {
    URL url = new URL("https://api.thedogapi.com/v1/breeds");
    InputStream inputStream = url.openStream();
    dogList = DogListReader.readDogList(inputStream);

    // Initializing 'breeds' array with names of dog breeds.
    breeds = new String[dogList.size()];
    for (int i = 0; i < dogList.size(); i++) 
    {
      breeds[i] = dogList.get(i).getName();
    }
    dogUrls = new String[dogList.size()];
    for (int i = 0; i < dogList.size(); i++) 
    {
      dogUrls[i] = dogList.get(i).getUrlString();
    }

    list = new JList<String>(breeds);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.setSelectedIndex(0);
    // list.addListSelectionListener(this);

    JScrollPane listScrollPane = new JScrollPane(list);
    
    picturePanel = new JPanel();

    picture = new JLabel();
    picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
    picture.setVerticalTextPosition(SwingConstants.BOTTOM);
    picture.setHorizontalTextPosition(SwingConstants.CENTER);
    
    label1 = new JLabel("");
    label1.setFont(new Font(label1.getFont().getFontName(), Font.BOLD, 24));
    
    label2 = new JLabel("");
    label2.setFont(label2.getFont().deriveFont(Font.ITALIC));
    label2.setHorizontalTextPosition(SwingConstants.RIGHT);
    
    label3 = new JLabel("");
    label3.setFont(label3.getFont().deriveFont(Font.ITALIC));
    label3.setHorizontalTextPosition(SwingConstants.RIGHT);

    picturePanel.add(label2);
    picturePanel.add(label3);
    
    memeLabel = new JPanel();
    linkLabel = new JPanel();
    
    pane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, linkLabel, memeLabel);
    pane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, label3, pane1);
    pane3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, label2, pane2); 
    pane4 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, picture, pane3);
    pane5 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, label1, pane4);
    
    pane1.setDividerSize(0);
    pane2.setDividerSize(0);
    pane3.setDividerSize(0);
    pane4.setDividerSize(0);
    pane5.setDividerSize(0);
    
    
    picturePanel.add(pane5);
    
    picturePanel.setAlignmentX(SwingConstants.LEFT);
    
    // Create a split pane with the two scroll panes in it.
    splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, picturePanel);
    splitPane.setOneTouchExpandable(true);
    //splitPane.setDividerLocation(225);


    // Provide minimum sizes for the two components in the split pane.
    Dimension minimumSize = new Dimension(100, 50);
    listScrollPane.setMinimumSize(minimumSize);
    picturePanel.setMinimumSize(minimumSize);

    // Provide a preferred size for the split pane.
    splitPane.setPreferredSize(new Dimension(1150, 600));
  }

  /**
   * Helper method for getting splitPane.
   * 
   * @return splitPane
   */
  public JSplitPane getSplitPane() 
  {
    return splitPane;
  }

  /**
   * Helper method to display pictures.
   */
  public static void pictureMethod() 
  {
    //picture.setIcon(new ImageIcon("DogGuiPic.png"));
      
    ListSelectionListener listSelectionListener = new ListSelectionListener() 
    {
      public void valueChanged(ListSelectionEvent listSelectionEvent) 
      {
    	  
        boolean adjust = listSelectionEvent.getValueIsAdjusting();
        
        if (!adjust) 
        {
          if (!DogPicShown)
			try {
				memeButton();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
          DogPicShown = true;
          //playSound("Bark2.wav");
          int index = list.getSelectedIndex();
          if(hyperlink!=null) {
            hyperlink.setVisible(false);
            picturePanel.remove(linkLabel);
          } 
          try 
          {
            current = new URL(dogUrls[index]);

            dogImage = ImageIO.read(current);
            dog selectedDog = dogList.get(index);

            if (dogImage != null) 
            {
              im = dogImage.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
              picture.setIcon(new ImageIcon(im));
              picture.setText(current.toString());
              hyperlink = createHyperlink(current.toString());
              linkLabel.add(hyperlink);


              label1.setText("Learn more about the " + selectedDog.getName() + " - ");
              label1.setSize(picturePanel.getWidth(), label1.getHeight());
              label2.setText("Temperament: " + selectedDog.getTemperament());
              label3.setText("Lifespan: " + selectedDog.getLifeSpan());
            }
          } catch (IOException e) 
          {
            picture.setText("Karen we lost the dog");
            e.printStackTrace();
          }

        }
      }
    };
    list.addListSelectionListener(listSelectionListener);
  }

  /**
   * Helper method for getting current selected
   * index.
   * 
   * @return current selected index.
   */
  public static int getSelectedIndex() 
  {
    return list.getSelectedIndex();
  }

  /**
   * Getter for list of dog urls.
   * 
   * @return an array of dog urls.
   */
  public static String[] getDogUrls() 
  {
    return dogUrls;
  }

  private static void memeButton() throws MalformedURLException 
  { 
	URL url = new URL("https://is2-ssl.mzstatic.com/image/thumb/Purple111/v4/bb/b2/67/bbb2670e-86d5-a5a3-52d2-26069841f728/source/256x256bb.jpg");
	ImageIcon icon = new ImageIcon(url);
	Image image = icon.getImage();
	Image previewImage = image.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
	ImageIcon finalImage = new ImageIcon(previewImage);
	button = new JButton("MemeMaker", finalImage);
	button.setPreferredSize( new Dimension(150, 150));
    
    memeLabel.add(button);
    button.addActionListener(new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
          MemeGUI.openMeme(getSelectedIndex());     
        }   
      });        
  }

  private static JLabel createHyperlink(String url) 
  {
    
    hyperlink = new JLabel("Source of Image");
    hyperlink.setForeground(Color.BLUE.darker());
    hyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    hyperlink.addMouseListener(new MouseAdapter() 
    {
 
      @Override
      public void mouseClicked(MouseEvent e) 
      {
          // the user clicks on the label
        try 
        {

          Desktop.getDesktop().browse(new URI(url));
            
        } catch (IOException | URISyntaxException e1) 
        {
          e1.printStackTrace();
        }
      }
   
      @Override
      public void mouseEntered(MouseEvent e) 
      {
          // the mouse has entered the label
        hyperlink.setText("Source of Image");
      }
   
      @Override
      public void mouseExited(MouseEvent e) 
      {
          // the mouse has exited the label
        String text = "Source of Image";
        hyperlink.setText("<html><a href=''>" + text + "</a></html>");
      }
    });

    return hyperlink;
  }
  
  /**
   * Plays sound file for specified event.
   * @param soundName is the name of the specified file.
   */
  public static void playSound(String soundName) 
  {
    try 
	{
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName));
      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      clip.start();
	}
    catch(Exception ex) 
	{
      System.out.println("Error with playing sound.");
	  ex.printStackTrace( );
	}
  } 
  
  /**
   * Creates the game buttons for the Game tab.
   * @param title is the title of the game.
   * @param url is the link to the game.
   * @return the button created to the Game tab.
 * @throws MalformedURLException 
   */
  public static JButton createGameButton(String title, String url, String previewImage) throws MalformedURLException 
  {
	  
	//initializing and scaling random game icon
	URL preview = new URL(previewImage);
	ImageIcon icon = new ImageIcon(preview);
	Image tempImage = icon.getImage();
	Image image = tempImage.getScaledInstance(275, 200, java.awt.Image.SCALE_SMOOTH);
	ImageIcon finalImage = new ImageIcon(image);
		
	JButton button = new JButton("Random Game", finalImage);
	button.setPreferredSize( new Dimension(275, 200));
	button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try 
        {
	      Desktop.getDesktop().browse(new URI(url));
	    } catch (IOException | URISyntaxException e1) 
	    {
	      e1.printStackTrace();
	    }
	  }
	});	  
	return button;
	  
  }
  
  /**
   * Creates a random game button on the Game tab.
   * @param URLs is a list of game URL's
   * @return the Random game button to the Game tab.
 * @throws MalformedURLException 
   */
  public static JButton createRandomGameButton(ArrayList<String> URLs) throws MalformedURLException 
  {
	//initializing and scaling random game icon
	URL url = new URL("https://i.ytimg.com/vi/WkhcaxBEkdY/maxresdefault.jpg");
	ImageIcon icon = new ImageIcon(url);
	Image image = icon.getImage();
	Image previewImage = image.getScaledInstance(275, 200, java.awt.Image.SCALE_SMOOTH);
	ImageIcon finalImage = new ImageIcon(previewImage);
	
	JButton button = new JButton("Random Game", finalImage);
	button.setPreferredSize( new Dimension(275, 200));
	  
	int min = 0;
	int max = URLs.size() - 1;
	randomURLIndex = new Random().nextInt(max - min + 1) + min;
	  
	button.addActionListener(new ActionListener() 
	{
      public void actionPerformed(ActionEvent e) 
	  {
        try 
		{

		  Desktop.getDesktop().browse(new URI(URLs.get(randomURLIndex)));
          randomURLIndex = new Random().nextInt(max - min + 1) + min;
        } catch (IOException | URISyntaxException e1) 
	    {
		  e1.printStackTrace();
	    }
	 }
   });
   return button;
	  
  }
  
  /**
   * Adds all the game buttons to the Game Tab.
   * @return the JComponent to the Game tab.
 * @throws MalformedURLException 
   */
  public static JComponent generateGameTab() throws MalformedURLException 
  {
	  
	JPanel gameGUI = new JPanel();
	  	  
    ArrayList<String> gameURLs = new ArrayList<String>();
    gameURLs.add(0, "https://www.crazygames.com/game/dog-simulator-3d");
	gameURLs.add(1, "https://www.crazygames.com/game/jigsaw-puzzle-doggies");
    gameURLs.add(2, "https://www.crazygames.com/game/pavlov-s-dog");
	gameURLs.add(3, "http://www.crazygames.com/game/doge-2048");
    gameURLs.add(4, "https://www.crazygames.com/game/good-doggo");
    gameURLs.add(5, "https://www.crazygames.com/game/doge-miner");
	gameURLs.add(6, "https://www.crazygames.com/game/doge-miner-2");
    gameURLs.add(7, "https://www.crazygames.com/game/dogs-vs-homework");
 	gameURLs.add(8, "https://www.crazygames.com/game/puppy-sling");
    gameURLs.add(9, "https://www.crazygames.com/game/dogod-io");
    gameURLs.add(10, "https://kizi.com/games/paw-care");
	    
	JButton randomGame = createRandomGameButton(gameURLs);	  
	JButton game1 = createGameButton("Dog Simulator", gameURLs.get(0), 
			"https://images.crazygames.com/dog-simulator-3d/20210210175744/"
			+ "dog-simulator-3d-cover?auto=format,compress&q=75&cs=strip&ch=DPR&w=1200&h=630&fit=crop");	  
	JButton game2 = createGameButton("Dog Puzzles", gameURLs.get(1), "https://www.gannett-cdn.com/presto/"
			+ "2020/03/31/PDTF/39912e3e-bd26-43a8-b536-82d173dab47b-GettyImages-1147913430.jpg");	  
	JButton game3 = createGameButton("Pavlov's Dog", gameURLs.get(2), "https://images.crazygames.com/games/"
			+ "pavlov-s-dog/cover-1628869258061.png?auto=format,compress&q=75&cs=strip&ch=DPR&w=1200&h=630&fit=crop");	  
	JButton game4 = createGameButton("Doge 2048", gameURLs.get(3), "https://i.exophase.com/android/games/o/j670d.png?1534664365");
	JButton game5 = createGameButton("Good Doggo", gameURLs.get(4), "https://cdn.cloudflare.steamstatic.com/steam/apps/840670/"
			+ "capsule_616x353.jpg?t=1525156020");
	JButton game6 = createGameButton("Doge Miner", gameURLs.get(5), "https://images.crazygames.com/games/doge-miner/"
			+ "cover-1593443166599.png?auto=format,compress&q=75&cs=strip&ch=DPR&w=1200&h=630&fit=crop");
	JButton game7 = createGameButton("Doge Miner 2", gameURLs.get(6), "https://images.crazygames.com/games/doge-miner-2/"
			+ "cover-1593443163888.png?auto=format,compress&q=75&cs=strip&ch=DPR&w=1200&h=630&fit=crop");
	JButton game8 = createGameButton("Dogs vs Homework", gameURLs.get(7), "https://i.ytimg.com/vi/dpPzzsKCCuA/maxresdefault.jpg");
	JButton game9 = createGameButton("Puppy Sling", gameURLs.get(8), "https://www.marketjs.com/game/thumbnail/cached/"
			+ "ahVzfm1hcmtldGpzLWVudGVycHJpc2VyEQsSBEdhbWUYgICg5bXD_ggM");
	JButton game10 = createGameButton("Dogod.io", gameURLs.get(9), "https://images.crazygames.com/dogod-io/20210615081336/"
			+ "dogod-io-cover?auto=format,compress&q=75&cs=strip&ch=DPR&w=1200&h=630&fit=crop");
	JButton game11 = createGameButton("Paw Care", gameURLs.get(10), "https://i.ytimg.com/vi/umRYgMuVH_Y/mqdefault.jpg");
	  	  
	gameGUI.add(randomGame);
	gameGUI.add(game1);
	gameGUI.add(game2);
	gameGUI.add(game3);
	gameGUI.add(game4);
	gameGUI.add(game5);
	gameGUI.add(game6);
	gameGUI.add(game7);
	gameGUI.add(game8);
    gameGUI.add(game9);
	gameGUI.add(game10);
	gameGUI.add(game11);
	  
	return gameGUI;
	  
  }
  
  
  private static void createAndShowGUI() throws IOException 
  {
    //showStyles();
    try 
    {
      for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
      {
        if ("Nimbus".equals(info.getName())) 
        {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (Exception e) {
      // If Nimbus is not available, you can set the GUI to another look and feel.
    }
    // Create and set up the window.
    frame = new JFrame("Dog GUI");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setMinimumSize(new Dimension(500, 250));
    DogGUI splitPaneDemo = new DogGUI();
    splitPaneDemo.getSplitPane();
        
       
    JTabbedPane tabs = new JTabbedPane();
    JComponent homeTab = splitPaneDemo.getSplitPane();
    tabs.addTab("Home",homeTab);
    JComponent gameTab = generateGameTab();
    tabs.addTab("Dog Games",gameTab);
    frame.getContentPane().add(tabs);
    
    
    //playSound("WhoLetTheDogsOut.wav");
    pictureMethod();
    

    // Display the window.
    frame.pack();
    frame.setVisible(true);
  }

  /**
   * Main method for dog program.
   * 
   * @param args
   */
  public static void main(String[] args) 
  {
    // Schedule a job for the event-dispatching thread:
    // creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() 
    {
      public void run() 
      {
        try 
        {
          createAndShowGUI();

        } catch (IOException e) 
        {
          e.printStackTrace();
        }
      }
    });
  }
}
