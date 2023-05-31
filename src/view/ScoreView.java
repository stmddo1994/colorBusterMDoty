/**
 * 
 */
package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;


/**
 * @author Frank J. Mitropoulos
 * edited by Mathew Doty
 *  A very simple score panel
 *  call updateScore and pass in the score to update the display
 */
public class ScoreView extends JPanel {

	
	private static final long serialVersionUID = 1L;

	private JLabel scoreLabel;
	private int savedScore;
	
	public ScoreView() {
		savedScore = 0;
		scoreLabel = new JLabel("Score: " + Integer.toString(savedScore));
		add(scoreLabel);

	}
	
	public void updateScore(int score) {
		savedScore += score;
		scoreLabel.setText("Score: " + savedScore);
	}

	public void resetScore(){
		savedScore = 0;
		scoreLabel.setText("Score: " + savedScore);
	}

}
