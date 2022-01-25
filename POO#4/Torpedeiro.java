
package geremarina;

public class Torpedeiro  extends Marinha{
    private int torpedos;
    
    public Torpedeiro(String estrutura, String dimensao, int matricula, int torpedos) {
        super(estrutura, dimensao, matricula);
        this.torpedos = torpedos;
    }

    public int getTorpedos() {
        return torpedos;
    }

    public void setTorpedos(int torpedos) {
        this.torpedos = torpedos;
    }
    
    @Override
    public String toString() {
        return "Barco -> Marinha -> Torpedeiro {" + "torpedos=" + torpedos + ", " + super.toString()+"}";
    }
    
}
