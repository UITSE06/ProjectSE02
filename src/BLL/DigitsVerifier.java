/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author John
 */
public class DigitsVerifier extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        JTextField tf = (JTextField) input;
        String str = tf.getText();
        if (str == null) {
            return false;
        }
        char[] addedFigures = str.toCharArray();
        char c;
        int count = 0;
        for (int i = addedFigures.length; i > 0; i--) {
            c = addedFigures[i - 1];
            if (Character.isDigit(c)) {
                count++;
            }
        }
        return count == addedFigures.length;
    }
}
