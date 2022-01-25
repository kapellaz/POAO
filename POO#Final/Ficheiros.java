package gestordevendas;

import java.io.*;
import java.util.ArrayList;

/**
 * class responsible to open and read both text and object files
 * @author conta
 */
class Ficheiros {

    
    public Ficheiros(){
        
    }
    
    /**
     * function that opens a file with the shop´s clients and their information
     * @param clientes the arraylist where the clients will be saved
     * @param fich the name of the file to open
     */
    public void leFichClientes(ArrayList<Cliente> clientes, String fich) {
        int users = 0;
        File f = new File(fich);
        if(f.exists() && f.isFile()) {
            try{
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while((line= br.readLine()) != null) {
                    String[] info = line.split("\\+");
                    Cliente cliente;
                    if(Integer.parseInt(info[0])>users){
                        users = Integer.parseInt(info[0]);
                    }
                    String[] data = info[5].split(",");
                    Data d = new Data(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
                    cliente = new Cliente(Integer.parseInt(info[0]), info[1], Integer.parseInt(info[2]), info[3], info[4], d);
                    clientes.add(cliente);
                }
                br.close();
            } catch(FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro de texto.");
            } catch(IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");}
        } else{
            System.out.println("Ficheiro não existe.");
        }
    }

    
    /**
     * function that opens a file with the with the purchases that clients made
     * @param clientes  arraylist with the clients 
     * @param produtos  arraylist with the products available at the store
     * @param file the name of the file to open
     */
    public void leFichCompras(ArrayList<Cliente> clientes, ArrayList<StockProduto> produtos, String file){
        File f = new File(file);
        if(f.exists() && f.isFile()) {
            try{
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while((line= br.readLine()) != null) {
                    ArrayList<PreparaProduto> prepArr = new ArrayList<>();
                    String[] info = line.split("\\+");
                    int utilizador = Integer.parseInt(info[0]);
                                    
                    int size = info.length;

                    String[] d = info[size-1].split(",");
                    Data data = new Data(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2]));
                    
                    for (int i = 1; i < size-3; i++) {
                        PreparaProduto prep = new PreparaProduto();
                        for (StockProduto prod : produtos) {
                            if(prod.getProd().getIdentificador() == Integer.parseInt(info[i])){
                                prep.setProd(prod.getProd());
                            }
                        }

                        prep.setQuantidade(Integer.parseInt(info[i+1]));
                        i+=1;
                        prepArr.add(prep);
                    }
                    
                    Compra compra = new Compra();
                    compra.setProd(prepArr);
                    compra.setPrecoFinal(Float.parseFloat(info[size-2]));
                    compra.setDate(data);
                    for(Cliente c: clientes){
                        if(c.getNumCliente()== utilizador){
                            ArrayList<Compra> compras = c.getCompras();
                            compras.add(compra);
                            c.setCompras(compras);
                        }
                    }
                }
                br.close();
            } catch(FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro de texto.");
            } catch(IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");}
        } else{
            System.out.println("Ficheiro não existe.");
        }
    }
    
    
    
    /**
     * function that opens a file with the products for sale
     * @param produtos the arraylist where the products will be saved
     * @param file the name of the file to open    
     */
    public void leFichProd(ArrayList<StockProduto> produtos, String file) {
        File f = new File(file);
        if(f.exists() && f.isFile()) {
            try{
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while((line= br.readLine()) != null) {
                    String[] info = line.split("\\+");
                    StockProduto sp = new StockProduto();
                    int codigo = Integer.parseInt(info[1]);
                    switch (codigo%10) {
                        case 1:
                            {
                                Alimentar a = new Alimentar(Integer.parseInt(info[4]), Float.parseFloat(info[5]), codigo, info[0], Float.parseFloat(info[3]), null);
                                sp.setProd(a);
                                sp.setQuantidadeEmStock(Integer.parseInt(info[2]));
                                produtos.add(sp);
                                break;
                            }
                        case 2:
                            {
                                Limpeza a = new Limpeza(Integer.parseInt(info[4]), codigo, info[0], Float.parseFloat(info[3]), null);
                                sp.setProd(a);
                                sp.setQuantidadeEmStock(Integer.parseInt(info[2]));
                                produtos.add(sp);
                                break;
                            }
                        case 3:
                            {
                                Mobilia a = new Mobilia(Integer.parseInt(info[4]), Integer.parseInt(info[5]), Integer.parseInt(info[6]), Integer.parseInt(info[7]), codigo, info[0], Float.parseFloat(info[3]), null);
                                sp.setProd(a);
                                sp.setQuantidadeEmStock(Integer.parseInt(info[2]));
                                produtos.add(sp);
                                break;
                            }
                    } 
                }
                br.close();
            } catch(FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro de texto.");
            } catch(IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");}
        } else{
            System.out.println("Ficheiro não existe.");
        }
    }

    
    /**
     * function that opens a file with store promotions and associate them with each produc
     * @param produtos the arraylist of products to associate the promotions
     * @param file the name of the file to open
     */
    public void leFichPromo(ArrayList<StockProduto> produtos, String file) {
        File f = new File(file);
        if(f.exists() && f.isFile()) {
            try{
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while((line= br.readLine()) != null) {
                    String[]info = line.split("\\+");
                    int id = Integer.parseInt(info[0]);
                    int idPromo = id/1000;
                    ArrayList<Data>dataini = new ArrayList<>();
                    ArrayList<Data>datafim = new ArrayList<>();
                    
                    Promocao promo;
                    if(idPromo == 1){
                        promo = new PromoPagaMenos();
                    }else{
                        promo = new Promo4para3();
                    }
                    
                    for(StockProduto sp:produtos){
                        if(sp.getProd().getIdentificador()==id){
                            for (int i = 1; i < info.length; i++) {
                                String[] d = info[i].split(",");
                                Data data = new Data(Integer.parseInt(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2]));
                                if(i%2 !=0){
                                    dataini.add(data);
                                }else{
                                    datafim.add(data);
                                }
                            }
                            promo.setDatainicio(dataini);
                            promo.setDatafim(datafim);
                            sp.getProd().setPromo(promo);
                        }
                    }
                }
                br.close();
            } catch(FileNotFoundException ex) {
                System.out.println("Erro a abrir ficheiro de texto.");
            } catch(IOException ex) {
                System.out.println("Erro a ler ficheiro de texto.");}
        } else{
            System.out.println("Ficheiro não existe.");
        }
    }
    
    
    
    /**
     * function that saves the list of the products and the clients of the store in an object file 
     * @param produtos the arraylist with the products to save
     * @param clientes the arraylist with the clients to save
     */
    public void gravaInfo(ArrayList<StockProduto> produtos, ArrayList<Cliente> clientes) {
        File escreverProdutos = new File("stock.obj");
        try {
            FileOutputStream fos = new FileOutputStream(escreverProdutos);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(produtos);
            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.out.println("Erro a escrever para o ficheiro.");
        }
        File escreverClientes = new File("clientes.obj");
        try {
            FileOutputStream fos = new FileOutputStream(escreverClientes);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(clientes);
            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.out.println("Erro a escrever para o ficheiro.");
        }
    }

    
    
    
    /**
     * function that reads an object file with the list of the products available at the store
     * @return the arraylist with the products
     */
    public ArrayList<StockProduto> lerStock(){
        ArrayList<StockProduto> produtos= new ArrayList<>();
        File ficheiroProdutos = new File("stock.obj");
        try {
            FileInputStream fis = new FileInputStream(ficheiroProdutos);
            ObjectInputStream ois = new ObjectInputStream(fis);
            produtos = (ArrayList<StockProduto>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a abrir ficheiro.");
        } catch (IOException ex) {
            System.out.println("Erro a ler ficheiro.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro a converter objeto.");
        }
        return produtos;
    }
    
    
    /**
     * function that reads an object file with the list of the clients
     * @return the arraylist with the clients
     */
    public ArrayList<Cliente> lerClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        File ficheiroClientes = new File("clientes.obj");
        try {
            FileInputStream fis = new FileInputStream(ficheiroClientes);
            ObjectInputStream ois = new ObjectInputStream(fis);
            clientes = (ArrayList<Cliente>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro a abrir ficheiro.");
        } catch (IOException ex) {
            System.out.println("Erro a ler ficheiro.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro a converter objeto.");
        }
       return clientes; 
    }
    
}
