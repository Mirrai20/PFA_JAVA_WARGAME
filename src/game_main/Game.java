/**
 *
 * @author BOUFADEN Nourhene, BOUNOUH Siwar, CHABIR Yassine, CHRIGUI Nader, EL BAROUDI Marouane, TOUATI Aladine

 */
package game_main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;

public class Game {
        public static JFrame window = new JFrame("WAR GAME JAVA");; 
	public Game() throws IOException{
                
		new FileWriter(new File("src/FilesTXT/stock_Unite.txt")).close();
		window.add(new GamePanel());
		
		window.setResizable(false);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
	public static void main(String[] args) throws IOException {
		Game G=new Game();
           //     Game.getWindGame().setTitle("Test");
                
	}
        
        
       public static JFrame getWindGame(){
           return window;
       }
	
}
