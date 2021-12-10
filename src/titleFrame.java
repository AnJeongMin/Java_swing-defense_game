import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;


// game title page
public class titleFrame extends JFrame {

	private JPanel contentPane;
	private JButton startButton;
	private JButton settingButton;
	private JButton rankButton;
	private JLabel castleLabel;
	private JLabel backgroundLabel;
	private JPanel titlePanel;
	
	Sound sound;
	titleFrame title = this;
	settingFrame setting;
	Toolkit tk = Toolkit.getDefaultToolkit();
	
	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					titleFrame frame = new titleFrame();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*  Create the frame */
	public titleFrame() {
		setBackground(Color.WHITE);
		 
		setIconImage(tk.getImage(new ImgGen(13).imgUrl()));
		setTitle("CASTLE DEFENSE");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 50, 1000, 700);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		titlePanel = new JPanel();
		titlePanel.setBackground(Color.WHITE);
		titlePanel.setBounds(0, 0, 986, 663);
		titlePanel.setLayout(null);
		contentPane.add(titlePanel);
		
		/*  title Panel - start */
		startButton = new JButton("");
		ImageIcon startImage = new ImageIcon(new ImgGen(1).imgUrl());
		startButton.setIcon(startImage);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon mouseOver = startImage;
				mouseOver = imageSetSize(mouseOver, 450, 240);
				startButton.setIcon(mouseOver);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startImage);
			}
		});
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setBounds(433, 307, 280, 104);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player player = new Player(-1);
	            new gameView(player, title);
	            sound.soundStop();
	            new Sound(14);
				setVisible(false);
			}
		});
		titlePanel.add(startButton);
		
		settingButton = new JButton("");
		ImageIcon settingImage = new ImageIcon(new ImgGen(2).imgUrl());
		settingButton.setIcon(settingImage);
		settingButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon mouseOver = settingImage;
				mouseOver = imageSetSize(mouseOver, 360, 200);
				settingButton.setIcon(mouseOver);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				settingButton.setIcon(settingImage);
			}
		});
		settingButton.setBorderPainted(false);
		settingButton.setContentAreaFilled(false);
		settingButton.setBounds(433, 408, 280, 104);
		settingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sound.soundStop();
				new Sound(14);
				new settingFrame(title); 
			}
		});
		
		titlePanel.add(settingButton); 
		
		rankButton = new JButton("");
		ImageIcon rankImage = new ImageIcon(new ImgGen(3).imgUrl());
		rankButton.setIcon(rankImage);
		rankButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon mouseOver = rankImage;
				mouseOver = imageSetSize(mouseOver, 360, 200);
				rankButton.setIcon(mouseOver);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rankButton.setIcon(rankImage);
			}
		});
		rankButton.setBorderPainted(false);
		rankButton.setContentAreaFilled(false);
		rankButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Sound(14);
				new rankFrame();
			}
		});
		rankButton.setBounds(433, 513, 280, 104);
		titlePanel.add(rankButton);
		
		castleLabel = new JLabel("");
		castleLabel.setIcon(new ImageIcon(new ImgGen(13).imgUrl()));
		castleLabel.setBounds(5, 111, 342, 542);
		titlePanel.add(castleLabel);
		
		ImageIcon imgIcon = new ImageIcon(new ImgGen(4).imgUrl());
		backgroundLabel = new JLabel("");
		backgroundLabel.setIcon(imgIcon);
		backgroundLabel.setBounds(12, 10, 962, 643);
		titlePanel.add(backgroundLabel);
		
		sound = new Sound(1);
		/* title Panel - end */
	}
	
	// For mouse over event(menu button)
	public ImageIcon imageSetSize(ImageIcon icon, int i, int j) {
		Image xImage = icon.getImage();
		Image yImage = xImage.getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
		ImageIcon xyImage = new ImageIcon(yImage);
		return xyImage;
	}
}


