
package gestordevendas;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * the class that characterizes the a client at the shop
 * @author conta
 */
class Cliente implements Serializable{
    private ArrayList<Compra> compras;
    private int numCliente;
    private String nome;
    private int telemovel;
    private String morada;
    private String mail;
    private Data data;
    
    
    public Cliente(){
        compras = new ArrayList<>();
    }

    public Cliente(int numCliente, String nome, int telemovel, String morada, String mail, Data data) {
        this.numCliente = numCliente;
        this.nome = nome;
        this.telemovel = telemovel;
        this.morada = morada;
        this.mail = mail;
        this.data = data;
        compras = new ArrayList<>();
    }
    
    public Cliente(String nome, int telemovel, String morada, String mail, Data data){
        this.nome = nome;
        this.telemovel = telemovel;
        this.morada = morada;
        this.mail = mail;
        this.data = data;
    }


    public Cliente(ArrayList<Compra> compras, int numCliente, String nome, int telemovel, String morada, String mail, Data data) {
        this.compras = compras;
        this.numCliente = numCliente;
        this.nome = nome;
        this.telemovel = telemovel;
        this.morada = morada;
        this.mail = mail;
        this.data = data;
    }

    public int getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }

    public ArrayList<Compra> getCompras() {
        return compras;
    }

    public void setCompras(ArrayList<Compra> compras) {
        this.compras = compras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }    

    @Override
    public String toString() {
        return "Cliente{" + "compras=" + compras + ", numCliente=" + numCliente + ", nome=" + nome + ", telemovel=" + telemovel + ", morada=" + morada + ", mail=" + mail + ", data=" + data + '}';
    }
    
}