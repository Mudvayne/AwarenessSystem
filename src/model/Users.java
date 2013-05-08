package model;

import java.util.TreeSet;

/**
 *
 * @author michi
 */
public class Users {
    private TreeSet<User> list;
    
    public Users() {
        list = new TreeSet<>();
    }

    public TreeSet<User> getList() {
        return list;
    }

    public void setList(TreeSet<User> list) {
        this.list = list;
    }
    
    @Override
    public String toString() {
        String output = "";
        for (User user : list) {
            output+= user + "\n";
        }
        return output;
    }
}
