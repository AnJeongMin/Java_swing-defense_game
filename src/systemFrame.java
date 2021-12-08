import java.awt.Toolkit;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Color;

// in game system page
public class systemFrame extends JDialog{
	
	private JPanel infoPanel;
	private JPanel stagePanel;
	private JPanel gameClearPanel;
	private JPanel gameOverPanel;
	
	Player player;
    gameView game;
    Sound sound;
    gameController controller;
    Toolkit tk = Toolkit.getDefaultToolkit();
    
    /* Information frame */
    private JButton unUseBtn;
    private JButton swordBtn;
    private JButton gunBtn;
    private JLabel swordLabel;
    private JLabel backInfoLabel;
    private JLabel gunLabel;
    private JLabel pickLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JTextPane textSword;
    private JTextPane textGun;
    /* Information frame */
    
    /* Stage frame */
    private JLabel charLabel;
    private JTextPane charText;
    private JLabel bossImgLabel;
    private JLabel nextBossLabel;
    private JLabel backStageLabel;
    private JLabel stageClearLabel;
    /* Stage frame */
    
    /* Game Clear frame */
    private JLabel backClearLabel;
    private JLabel gameClearLabel;
    private JLabel scoreLabel;
    private JButton titleBtn;
    private JTextPane gameClearText;
    private JTextField scoreText;
    private JButton recordBtn;
    /* Game Clear frame */
    
    /* Game Over frame */
    private JLabel backOverLabel;
    private JLabel gameOverLabel;
    private JButton reStartBtn;
    private JButton toTitleBtn;
    private JButton nextStageBtn;
    private JTextPane gameOverText;
    /* Game Over frame */
    
	public systemFrame(Player player, gameController controller) {
		this.player = player;
		this.game = controller.game;
		this.controller = controller;
		
		/* Main frame */
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setVisible(true);
		setResizable(false);
		setSize(487,337);
        setModal(true);
     
    	setIconImage(tk.getImage(new ImgGen(13).imgUrl()));
    	setTitle("CASTLE DEFENSE");
		
		setBounds(500, 200, 600, 400);
		setResizable(false);
		getContentPane().setLayout(null);
		
		infoPanel = new JPanel();
		infoPanel.setBounds(0, 0, 586, 363);
		getContentPane().add(infoPanel);
		infoPanel.setLayout(null);
		infoPanel.setVisible(false);
		
		stagePanel = new JPanel();
		stagePanel.setBounds(0, 0, 586, 363);
		getContentPane().add(stagePanel);
		stagePanel.setLayout(null);
		stagePanel.setVisible(false);
		
		gameClearPanel = new JPanel();
		gameClearPanel.setBounds(0, 0, 586, 363);
		getContentPane().add(gameClearPanel);
		gameClearPanel.setLayout(null);
		gameClearPanel.setVisible(false);
		
		gameOverPanel = new JPanel();
		gameOverPanel.setBounds(0, 0, 586, 363);
		getContentPane().add(gameOverPanel);
		gameOverPanel.setLayout(null);
		gameOverPanel.setVisible(false);
		/* Main frame */
		
		/* Information frame */
		unUseBtn = new JButton("");
		unUseBtn.setBorderPainted(false);
		unUseBtn.setContentAreaFilled(false);
		unUseBtn.setBounds(0, 0, 0, 0);
		infoPanel.add(unUseBtn);
		
		swordBtn = new JButton("SWORD");
		swordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 setVisible(false);
				 game.player.weaponIndex = 1;
				 game.player.image = tk.getImage(new ImgGen(28).imgUrl());
				 game.cloud = new cloudTimer(); 
				 controller.initialControl();
				 controller.gEnemy.enemyDelay = 0;
				 gameView.stage = 1;
				 Thread thread = new Thread(controller);
				 thread.start();
			}
		});
		swordBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				swordBtn.setForeground(Color.BLUE);
				swordBtn.setFont(new Font("Maiandra GD", Font.PLAIN, 45));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				swordBtn.setForeground(Color.BLACK);
				swordBtn.setFont(new Font("Maiandra GD", Font.PLAIN, 40));
			}
		});
		swordBtn.setBorderPainted(false);
		swordBtn.setContentAreaFilled(false);
		swordBtn.setFont(new Font("Maiandra GD", Font.PLAIN, 40));
		swordBtn.setBounds(37, 297, 214, 56);
		infoPanel.add(swordBtn);
		
		gunBtn = new JButton("GUN");
		gunBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				 game.player.weaponIndex = 2;
				 game.player.image = tk.getImage(new ImgGen(30).imgUrl());
				 game.cloud = new cloudTimer(); 
				 controller.initialControl();
				 controller.gEnemy.enemyDelay = 0;
				 gameView.stage = 1;
				 Thread thread = new Thread(controller);
				 thread.start();
			}
		});
		gunBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				gunBtn.setForeground(Color.BLUE);
				gunBtn.setFont(new Font("Maiandra GD", Font.PLAIN, 45));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				gunBtn.setForeground(Color.BLACK);
				gunBtn.setFont(new Font("Maiandra GD", Font.PLAIN, 40));
			}
		});
		gunBtn.setBorderPainted(false);
		gunBtn.setContentAreaFilled(false);
		gunBtn.setFont(new Font("Maiandra GD", Font.PLAIN, 40));
		gunBtn.setBounds(343, 297, 214, 56);
		infoPanel.add(gunBtn);
		
		swordLabel = new JLabel("");
		swordLabel.setIcon(new ImageIcon(new ImgGen(28).imgUrl()));
		swordLabel.setBounds(13, 109, 105, 120);
		infoPanel.add(swordLabel);
		
		gunLabel = new JLabel("");
		gunLabel.setIcon(new ImageIcon(new ImgGen(31).imgUrl()));
		gunLabel.setBounds(305, 109, 100, 126);
		infoPanel.add(gunLabel);
		
		pickLabel = new JLabel("Pick One");
		pickLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pickLabel.setBounds(263, 329, 93, 15);
		infoPanel.add(pickLabel);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(new ImgGen(32).imgUrl()));
		lblNewLabel_1.setBounds(117, 146, 150, 62);
		infoPanel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(new ImgGen(37).imgUrl()));
		lblNewLabel_2.setBounds(130, 10, 150, 126);
		infoPanel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(new ImgGen(34).imgUrl()));
		lblNewLabel_3.setBounds(451, 146, 40, 46);
		infoPanel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(new ImgGen(35).imgUrl()));
		lblNewLabel_4.setBounds(426, 52, 105, 62);
		infoPanel.add(lblNewLabel_4);
		
		textSword = new JTextPane();
		textSword.setEditable(false);
 	    textSword.setOpaque(false);
 	    textSword.setFont(new Font("Maiandra GD", Font.PLAIN, 12));
		textSword.setText("X : normal attack \n strong, short range \nZ : ultimate \n week, wide range\nC : castle ultimate");
		textSword.setBounds(127, 205, 136, 82);
		infoPanel.add(textSword);
		
		textGun = new JTextPane();
		textGun.setEditable(false);
		textGun.setOpaque(false);
		textGun.setFont(new Font("Maiandra GD", Font.PLAIN, 12));
		textGun.setText("X : normal attack \n week, long range \nZ : ultimate \n strong, narrow range\nC : castle ultimate");
		textGun.setBounds(426, 205, 136, 82);
		infoPanel.add(textGun);
		
		backInfoLabel = new JLabel("");
		backInfoLabel.setIcon(new ImageIcon(new ImgGen(7).imgUrl()));
		backInfoLabel.setBounds(0, 0, 586, 363);
		infoPanel.add(backInfoLabel);
		/* Information frame */
		
		/* Stage frame */
		stageClearLabel = new JLabel("STAGE " + (gameView.stage - 1) + " clear");
		stageClearLabel.setForeground(Color.DARK_GRAY);
		stageClearLabel.setHorizontalAlignment(SwingConstants.CENTER);
		stageClearLabel.setBounds(12, 0, 562, 61);
		stageClearLabel.setFont(new Font("Maiandra GD", Font.PLAIN, 50));
		stagePanel.add(stageClearLabel);
		
		charLabel = new JLabel("");
		if(player.weaponIndex == 1) charLabel.setIcon(new ImageIcon(new ImgGen(28).imgUrl()));
		else if(player.weaponIndex == 2) charLabel.setIcon(new ImageIcon(new ImgGen(31).imgUrl()));
		charLabel.setBounds(72, 109, 108, 120);
		stagePanel.add(charLabel);
		
		String charInfoString = "Damege UP\n";
		charText = new JTextPane();
		charText.setEditable(false);
		charText.setOpaque(false);
		charText.setFont(new Font("Maiandra GD", Font.PLAIN, 14));
		if(gameView.stage % 2 == 0) charInfoString += "X delay Down\nSpeed UP\n"; // When stage 2 and 4, delay down and speed up
		if(player.ultiPlayer < 3) { // When stage clear, ultimate number add (max : 3) 
			charInfoString += "Ulti(Z) +1\n";
			player.ultiPlayer++; 
		}
		if(player.ultiCastle < 3) {
			charInfoString += "Ulti(C) +1\n";
			player.ultiCastle++; 
		}
			
		charText.setText(charInfoString);
		charText.setBounds(190, 126, 108, 100);
		stagePanel.add(charText);

		bossImgLabel = new JLabel("");
		bossImgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bossImgLabel.setIcon(new ImageIcon(new ImgGen(22 + gameView.stage).imgUrl()));
		bossImgLabel.setBounds(310, 71, 264, 167);
		stagePanel.add(bossImgLabel);
		
		nextBossLabel = new JLabel("Next Boss");
		nextBossLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nextBossLabel.setFont(new Font("Maiandra GD", Font.PLAIN, 30));
		nextBossLabel.setBounds(310, 256, 264, 31);
		stagePanel.add(nextBossLabel);
		
		nextStageBtn = new JButton("Next stage");
		nextStageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				game.bossDead = false;
				controller.stageClear = false;
				controller.gEnemy.enemyDelay = 0;
				game.cloud = new cloudTimer();
				Thread thread = new Thread(controller);
				thread.start();
				game.backSound = new Sound(2);
			}
		});
		nextStageBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				nextStageBtn.setForeground(Color.BLUE);
				nextStageBtn.setFont(new Font("Maiandra GD", Font.PLAIN, 45));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				nextStageBtn.setForeground(Color.BLACK);
				nextStageBtn.setFont(new Font("Maiandra GD", Font.PLAIN, 40));
			}
		});
		nextStageBtn.setBorderPainted(false);
		nextStageBtn.setContentAreaFilled(false);
		nextStageBtn.setFont(new Font("Maiandra GD", Font.PLAIN, 40));
		nextStageBtn.setBounds(156, 276, 273, 68);
		stagePanel.add(nextStageBtn);
		
		backStageLabel = new JLabel("");
		backStageLabel.setForeground(Color.ORANGE);
		backStageLabel.setIcon(new ImageIcon(new ImgGen(7).imgUrl()));
		backStageLabel.setBounds(0, 0, 586, 363);
		stagePanel.add(backStageLabel);
		/* Stage frame */
		
		/* Game Clear frame */
		gameClearLabel = new JLabel("Game Clear");
		gameClearLabel.setForeground(Color.ORANGE);
		gameClearLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameClearLabel.setBounds(12, 10, 562, 82);
		gameClearLabel.setFont(new Font("Maiandra GD", Font.PLAIN, 50));
		gameClearPanel.add(gameClearLabel);
		
		titleBtn = new JButton("Title");
		titleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.dispose();
				gameView.stage = 1;
				dispose();
				titleFrame frame = new titleFrame();
				frame.setVisible(true);
			}
		});
		titleBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				titleBtn.setForeground(Color.BLUE);
				titleBtn.setFont(new Font("Maiandra GD", Font.PLAIN, 45));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				titleBtn.setForeground(Color.BLACK);
				titleBtn.setFont(new Font("Maiandra GD", Font.PLAIN, 40));
			}
		});
		titleBtn.setBorderPainted(false);
		titleBtn.setContentAreaFilled(false);
		titleBtn.setFont(new Font("Maiandra GD", Font.PLAIN, 40));
		titleBtn.setBounds(186, 254, 214, 74);
		gameClearPanel.add(titleBtn);
		
		gameClearText = new JTextPane();
		gameClearText.setEditable(false);
		gameClearText.setOpaque(false);
		gameClearText.setFont(new Font("Maiandra GD", Font.PLAIN, 25));
		gameClearText.setText(" You protected the castle!\n You are a hero of the kingdom\n Please tell me your name");
		gameClearText.setBounds(37, 135, 352, 99);
		gameClearPanel.add(gameClearText);
		
		scoreLabel = new JLabel("SCORE : "+player.gameScore);
		scoreLabel.setForeground(Color.BLACK);
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setFont(new Font("Maiandra GD", Font.PLAIN, 15));
		scoreLabel.setBounds(401, 129, 173, 33);
		gameClearPanel.add(scoreLabel);
		
		scoreText = new JTextField();
		scoreText.setHorizontalAlignment(SwingConstants.CENTER);
		scoreText.setFont(new Font("±¼¸²", Font.BOLD, 20));
		scoreText.setBounds(434, 166, 115, 36);
		gameClearPanel.add(scoreText);
		scoreText.setColumns(10);
		
		recordBtn = new JButton("Record");
		recordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rankWrite();
				recordBtn.setText("END");
				recordBtn.setEnabled(false);
			}
			private void rankWrite() { // Record user score when game clear
				try {			
					File file = new File("C:\\Users\\AnJeongMin\\eclipse-workspace\\Final_Project\\src\\rank.txt");
					FileOutputStream fileObject = new FileOutputStream(file, true);
					PrintWriter x = new PrintWriter(fileObject);
					x.println(player.gameScore);                          // e.x) [0] 23400
					x.println(scoreText.getText());                       // e.x) [1] AJM
					x.close();											  // First score -> user -> score -> user ...
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		recordBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					recordBtn.setForeground(Color.BLUE);
					recordBtn.setFont(new Font("Maiandra GD", Font.PLAIN, 30));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					recordBtn.setForeground(Color.BLACK);
					recordBtn.setFont(new Font("Maiandra GD", Font.PLAIN, 25));
			}
		});
		recordBtn.setBorderPainted(false);
		recordBtn.setContentAreaFilled(false);
		recordBtn.setFont(new Font("Maiandra GD", Font.PLAIN, 25));
		recordBtn.setBounds(417, 199, 140, 45);
		gameClearPanel.add(recordBtn);
		
		backClearLabel = new JLabel("");
		backClearLabel.setIcon(new ImageIcon(new ImgGen(7).imgUrl()));
		backClearLabel.setBounds(0, 0, 586, 363);
		gameClearPanel.add(backClearLabel);
		/* Game Clear frame */
		
		/* Game Over frame */
		gameOverLabel = new JLabel("Game Over");
		gameOverLabel.setForeground(Color.DARK_GRAY);
		gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameOverLabel.setBounds(12, 10, 562, 82);
		gameOverLabel.setFont(new Font("Maiandra GD", Font.PLAIN, 50));
		gameOverPanel.add(gameOverLabel);
		
		reStartBtn = new JButton("Go-Past");
		reStartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// invisiblePanel();
				gameView.stage = 1;
				dispose();
				controller.initialControl();
				new systemFrame(player, controller);
				game.backSound = new Sound(2);
			}
		});
		reStartBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				reStartBtn.setForeground(Color.BLUE);
				reStartBtn.setFont(new Font("Maiandra GD", Font.PLAIN, 45));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				reStartBtn.setForeground(Color.BLACK);
				reStartBtn.setFont(new Font("Maiandra GD", Font.PLAIN, 40));
			}
		});
		reStartBtn.setBorderPainted(false);
		reStartBtn.setContentAreaFilled(false);
		reStartBtn.setFont(new Font("Maiandra GD", Font.PLAIN, 40));
		reStartBtn.setBounds(37, 297, 214, 56);
		gameOverPanel.add(reStartBtn);
		
		toTitleBtn = new JButton("To-Title");
		toTitleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.dispose();
				gameView.stage = 1;
				dispose();
				titleFrame frame = new titleFrame();
				frame.setVisible(true);
			}
		});
		toTitleBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				toTitleBtn.setForeground(Color.BLUE);
				toTitleBtn.setFont(new Font("Maiandra GD", Font.PLAIN, 45));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				toTitleBtn.setForeground(Color.BLACK);
				toTitleBtn.setFont(new Font("Maiandra GD", Font.PLAIN, 40));
			}
		});
		toTitleBtn.setBorderPainted(false);
		toTitleBtn.setContentAreaFilled(false);
		toTitleBtn.setFont(new Font("Maiandra GD", Font.PLAIN, 40));
		toTitleBtn.setBounds(333, 297, 214, 56);
		gameOverPanel.add(toTitleBtn);
				
		gameOverText = new JTextPane();
		gameOverText.setEditable(false);
		gameOverText.setOpaque(false);
		gameOverText.setFont(new Font("Maiandra GD", Font.PLAIN, 25));
		gameOverText.setText(" The castle was destroyed by enemies... \n Go back to the past and protect your castle!");
		gameOverText.setBounds(37, 158, 511, 77);
		gameOverPanel.add(gameOverText);
		
		backOverLabel = new JLabel("");
		backOverLabel.setIcon(new ImageIcon(new ImgGen(7).imgUrl()));
		backOverLabel.setBounds(0, 0, 586, 363);
		gameOverPanel.add(backOverLabel);
		/* Game Over frame */
				
		invisiblePanel();
		changePanel();
	}
	
	// Whenever system frame opened, correct panel opened depend on stage index  
	void changePanel() {
		if(gameView.stage == 1) { // Start panel
			infoPanel.setVisible(true);
		}
		if(controller.stageClear == true) { // Stage panel
			if(gameView.stage > 5) {
				gameClearPanel.setVisible(true);
				new Sound(12); 
				gameView.stage = 0;
			}
			else{ // Game clear
				stagePanel.setVisible(true);
	            new Sound(10);
			}
		}
		if(controller.gameOver == true) { // Game over
			gameOverPanel.setVisible(true);
            new Sound(11);
		}
	}
	
	// Initial setting 
	void invisiblePanel() {
		infoPanel.setVisible(false);
		stagePanel.setVisible(false);
		gameClearPanel.setVisible(false);
		gameOverPanel.setVisible(false);
	}
}
