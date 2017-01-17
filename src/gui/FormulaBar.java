package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A formula bar that is used to display the formula of a cell.
 * 
 * @author Dmitriy Onishchenko
 * @version 1 March 2016
 */
public class FormulaBar extends JPanel { 
    
    /**
     * Generated ID.
     */
    private static final long serialVersionUID = 3668140136296818344L;
    
    /**
     * Default message at startup for formula bar.
     */
    private static final String MESSAGE = "Click on a cell to change";
    
    /**
     * Default message at startup for Cell label.
     */
    private static final String MESSAGE_CELL = "Formula at A0 is: ";
    
    /**
     * Text box for for displaying formula, not editable in this version
     * Could be changed.
     */
    private JTextField formulaBar;
    
    /**
     * A label that sais which cell you are on.
     */
    private JLabel cellAt;  
    
    /**
     * The text in the formula bar.
     */
    private String inputFormula;
    
    /**
     * Constructor.
     */
    public FormulaBar() {
        
        formulaBar = new JTextField(MESSAGE);
        cellAt = new JLabel(MESSAGE_CELL);        
        inputFormula = MESSAGE;
        setUpComponets();        
        add(cellAt);    
        add(formulaBar);       
        
    }
    
    /**
     * Method to set up formula view.
     */
    private void setUpComponets() {
        
        cellAt.setPreferredSize(new Dimension (180, 50));        
        cellAt.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        formulaBar.setPreferredSize(new Dimension (380, 50));        
        formulaBar.setEditable(false);
        formulaBar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        formulaBar.setBackground(Color.WHITE);
        formulaBar.setBorder(BorderFactory.createCompoundBorder(formulaBar.getBorder(), 
                                BorderFactory.createEmptyBorder(0, 10, 0, 10)));
                
        
        formulaBar.addMouseListener(new MouseAdapter() {            
            @Override
            public void mouseReleased(MouseEvent e) {
                inputFormula = formulaBar.getText();                
            }
        });
        
        formulaBar.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyReleased(KeyEvent e) {
                
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {                    
                    firePropertyChange("new", inputFormula, formulaBar.getText());                   
                }
            }
        });
        
    }
    
    /**
     * Method to update formula bar text.
     * @param formula the formula
     * @param cell the cell that your located at.
     */
    public void updateFormulaBar(String formula, String cell) { 
        formulaBar.setEditable(true);
        formulaBar.setText(formula);         
        cellAt.setText("Formula at " + cell + " is: ");        
    }
    
    /**
     * Method to just set the formula bar text.
     * @param formula the formula.
     */
    public void setFormulaText(String formula) {        
        formulaBar.setText(formula);         
    }
    
    /**
     * Method to just set the Cell at text.
     * @param cell the cell location.
     */
    public void setCellAtText(String cell) {
        cellAt.setText("Formula at " + cell + " is: ");
    }
    
    /**
     * Resets to default messages.
     */
    public void resetMessages() {
        formulaBar.setEditable(false);
        formulaBar.setText(MESSAGE);
        cellAt.setText(MESSAGE_CELL);        
    }
}
