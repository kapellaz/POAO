
package gestordevendas;


import java.io.Serializable;
import java.util.ArrayList;


/**
 * class that handles promotions
 * @author conta
 */
abstract class Promocao implements Serializable{
    ArrayList<Data> datainicio;
    ArrayList<Data> datafim;

    public Promocao(){
        datainicio = new ArrayList<>();
        datafim = new ArrayList<>();
    }

    public ArrayList<Data> getDatainicio() {
        return datainicio;
    }

    public void setDatainicio(ArrayList<Data> datainicio) {
        this.datainicio = datainicio;
    }

    public ArrayList<Data> getDatafim() {
        return datafim;
    }

    public void setDatafim(ArrayList<Data> datafim) {
        this.datafim = datafim;
    }



    /**
     * function that calculates the promotion to apply to a certain product
     * @param quant
     * @param preco
     * @return 
     */
    public abstract float calculaPromo(int quant, float preco);


    /**
     * function that returns if the promotion is available today
     * @param diaCompra today´s day
     * @return true or false
     */
    public boolean procuraPromo(Data diaCompra){
        int tamanho=datafim.size();
            for(int i=0;i<tamanho;i++){
                Data ini=datainicio.get(i);
                Data fim=datafim.get(i);

                if(ini.comparaDatas(ini, diaCompra)){
                    if(ini.comparaDatas(diaCompra, fim)){
                        return true;
                    }
                }
            }
         return false;
    }
}
