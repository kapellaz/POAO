
package geremarina;


public class Recreio extends Barco{
    private int ocupacaoMaxima;

    public Recreio(String estrutura, String dimensao, int matricula, int ocupacaoMaxima) {
        super(estrutura, dimensao, matricula);
        this.ocupacaoMaxima = ocupacaoMaxima;
    }

    public int getOcupacaoMaxima() {
        return ocupacaoMaxima;
    }

    public void setOcupacaoMaxima(int ocupacaoMaxima) {
        this.ocupacaoMaxima = ocupacaoMaxima;
    }

    @Override
    public String toString() {
        return "Barco -> Recreio {" + "ocupacaoMaxima=" + ocupacaoMaxima + ", " + super.toString()+'}';
    }
}
