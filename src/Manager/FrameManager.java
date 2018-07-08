/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Interfaces.FrameInterface;
import Interfaces.FrameManagerInterface;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author leonardoalvesdemelo
 */
public class FrameManager implements FrameManagerInterface {
    
    FrameInterface currentPanel;
    JFrame frame;
    
    public FrameManager(FrameInterface initialPanel, String frameName) {
        this.currentPanel = initialPanel;

        this.frame = new JFrame(frameName);
        
        this.applyCurrentPanel();
        
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
            
        //
    }
    
    private void applyCurrentPanel() {
        if(this.currentPanel instanceof JPanel) {
            
            JPanel panel = (JPanel) this.currentPanel;
            
            this.frame.add(panel);
        }
        
        this.currentPanel.setManager(this);
        
        this.frame.repaint();
        this.frame.setSize(500,500);
        
        this.frame.setVisible(true);
    }
    
    @Override
    public void changeCurrentPanel(FrameInterface newPanel) {
        
        if(this.currentPanel instanceof JPanel) {
            
            JPanel panel = (JPanel) this.currentPanel;
            
            this.frame.remove(panel);
        }
        
        this.currentPanel = newPanel;

        
        
        this.applyCurrentPanel();
    }
        
        
}
