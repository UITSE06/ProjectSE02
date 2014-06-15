/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author John
 */
public class ClsLimitDocument_BLL extends PlainDocument {

    private int limit;

    public ClsLimitDocument_BLL(int lm) {
        limit = lm;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {
        if (str == null) {
            return;
        }

        if ((getLength() + str.length()) <= limit) {//kiem tra xem so ki tu co thoa man hay khong?
            super.insertString(offs, str, a);
        }

    }
}
