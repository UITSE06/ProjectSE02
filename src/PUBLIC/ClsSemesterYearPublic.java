/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PUBLIC;

import java.sql.Date;

/**
 *
 * @author John
 */
public class ClsSemesterYearPublic {

    private String idSemesterYear;
    private int firstYear;
    private int secondYear;
    private int semester;
    private Date reduceDeadline;
    private Date getFeeDeadline;
    private Date RegisterDeadline;
    private Date BeginRegister;
    private int reducePercent;

    /**
     * @return the idSemesterYear
     */
    public String getIdSemesterYear() {
        return idSemesterYear;
    }

    /**
     * @param idSemesterYear the idSemesterYear to set
     */
    public void setIdSemesterYear(String idSemesterYear) {
        this.idSemesterYear = idSemesterYear;
    }

    /**
     * @return the firstYear
     */
    public int getFirstYear() {
        return firstYear;
    }

    /**
     * @param firstYear the firstYear to set
     */
    public void setFirstYear(int firstYear) {
        this.firstYear = firstYear;
    }

    /**
     * @return the secondYear
     */
    public int getSecondYear() {
        return secondYear;
    }

    /**
     * @param secondYear the secondYear to set
     */
    public void setSecondYear(int secondYear) {
        this.secondYear = secondYear;
    }

    /**
     * @return the semester
     */
    public int getSemester() {
        return semester;
    }

    /**
     * @param semester the semester to set
     */
    public void setSemester(int semester) {
        this.semester = semester;
    }

    /**
     * @return the reduceDeadline
     */
    public Date getReduceDeadline() {
        return reduceDeadline;
    }

    /**
     * @param reduceDeadline the reduceDeadline to set
     */
    public void setReduceDeadline(Date reduceDeadline) {
        this.reduceDeadline = reduceDeadline;
    }

    /**
     * @return the getFeeDeadline
     */
    public Date getGetFeeDeadline() {
        return getFeeDeadline;
    }

    /**
     * @param getFeeDeadline the getFeeDeadline to set
     */
    public void setGetFeeDeadline(Date getFeeDeadline) {
        this.getFeeDeadline = getFeeDeadline;
    }

    /**
     * @return the RegisterDeadline
     */
    public Date getRegisterDeadline() {
        return RegisterDeadline;
    }

    /**
     * @param RegisterDeadline the RegisterDeadline to set
     */
    public void setRegisterDeadline(Date RegisterDeadline) {
        this.RegisterDeadline = RegisterDeadline;
    }

    /**
     * @return the BeginRegister
     */
    public Date getBeginRegister() {
        return BeginRegister;
    }

    /**
     * @param BeginRegister the BeginRegister to set
     */
    public void setBeginRegister(Date BeginRegister) {
        this.BeginRegister = BeginRegister;
    }

    /**
     * @return the reducePercent
     */
    public int getReducePercent() {
        return reducePercent;
    }

    /**
     * @param reducePercent the reducePercent to set
     */
    public void setReducePercent(int reducePercent) {
        this.reducePercent = reducePercent;
    }
}
