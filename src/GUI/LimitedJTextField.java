/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

/**
 *
 * @author John
 */
public class LimitedJTextField extends JTextField {

    private static int limit;

    public LimitedJTextField(int limit) {
        setLimit(limit);
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    protected Document createDefaultModel() {
        return new LimitedLengthDocument();
    }

    static class LimitedLengthDocument extends PlainDocument {

        public void insertString(int offs, String str, AttributeSet a)
                throws BadLocationException {
            if (str == null) {
                return;
            }

//            if ((getLength() + str.length()) <= limit) {
//                super.insertString(offs, str, a);
//            }

            char[] addedFigures = str.toCharArray();
            char c;
            for (int i = addedFigures.length; i > 0; i--) {
                c = addedFigures[i - 1];
                if (Character.isDigit(c)) {//kiem tra xem co phai so hay khong?
                    if ((getLength() + str.length()) <= limit) {//kiem tra xem so ki tu co thoa man hay khong?
                        //super.insertString(offs, str, a);
                        super.insertString(offs, new Character(c).toString(), a);
                    }                    
                }
            }
        }
    }
}
