/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uploadcontroller;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class InforDAO {
    public static List<Infor> ls = new ArrayList<>();
    public Infor findById(String id){
        for(Infor info:ls){
           if(info.getId().equals(id)){
               return info;
           }
        }
        return null;
    }
    
    public int update(Infor info){
        for (int i = 0; i < ls.size(); i++) {
            if(ls.get(i).getId().equals(info.getId())){
                ls.set(i, info);
                return i;
            }
        }
        return -1;
    }
    
    public int save(Infor info){
        ls.add(info);
        return 1;
    }
    
    public int delete(String id){
        Infor info = findById(id);
        if(info !=null){
            ls.remove(info);
            return 1;
        }
        return 0;
        
    }
    
    public void dummydata(){
        ls.add(new Infor("PD1","A1","anh1.jpg"));
        ls.add(new Infor("PD2","A2","anh2.jpg"));
        ls.add(new Infor("PD3","A3","anh3.jpg"));
        ls.add(new Infor("PD4","A4","anh4.jpg"));
        
    }
    
    public List<Infor> GetAll(){
//        dummydata();
        return ls;
    } 
    
}
