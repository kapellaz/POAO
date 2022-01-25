
package geremarina;


public class Pesca extends Barco {
    private String tipoDePesca;
    
    public Pesca(String estrutura, String dimensao, int matricula, String tipoDePesca) {
        super(estrutura, dimensao, matricula);
        this.tipoDePesca = tipoDePesca;
    }

    public String getTipoDePesca() {
        return tipoDePesca;
    }

    public void setTipoDePesca(String tipoDePesca) {
        this.tipoDePesca = tipoDePesca;
    }

    @Override
    public String toString() {
        return "Barco -> Pesca {" + "tipoDePesca=" + tipoDePesca + ", " + super.toString()+'}';
    }
    
}
