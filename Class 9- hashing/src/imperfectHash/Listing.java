package imperfectHash;

import javax.swing.JOptionPane;

public class Listing {

    private String name;  // key field
    private String address;
    private String number;

    public Listing(String n, String a, String num) {
        name = n;
        address = a;
        number = num;
    }
    
    public Listing() {
        name = "";
        address = "";
        number = "";
    }

    public String toString() {
        return ("Name is " + name
                + "\nAddress is " + address
                + "\nNumber is " + number + "\n");
    }

    public Listing deepCopy() {
        Listing clone = new Listing(name, address, number);
        return clone;
    }

    public int compareTo(String targetKey) {
        return (name.compareTo(targetKey));
    }

    public void setAddress(String a) // coded to demonstrate encapsulation
    {
        address = a;
    }

    public String getKey() {
        return name;
    }

    public void inputStudent() {
        name = JOptionPane.showInputDialog("Enter a name");
        address = JOptionPane.showInputDialog("Enter an address");
        number = JOptionPane.showInputDialog("Enter a number");
    }
}// end of class Listing

