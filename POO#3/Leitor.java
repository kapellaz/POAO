
package gerebilioteca;

/**
 * class that characterizes the client
 * @author conta
 */
public class Leitor {
    private int userNumber;
    private String username;

    
    public Leitor() {
    }
    
    
    public Leitor(int userNumber, String username) {
        this.userNumber = userNumber;
        this.username = username;
    }


    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Leitor{" + "userNumber=" + userNumber + ", username=" + username + "}\n";
    }
}