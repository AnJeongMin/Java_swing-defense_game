import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Font;

// rank board page (Using JDialog for sub frame in title frame)
public class rankFrame extends JDialog{
	private JLabel backLabel;
	private JLabel rankBoardLabel;
	private JLabel rankInfoLabel;
	
    public rankFrame() {
		// TODO 磊悼 积己等 积己磊 胶庞
    	setBounds(550, 250, 450, 300);
    	
    	setIconImage(Toolkit.getDefaultToolkit().getImage(new ImgGen(12).imgUrl()));
    	setTitle("Rank Board");
    	getContentPane().setLayout(null);
    	 	    
 	    rankBoardLabel = new JLabel("Rank Board");
 	    rankBoardLabel.setFont(new Font("Maiandra GD", Font.PLAIN, 30));
 	    rankBoardLabel.setHorizontalAlignment(SwingConstants.CENTER);
 	    rankBoardLabel.setBounds(12, 10, 449, 56);
 	    getContentPane().add(rankBoardLabel);
	    
	    rankInfoLabel = new JLabel("     [RANK]              [USER]                [SCORE]");
	    rankInfoLabel.setFont(new Font("Maiandra GD", Font.PLAIN, 20));
	    rankInfoLabel.setBounds(12, 76, 449, 38);
	    getContentPane().add(rankInfoLabel);
	    
	    rankPrint(); // Real rank data
	    
 	    backLabel = new JLabel("");
	    backLabel.setBounds(0, 0, 473, 413);
	    backLabel.setIcon(new ImageIcon(new ImgGen(7).imgUrl()));
	    getContentPane().add(backLabel);
	    
        setResizable(false);
        setSize(487,450);
        setModal(true);
        setVisible(true);
	}
    
    private void rankPrint() {
		try {
			File file = new File("C:\\Users\\AnJeongMin\\eclipse-workspace\\Final_Project\\src\\rank.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			ArrayList<String> rank = new ArrayList<String>();  // Restore all data 
			ArrayList<Integer> score = new ArrayList<Integer>(); // Restore integer data
			ArrayList<String> scoreStr = new ArrayList<String>(); // Converted score integer to string 
			String str;
			while((str = br.readLine()) != null) {
				rank.add(str); // Read data for string
			}
			
			for(int i = 0; i < rank.size() / 2; i++) {
				score.add(Integer.parseInt(rank.get(2*i)));
			} // Convert to integer for sorting
			
			if(rank.size() == 0) { // When game record not exist
				JTextPane nullPane = new JTextPane();
				nullPane.setOpaque(false);
				nullPane.setEditable(false);
				nullPane.setText("There's no game record\n\nProtect the castle and leave your name to people!");
				nullPane.setFont(new Font("Maiandra GD", Font.PLAIN, 20));
				nullPane.setBounds(15, 180, 500, 400);
			    getContentPane().add(nullPane);
			}
			
			Collections.sort(score);
			for(int i = 0; i < score.size(); i++) {
				scoreStr.add(score.get(i) + "");
			} // After sorting, covert integer to string
			
			/* Ranking print */
			int ranking = 1; 
			for(int i = scoreStr.size(); i >= 1; i--) {	
						
				JLabel rankLabel = new JLabel(ranking +"");
				rankLabel.setHorizontalAlignment(SwingConstants.CENTER);
				rankLabel.setFont(new Font("Maiandra GD", Font.PLAIN, 20));
				rankLabel.setBounds(27, 124 + 25 * (ranking - 1), 100, 25);
			    getContentPane().add(rankLabel);
			    
			    int x = rank.indexOf(scoreStr.get(i - 1));
			    JLabel nameLabel = new JLabel(rank.get(x + 1));
			    nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			    nameLabel.setFont(new Font("Maiandra GD", Font.PLAIN, 20));
			    nameLabel.setBounds(169, 124 + 25 * (ranking - 1), 100, 25);
			    getContentPane().add(nameLabel);
			    
			    JLabel scoreLabel = new JLabel(rank.get(x));
			    scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
			    scoreLabel.setFont(new Font("Maiandra GD", Font.PLAIN, 20));
			    scoreLabel.setBounds(325, 124 + 25 * (ranking - 1), 100, 25);
			    getContentPane().add(scoreLabel);
			    
			    if(ranking == 10) break; // Cancel under ranking 10
			    
			    ranking++;
			}
			/* Ranking print */
			
		} catch (Exception e) { // When "rank.txt" is not created
			// TODO Auto-generated catch block
			JTextPane nullPane = new JTextPane();
			nullPane.setOpaque(false);
			nullPane.setEditable(false);
			nullPane.setText("There's no game record\n\nProtect the castle and leave your name to people!");
			nullPane.setFont(new Font("Maiandra GD", Font.PLAIN, 20));
			nullPane.setBounds(15, 180, 500, 400);
		    getContentPane().add(nullPane);
			e.printStackTrace();
		}
    }
}
