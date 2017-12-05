package common;

import javax.swing.JOptionPane;

/**
 *
 * @author Naveen
 */
public class MessageBox {
    
    public static void showMessage(String message, String title){
        JOptionPane.showMessageDialog(null, message, "Message: " + title, JOptionPane.INFORMATION_MESSAGE);
    }
    
}
