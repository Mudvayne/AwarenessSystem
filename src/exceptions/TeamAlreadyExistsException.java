/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Florian
 */
public class TeamAlreadyExistsException extends Exception{
    
    public TeamAlreadyExistsException(String s){
        super(s);
    }
}
