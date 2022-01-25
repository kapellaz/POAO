
package geremarina;


public class Fragata extends Marinha{
    
    private String tipoDeArtilharia;
    
    public Fragata(String estrutura, String dimensao, int matricula, String artilharia) {
        super(estrutura, dimensao, matricula);
        this.tipoDeArtilharia = artilharia;
    }

    public String getTipoDeArtilharia() {
        return tipoDeArtilharia;
    }

    public void setTipoDeArtilharia(String tipoDeArtilharia) {
        this.tipoDeArtilharia = tipoDeArtilharia;
    }

    @Override
    public String toString() {
        return "Barco -> Marinha -> Fragata {" + "artilharia=" + tipoDeArtilharia + ", " + super.toString()+"}";
    }
    
}
