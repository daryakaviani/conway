import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class conway extends JApplet implements MouseListener, KeyListener {
    int squareSize = 3;
    int dimensions= 200;
    boolean board[][] = new boolean[dimensions][dimensions];
    public void init() {
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(Math.random() > 0.1){
                    board[i][j] = false;
                }else{
                    board[i][j] = true;
                }
            }
        }
        
        setFocusable(true);     
        addMouseListener(this);
        addKeyListener(this);
    }
    
    public void step() {
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == false && livingNeighbors(i,j) == 3){
                    board[i][j] = true;
                }else if(board[i][j] == true && (livingNeighbors(i,j) == 2 || livingNeighbors(i,j) == 3)){
                    board[i][j] = true;
                }else{
                    board[i][j] = false;
                }
            }
        }
    }

    public void paint(Graphics g) {
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == true){
                    g.setColor(Color.green);
                }else{
                    g.setColor(Color.black);
                }
                g.fillRect(j*squareSize,i*squareSize,squareSize, squareSize);
            }
        }
    }
    
    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == ' '){
            step();
            repaint();
        }
    }
    public void keyReleased(KeyEvent e) {}
    
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        
        board[y/squareSize][x/squareSize] = !board[y/squareSize][x/squareSize];
        
        repaint();
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
   
    
    public int livingNeighbors(int i, int j){
        int living = 0;
        //top right
        if(i == 0 && j == dimensions-1){
            if(board[i][j-1] == true)living++;
            if(board[i+1][j-1] == true)living++;
            if(board[i+1][j] == true)living++;
            return living;
        }
        //top left
        if(i == 0 && j ==0){
            if(board[i+1][j] == true)living++;
            if(board[i][j+1] == true)living++;
            if(board[i+1][j+1] == true)living++;
            return living;
        }
        //bottom right
        if(i == dimensions-1 && j == dimensions-1){
            if(board[i-1][j-1] == true)living++;
            if(board[i][j-1] == true)living++;
            if(board[i-1][j] == true)living++;
            return living;
        }
        //bottom left
        if(i == dimensions-1 && j == 0){
            if(board[i-1][j] == true)living++;
            if(board[i-1][j+1] == true)living++;
            if(board[i][j+1] == true)living++;
            return living;
        }
        //top row
        if(i == 0){
            if(board[i][j-1] == true)living++;
            if(board[i+1][j-1] == true)living++;
            if(board[i+1][j] == true)living++;
            if(board[i][j+1] == true)living++;
            if(board[i+1][j+1] == true)living++;
            return living;
        }
        //bottom row
        else if(i == dimensions-1){
            if(board[i-1][j-1] == true)living++;
            if(board[i][j-1] == true)living++;
            if(board[i-1][j] == true)living++;
            if(board[i-1][j+1] == true)living++;
            if(board[i][j+1] == true)living++;
            return living;
        }
        //left
        else if(j == 0){
            if(board[i-1][j] == true)living++;
            if(board[i+1][j] == true)living++;
            if(board[i-1][j+1] == true)living++;
            if(board[i][j+1] == true)living++;
            if(board[i+1][j+1] == true)living++;
            return living;
        }
        //right
        else if(j == dimensions-1){
            if(board[i-1][j-1] == true)living++;
            if(board[i][j-1] == true)living++;
            if(board[i+1][j-1] == true)living++;
            if(board[i-1][j] == true)living++;
            if(board[i+1][j] == true)living++;
            return living;
        }

        if(board[i-1][j-1] == true)living++;
        if(board[i][j-1] == true)living++;
        if(board[i+1][j-1] == true)living++;
        if(board[i-1][j] == true)living++;
        if(board[i+1][j] == true)living++;
        if(board[i-1][j+1] == true)living++;
        if(board[i][j+1] == true)living++;
        if(board[i+1][j+1] == true)living++;
        return living;
    }
}