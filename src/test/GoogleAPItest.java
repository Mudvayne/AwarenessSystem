/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import control.GoogleAPIControl;
import model.Users;

/**
 *
 * @author michi
 */
public class GoogleAPItest {
    
    public static void main(String[] args) {
    
        Users model = new Users();
        GoogleAPIControl c = new GoogleAPIControl(model);
        System.out.println(model.toString());
        
        
    }
}
