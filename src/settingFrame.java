import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

// setting page (Using JDialog for sub frame in title frame)
public class settingFrame extends JDialog{
	
	private JLabel volunmeControlLabel;
	private JButton volumeUpButton;
	private JButton volumeDownButton;
	private JButton levelUpButton;
	private JButton levelDownButton;
	private JLabel backLabel;
	private JLabel levelLabel;
	private JLabel levelTextLabel;
	
	static int levelIndex = 1;
	
	settingFrame setting = this;
	JTextField textField;
	Sound sound;

       public settingFrame(titleFrame title) {
    	   this.sound = title.sound;
    	   
    	   setVisible(true);
           setResizable(false);
    	   
           setIconImage(Toolkit.getDefaultToolkit().getImage(new ImgGen(13).imgUrl()));
           
    	   getContentPane().setForeground(Color.BLACK);
       	   setTitle("SETTING");
       	   getContentPane().setBackground(Color.WHITE);

    	   setBounds(550, 250, 450, 300);
    	   
    	   getContentPane().setLayout(null);
    	   
    	   volunmeControlLabel = new JLabel("");
    	   volunmeControlLabel.setIcon(new ImageIcon(new ImgGen(42).imgUrl()));
    	   volunmeControlLabel.setBounds(25, 60, 190, 99);
    	   getContentPane().add(volunmeControlLabel);
    	   
    	   /* Sound control */
    	   volumeUpButton = new JButton("");
    	   volumeUpButton.setBorderPainted(false);
   		   volumeUpButton.setContentAreaFilled(false);
   		   volumeUpButton.setIcon(new ImageIcon(new ImgGen(5).imgUrl()));
    	   volumeUpButton.addActionListener(new ActionListener() {
    	   	public void actionPerformed(ActionEvent e) {
    	   		sound.soundControl(1, setting);
    	   		new Sound(14);
    	   	}
    	   });
    	   volumeUpButton.setBounds(378, 74, 95, 78);
    	   getContentPane().add(volumeUpButton);
    	   
    	   volumeDownButton = new JButton("");
    	   volumeDownButton.setBorderPainted(false);
    	   volumeDownButton.setContentAreaFilled(false);
    	   volumeDownButton.setIcon(new ImageIcon(new ImgGen(6).imgUrl()));
    	   volumeDownButton.addActionListener(new ActionListener() {
    	   	public void actionPerformed(ActionEvent e) {
    	   		sound.soundControl(0, setting);
    	   		new Sound(14);
    	   	}
    	   });
    	   volumeDownButton.setBounds(213, 74, 95, 78);
    	   getContentPane().add(volumeDownButton);
    	   
    	   textField = new JTextField();
    	   textField.setForeground(Color.GRAY);
    	   textField.setBackground(Color.WHITE);
    	   textField.setHorizontalAlignment(SwingConstants.CENTER);
    	   textField.setEditable(false);
    	   textField.setOpaque(false);
    	   textField.setFont(new Font("Harlow Solid Italic", Font.PLAIN, 40));
    	   textField.setBorder(null);
    	   textField.setBounds(300, 90, 86, 48);
    	   getContentPane().add(textField);
    	   textField.setColumns(10);	
    	   // Whenever open setting page, to maintain the previous volume value and print percentage
    	   sound.soundControl(0, setting);	
    	   sound.soundControl(1, setting);
    	   /* Sound control */
    	   
    	   /* Level control */
    	   levelLabel = new JLabel("");
    	   levelLabel.setBounds(35, 174, 146, 88);
    	   levelLabel.setIcon(new ImageIcon(new ImgGen(8).imgUrl()));
    	   getContentPane().add(levelLabel);
    	   
    	   levelDownButton = new JButton("");
    	   levelDownButton.addActionListener(new ActionListener() {
    	   	public void actionPerformed(ActionEvent e) {
    	   		controlLevel(-1);
    	   		new Sound(14);
    	   		setLevel(levelIndex);
    	   	}
    	   });
    	   levelDownButton.setBounds(189, 181, 95, 71);
    	   levelDownButton.setBorderPainted(false);
    	   levelDownButton.setContentAreaFilled(false);
    	   levelDownButton.setIcon(new ImageIcon(new ImgGen(6).imgUrl()));
    	   getContentPane().add(levelDownButton);
    	   
    	   levelUpButton = new JButton("");
    	   levelUpButton.addActionListener(new ActionListener() {
    	   	public void actionPerformed(ActionEvent e) {
    	   		controlLevel(1);
    	   		new Sound(14);
    	   		setLevel(levelIndex);
    	   	}
    	   });
    	   levelUpButton.setBounds(378, 181, 95, 71);
    	   levelUpButton.setBorderPainted(false);
    	   levelUpButton.setContentAreaFilled(false);
    	   levelUpButton.setIcon(new ImageIcon(new ImgGen(5).imgUrl()));
    	   getContentPane().add(levelUpButton);
    	   
    	   levelTextLabel = new JLabel("");
    	   levelTextLabel.setBounds(250, 181, 136, 71);
    	   
    	   getContentPane().add(levelTextLabel);
    	   setLevel(levelIndex);
    	   /* Level control */
    	   
    	   backLabel = new JLabel("");
	   	   backLabel.setBounds(0, 0, 473, 300);
	   	   backLabel.setIcon(new ImageIcon(new ImgGen(7).imgUrl()));
	   	   getContentPane().add(backLabel);
	   	   
           setSize(487,337);
           setModal(true);
       }
       
       public void controlLevel(int input) {
    	   if(input == -1 && levelIndex > 1) levelIndex += input;
    	   else if(input == 1 && levelIndex < 3) levelIndex += input;
       }
       
       // Game level print
       public void setLevel(int level) {
    	   if(levelIndex == 1) levelTextLabel.setIcon(new ImageIcon(new ImgGen(9).imgUrl()));
    	   else if(levelIndex == 2) levelTextLabel.setIcon(new ImageIcon(new ImgGen(10).imgUrl()));
    	   else if(levelIndex == 3) levelTextLabel.setIcon(new ImageIcon(new ImgGen(11).imgUrl()));
       }
}
	