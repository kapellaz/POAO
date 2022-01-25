
package gerebilioteca;


/**
 * class that characterizes a request
 * @author conta
 */
public class Requisicao {
    private Leitor leitor;
    private Livro livro;
    private Data dataInicio;
    private Data dataFim;

    
    public Requisicao(){
        
    }
    
    /**
     * Inicializa as variaveis
     * @param l
     * @param dataReq
     * @param dataDev 
     */
    public Requisicao(Leitor l, Data dataReq, Data dataDev){
        this.leitor = l;
        this.dataInicio = dataReq;
        this.dataFim = dataDev;
    }
    
  
    
    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Data getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Data dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Data getDataFim() {
        return dataFim;
    }

    public void setDataFim(Data dataFim) {
        this.dataFim = dataFim;
    }  
    
    @Override
    public String toString(){
        return "\nRequisicao do livro " + livro.getTitle() +" de " + livro.getBookAuthor() + " em nome de " + leitor.getUsername() + " concluida";
    }
}

