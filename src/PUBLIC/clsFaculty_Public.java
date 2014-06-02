/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PUBLIC;

/**
 *
 * @author John
 */
public class clsFaculty_Public {
    private String idFaculty;
    private String nameOfFaculty;
    private String shortcutName;
    @Override
    public String toString() {
        return nameOfFaculty;
    }

    /**
     * @return the idFaculty
     */
    public clsFaculty_Public() { }
    
    public clsFaculty_Public(String maKhoa, String tenKhoa) {
        this.idFaculty = maKhoa;
        this.nameOfFaculty = tenKhoa;
    }

    
    public String getIdFaculty() {
        return idFaculty;
    }

    /**
     * @param idFaculty the idFaculty to set
     */
    public void setIdFaculty(String idFaculty) {
        this.idFaculty = idFaculty;
    }

    /**
     * @return the nameOfFaculty
     */
    public String getNameOfFaculty() {
        return nameOfFaculty;
    }

    /**
     * @param nameOfFaculty the nameOfFaculty to set
     */
    public void setNameOfFaculty(String nameOfFaculty) {
        this.nameOfFaculty = nameOfFaculty;
    }

    /**
     * @return the shortcutName
     */
    public String getShortcutName() {
        return shortcutName;
    }

    /**
     * @param shortcutNam the shortcutName to set
     */
    public void setShortcutName(String shortcutNam) {
        this.shortcutName = shortcutNam;
    }
}
