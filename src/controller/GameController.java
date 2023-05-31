/**
 * 
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import view.GameView;
import view.TileView;

/**
 * @author Frank J. Mitropoulos
 * edited by Mathew Doty
 */
public class GameController {
	// These aren't used, but could be depending on your gme and what you want to do
	private int score;
	private int gameStatus;
	private int rows;
	private int cols;
	private final int dfault = 3; //default match size
	
	private int moveNumber = 0;
	
	private GameView gameView;

	private int matchSize; //match size will be the amount of adjacent tiles needed to clear out
	
	

	/**
	 * Create the GameView and pass in the appropriate listeners
	 */
	public GameController() {
		super();		
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				matchSize = dfault; //start game with 3 match default

				gameView = new GameView("Color Buster",6,6,null,new NewGameListener(), new TileTouchedListener(), new LevelListener(), matchSize);
				gameView.setVisible(true);	
				
			}
		});
		
	}
	
	// Listener used to process touches on TileView
	
	class TileTouchedListener implements ActionListener {
		@Override 
		public void actionPerformed(ActionEvent event) {
			TileView tv = (TileView) event.getSource();
			System.out.println("Tile touched..." + tv.toString());
			
			
			gameView.processTouchedTile(tv);
			// If no move is available display a message
			
			if (!gameView.isMoveAvailable()) {
				JOptionPane.showMessageDialog(gameView,
					    "No more moves...\nStart a New Game");
			}
		}
		
	}
	
	// Listener used to process click on New Game Button
	
	class NewGameListener implements ActionListener { //Start new game with match size parameter
		@Override 
		public void actionPerformed(ActionEvent event) {
			System.out.println("Starting new game...");
			gameView.newGame(matchSize);


		}
		
	}

	class LevelListener implements ActionListener { //Level listener to retrieve match number from level selector combo box
		@Override
		public void actionPerformed(ActionEvent event) {
			JComboBox cb = (JComboBox)event.getSource();
			String level = (String)cb.getSelectedItem();
			matchSize = Integer.parseInt(level);
			gameView.clearSize(matchSize);
		}

	}
}
