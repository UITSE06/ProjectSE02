package BLL;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class ClsDigitsDocument_BLL extends PlainDocument {

    private static int limit;

    public ClsDigitsDocument_BLL(int lm) {
        ClsDigitsDocument_BLL.limit = lm;
    }
    /**
     *
     * @param offs
     * @param str
     * @param a
     * @throws BadLocationException
     */
    @Override
    public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {
        if (str == null) {
            return;
        }
        char[] addedFigures = str.toCharArray();
        char c;
        for (int i = addedFigures.length; i > 0; i--) {
            c = addedFigures[i - 1];
            if (Character.isDigit(c)) {//kiem tra xem co phai so hay khong?
                if ((getLength() + str.length()) <= limit) {//kiem tra xem so ki tu co thoa man hay khong?
                    super.insertString(offs, new Character(c).toString(), a);
                }
            }
        }
    }
}
