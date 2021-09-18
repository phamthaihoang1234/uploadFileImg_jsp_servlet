/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uploadcontroller;

/**
 *
 * @author Admin
 */
public class Infor {
    private String id;
    private String name;
    private String photo;

    public Infor(String id, String name, String photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
    }

    public Infor() {
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    
    
}
