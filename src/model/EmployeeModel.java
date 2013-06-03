package model;

import java.util.Objects;

/**
 * Model fuer einen Mitarbeiter, mit Name und eindeutiger E-Mail
 * @author Michael U.
 */
public class EmployeeModel implements Comparable<EmployeeModel>{
    private String name;
    private String mail;

    public EmployeeModel(String name, String mail) {
        this.name = name;
        this.mail = mail;
    }

   public EmployeeModel(String mail) {
        this.mail = mail;
    }
    
    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    @Override
    public String toString() {
        return name + " (" + mail + ")";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.mail);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EmployeeModel other = (EmployeeModel) obj;
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(EmployeeModel o) {
        return this.name.compareTo(o.name);
    }
}
