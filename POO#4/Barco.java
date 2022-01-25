
package geremarina;


public class Barco {
     private String estrutura;
     private String dimensao;
     private int matricula;

    public Barco(String estrutura, String dimensao, int matricula) {
        this.estrutura = estrutura;
        this.dimensao = dimensao;
        this.matricula = matricula;
    }

    public String getEstrutura() {
        return estrutura;
    }

    public void setEstrutura(String estrutura) {
        this.estrutura = estrutura;
    }

    public String getDimensao() {
        return dimensao;
    }

    public void setDimensao(String dimensao) {
        this.dimensao = dimensao;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }  

    @Override
    public String toString() {
        return "estrutura=" + estrutura + ", dimensao=" + dimensao + ", matricula=" + matricula;
    }
}

