/**
 * 
 */
package view;
import javax.swing.JFrame;
import java.awt.*;

//import view.ScoreView;
//import view.ButtonView;
import view.BoardView;
import java.awt.event.*;

/**
 * @author Frank J. Mitropoulos
 * edited by Mathew Doty
 */
public class GameView extends JFrame {
	// Create the HUD Panel
	// Create the Board
	
	
	private static final long serialVersionUID = 1L;
	private ScoreView scoreView;
	private ButtonView buttonView;
	private BoardView boardView;
	
	private int score;
	
	// Setting defaul to 8x8
	
	int rows = 8, cols = 8;
	int width, height;
	

	ActionListener newGameListener;
	ActionListener tileTouchedListener;

	
	/**
	 * @param title
	 * @throws HeadlessException
	 */

	private int matchSize;
	ActionListener levelListener;
	
	public GameView(String title, int rows, int cols, MouseListener listener, ActionListener newGameListener, ActionListener tileTouched, ActionListener levelListener, int matchSize) throws HeadlessException {
		//added match size and level listener parameters
		super(title);
		setResizable(false);
		
		width = 500;
		height = 500;
		score = 0;
		this.rows = rows;
		this.cols = cols;
		
		this.newGameListener = newGameListener;
		this.tileTouchedListener = tileTouched;
		
		// Set up some reasonable sizes for the gameview
		
		setBounds(100,50,width, height);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		scoreView = new ScoreView();
    	add(scoreView, BorderLayout.NORTH);
		
    	buttonView = new ButtonView(newGameListener, levelListener);
    	add(buttonView, BorderLayout.SOUTH);

		this.matchSize = matchSize; //add match size from call function
		boardView = new BoardView(rows,cols, tileTouchedListener, matchSize);
		boardView.setMatchSize(matchSize); //set match size to the board view
		add(boardView, BorderLayout.CENTER);
		
		setVisible(true);
				
	}
	
	// Delegate to boardView
	public boolean isMoveAvailable() {
		if(boardView.isMoveAvailable()) //call is move available function from board view
			return true;
		else
			return false;
	}
	
	// Call this method to start a new Game
	
	public void newGame(int matchSize) {
		// Recreate some components and update the GUI.
		
		Container c = getContentPane();
		c.remove(boardView);
		c.invalidate();
		pack();

		boardView = null;
		score = 0;
		scoreView.resetScore(); //reset score to 0
		
		boardView = new BoardView(rows,cols, tileTouchedListener, matchSize);
		clearSize(matchSize); //update match size for new game

		add(boardView, BorderLayout.CENTER);
		pack();
		revalidate();
		setBounds(100,50,width, height);
		
		System.out.println(boardView);  // debug
	}
	
	public void processTouchedTile(TileView tv) {
		// You need to implement this method. It is called when a tileview is touched
		System.out.println("GameView == processing tile touch");

		int x = boardView.processTouchedTile(tv); //retrieve score from tile function
		scoreView.updateScore(x); //update score to scoreboard
		System.out.println(boardView); // debug
	}

	public void clearSize(int matchSize){ //convert the match size and update the board view's match size
		this.matchSize = matchSize;
		boardView.setMatchSize(matchSize);
		System.out.println(matchSize);
	}

}
