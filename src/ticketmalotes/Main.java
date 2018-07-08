/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketmalotes;

import Manager.FrameManager;
import View.InitialViewPanel;

/**
 *
 * @author leonardoalvesdemelo
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        FrameManager frameManager = new FrameManager(new InitialViewPanel(), "Gerenciador de Malotes");

    }
}
