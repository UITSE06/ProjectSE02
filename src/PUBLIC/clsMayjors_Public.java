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
public class clsMayjors_Public extends clsStudent_Public{
    private String idMayjors;
    private String nameOfMayjors;
    private String idFaculty;
    private int noSemester;
    private Integer noSe;

    /**
     * @return the idMayjors
     */
    public clsMayjors_Public(){
    };
    
    public clsMayjors_Public(String strIDMayjors, String strNameOfMayjors, String strIDFaculty){
        this.idMayjors = strIDMayjors;
        this.nameOfMayjors = strNameOfMayjors;
        this.idFaculty = strIDFaculty;
    }
    public String getIdMayjors() {
        return idMayjors;
    }

    /**
     * @param idMayjors the idMayjors to set
     */
    public void setIdMayjors(String idMayjors) {
        this.idMayjors = idMayjors;
    }

    /**
     * @return the nameOfMayjors
     */
    public String getTenNganh() {
        return nameOfMayjors;
    }

    /**
     * @param tenNganh the nameOfMayjors to set
     */
    public void setNameMayjors(String tenNganh) {
        this.nameOfMayjors = tenNganh;
    }

    /**
     * @return the idFaculty
     */
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
     * @return the noSemester
     */
    public int getNoSemester() {
        return noSemester;
    }

    /**
     * @param noSemester the noSemester to set
     */
    public void setNoSemester(int noSemester) {
        this.noSemester = noSemester;
    }

    /**
     * @return the noSe
     */
    public Integer getNoSe() {
        return noSe;
    }

    /**
     * @param noSe the noSe to set
     */
    public void setNoSe(Integer noSe) {
        this.noSe = noSe;
    }
}
