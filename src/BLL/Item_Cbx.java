/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

/**
 *
 * @author Nguyen Thanh Thai
 */
public class Item_Cbx {
    private String id;
    private String description;
    private String option;

    public Item_Cbx(String description){
        this.description = description;
    }

    public Item_Cbx(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public Item_Cbx(String id, String description, String option)
    {
        this.id = id;
        this.description = description;
        this.option = option;
    }
    
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getOption() {
        return option;
    }
    
    @Override
    public String toString() {
        return description;
    }
}
