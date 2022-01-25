
package gestordevendas;



import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * main class, where the client will interact directly
 * @author conta
 */
public class GestorDeVendas {
    private ArrayList<Cliente> clientes;
    private ArrayList<StockProduto> produtos;
    private Data data;
    private Ficheiros f;

    
    
    public static void main(String[] args) {
        GestorDeVendas gestor = new GestorDeVendas();
        Cliente cliente = gestor.login();
        
        if(cliente.getNumCliente()==0){
            gestor.menuAdmin();
        }else{
            gestor.menu(cliente);
        }
    }
    
    public GestorDeVendas(){
        f = new Ficheiros();
        data = new Data(20, 1, 2022);
        File file = new File("stock.obj");
        clientes = new ArrayList<>();
        produtos = new ArrayList<>();
        if(file.exists()){
            System.out.println("----");
            produtos = f.lerStock();
            clientes = f.lerClientes();
        }
        else{
            f.leFichClientes(clientes, "clientes.txt");
            f.leFichProd(produtos, "produtos.txt");
            f.leFichCompras(clientes, produtos, "compras.txt");
            f.leFichPromo(produtos, "promocoes.txt");
        }
    }
    
    
    /**
     * function that shows a different menu for an admin
     */
    private void menuAdmin() {
        int escolha;
        
        do{
            Scanner stdin = new Scanner(System.in);
            System.out.println("1 - Adicionar novo produto");
            System.out.println("2 - Aumentar stock");
            System.out.println("Outro - Cancelar/sair");
            
            if (stdin.hasNextInt()) {
                    escolha = stdin.nextInt();
                }else{
                    escolha = 0;
                }
                if (escolha > 2 || escolha < 0) {
                    escolha = 0;
                }
            
                switch(escolha){
                    case 1 -> addProduto();
                    case 2 -> aumentaStock();
                       
                    case 0 -> {
                        f.gravaInfo(produtos, clientes);
                        System.exit(0);
                    }
                    
                    
                }
        }while(escolha !=0);
    
    }
    
    
    /**
     * function that shows a different menu for a user
     * @param cliente client that is using the site
     */
    
    private void menu(Cliente cliente){
        
        ArrayList<PreparaProduto> listaDeCompras = new ArrayList<>();
        int compra = 0;
        int escolha;
        
        do{
            Scanner stdin = new Scanner(System.in);
            System.out.println("1 - Ver Catalogo");
            System.out.println("2 - Acrescentar produto ao carrinho");
            System.out.println("3 - Finalizar compra");
            System.out.println("4 - Mostrar compras realizadas");
            System.out.println("Outro - Terminar ações");
            
            if (stdin.hasNextInt()) {
                    escolha = stdin.nextInt();
                }else{
                    escolha = 0;
                }
                if (escolha > 4 || escolha < 0) {
                    escolha = 0;
                }
            
                switch(escolha){
                    case 1 -> listaProd();
                    case 2 -> {
                        PreparaProduto prod = compraProd();
                        if(prod == null){
                            System.out.println("Produto indisponivel");
                        }else{
                            boolean existe = false;
                            for(PreparaProduto p : listaDeCompras){
                                if(p.getProd().equals(prod.getProd())){
                                    existe = true;
                                    p.setQuantidade(prod.getQuantidade()+p.getQuantidade());
                                    break;
                                }
                            }
                            
                            if(!existe){
                                listaDeCompras.add(prod);
                            }
                        }
                            
                    }
                    case 3 -> {
                        finalizaComp(cliente, listaDeCompras);
                        f.gravaInfo(produtos, clientes);
                    }
                    case 4 -> listarCompras(cliente);
                    case 0 -> {
                     f.gravaInfo(produtos, clientes);
                     System.out.println("Compra cancelada com sucesso");
                     compra = 1;
                    }
                    
                }
        }while(compra != 1);
    }
    
    
    /**
     * function responsible for logging the user in the app
     * @return the client that is logging in
     */
    private Cliente login(){

        Cliente cliente = null;
        System.out.println("Bem Vindo");
        System.out.println("Insira o seu email: ");
        Scanner stdin = new Scanner(System.in);
        String email = stdin.next();
        
        if(clientes.isEmpty()){
            cliente = criaCliente(email);
            System.out.println("Obrigado pelo seu registo " + cliente.getNome());
            return cliente;

        }
        else{
            for (int i = 0; i < clientes.size(); i++) {
                if(email.equals(clientes.get(i).getMail())){
                    cliente = clientes.get(i);
                    System.out.println("Bem vindo "+ cliente.getNome());
                    return cliente;
                }   
        }
    }
        cliente = criaCliente(email);
        System.out.println("Obrigado pelo seu registo " + cliente.getNome());
        clientes.add(cliente);
        return cliente;
}

    
    /**
     * function that signs in a new client
     * @param mail mail of the user 
     * @return the new client
     */
    
    private Cliente criaCliente(String mail) {
        int numero;
        String nome;
        String morada;
        Data dataN;
        System.out.println("Insira o seu nome: ");
        Scanner stdin = new Scanner(System.in);
        nome = stdin.next();
        numero = 0;
        do{
            System.out.println("Insira o seu número de telemóvel: ");
            stdin = new Scanner(System.in);
            if(stdin.hasNextInt()){
                numero = stdin.nextInt();
            }

        }while(((int)Math.log10(numero))+1 != 9);
        System.out.println("Insira a sua morada: ");
        stdin = new Scanner(System.in);
        morada = stdin.next();
        System.out.println("Insira a sua data de nascimento: ");
        dataN = new Data();
        
        
        Cliente client = new Cliente(new ArrayList<>(), clientes.size(), nome, numero, morada, mail, dataN);     
        return client;  
    }

    
    /**
     * function that prints the existing products
     */
    private void listaProd() {
        System.out.println("\nCatálogo:");
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println(produtos.get(i).toString(data)+"\n");
        }
    }
    
    
    
    /**
     * function that aks the user for the product that he wants to buy
     * @return the product that the user bought
     */
    private PreparaProduto compraProd() {
        PreparaProduto prod = null;
        System.out.println("Insira o nome do produto: ");
        Scanner stdin = new Scanner(System.in);
        String nome = stdin.next();
        System.out.println("Insira a quatidade: ");
        int quant = pedeInteiro();
        Produto p = procuraProd(nome, quant);
        if(p != null) {
            prod = new PreparaProduto(p, quant);
        }
        return prod;
    }
    
    /**
     * function that find if a product is in the store
     * @param nome the name of the product
     * @param quant the quantity that the client wants to buy
     * @return the respective product or null if it isn´t in the store
     */
    private Produto procuraProd(String nome, int quant){
        Produto aSerAnalizado;
        for(StockProduto p : produtos){
            aSerAnalizado=p.getProd();
            if(aSerAnalizado.getNome().equals(nome)){
                if(p.getQuantidadeEmStock()>=quant){
                    p.setQuantidadeEmStock(p.getQuantidadeEmStock()-quant);
                    return aSerAnalizado;
                }
            }
        }
        return null;
    } 
    
    
    /**
    * function that asks user for a valid Integer
    * @return the integer that the user inputs
    */
    private int pedeInteiro() {
        int num;
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            num = sc.nextInt();
        } else {
            System.out.println("Número inválido");
            return pedeInteiro();
        }
        return num;
    }
    
    /**
     * function that asks user for a valid Float
     * @return the float that the user inputs
     */
    private float pedeFloat(){
        float num;
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextFloat()) {
            num = sc.nextFloat();
        } else {
            System.out.println("Número inválido");
            return pedeFloat();
        }
        return num;
    }
    
    /**
     * function that ends a purchase, it calcules the price to pay and prints the receipt
     * @param cliente the client who is purchasing
     * @param compras arraylist of every purshase made in the store
     */
    private void finalizaComp(Cliente cliente, ArrayList<PreparaProduto> compras) {
        int precoTransp=0;
        ArrayList<Compra> historico = cliente.getCompras();
        float preco = calculaPreco(compras);
        if(preco == 0){
            System.out.println("Carrinho vazio!");
            return;
        }
        int comprasAntigas = historico.size();
        if (comprasAntigas > 5) {
            if (preco < 40) {
                precoTransp=15;
            }
        } else {
            precoTransp = 20;
        }
        precoTransp = precoTransp + transporteMobilia(compras);
        Compra compra = new Compra(compras, preco+precoTransp,data);
        historico.add(compra);
        cliente.setCompras(historico);
        compra.printFatura(data, precoTransp);
    }

    /**
     * function that calcules the price of the transportation
     * @param listaDeCompras arraylist of the products who will be sent
     * @return the price to pay for transportation
     */
    private int transporteMobilia(ArrayList<PreparaProduto> compras) {
        int preco = 0;
        for (PreparaProduto p : compras) {
            Produto aVerificar = p.getProd();
            int identificador = aVerificar.getIdentificador();
            if (identificador % 10 == 3) {
                Mobilia mob = (Mobilia) aVerificar;
                if (mob.getPeso() > 15) {
                    preco = preco + 10*p.getQuantidade();
                }
            }
        }
        return preco;
    }

    
    /**
     * function that calcules the final price to pay
     * @param compras arraylist of the products to buy
     * @return the final price
     */
    private float calculaPreco(ArrayList<PreparaProduto> compras) {
        float preco = 0;
        for (PreparaProduto p : compras) {
            Produto aCalcPreco = p.getProd();
            if (aCalcPreco.getPromo() != null) {
                Promocao promo = aCalcPreco.getPromo();
                if (promo.procuraPromo(data)) {
                    preco = preco + promo.calculaPromo(p.getQuantidade(), aCalcPreco.getPrecoUnidade());
                } else {
                preco = aCalcPreco.getPrecoUnidade() * p.getQuantidade() + preco;
            }
            } else {
                preco = aCalcPreco.getPrecoUnidade() * p.getQuantidade() + preco;
            }
        }
        return preco;
    }

    /**
     * function that shows to the client every purshase made at the store
     * @param cliente the client that wants to see the purshases
     */
    private void listarCompras(Cliente cliente) {
        for(Cliente c : clientes){
            if(c.getNome().equals(cliente.getNome())){
                if(c.getCompras().isEmpty()){
                    System.out.println("Ainda não fez nenhuma compra!!!");
                }else{
                    int i = 1;
                    for(Compra comp: c.getCompras()){
                        System.out.println("\n---Compra "+i+"---");
                        comp.printListaCompras(comp.getDate());
                        i++;
                    }
                    System.out.println();
                }
            }
        }
    }

    
    /**
     * function responsible to add new produtcs to the store
     */
    private void addProduto() {
        System.out.println("Insira o nome do produto a adicionar: ");
        Scanner stdin = new Scanner(System.in);
        String nome = stdin.next();
        System.out.println("Insira a quatidade do Stock: ");
        int quant = pedeInteiro();
        Produto p = procuraProd(nome, quant);
        if(p == null) {
            System.out.println("Insira o número identificador do produto: ");
            int id = pedeInteiro();
            System.out.println("Insira o preco unitário do produto");
            float preco= pedeFloat();
            switch (id%10) {
                case 1:
                    Alimentar prodAlimentar;
                    System.out.println("Insira o número de calorias: ");
                    int numCalorias = pedeInteiro();
                    System.out.println("Insira a percentagem de gordura: ");
                    float percentGordura=pedeFloat();
                    prodAlimentar = new Alimentar(numCalorias, percentGordura, id, nome, preco, criaPromo(id));
                    StockProduto prodAliAdicionar = new StockProduto(quant, prodAlimentar);
                    produtos.add(prodAliAdicionar);
                    break;
                case 2:
                    Limpeza prodLimp;
                    System.out.println("insira o grau de toxicidade");
                    int toxicidade=pedeInteiro();
                    while(toxicidade<0 || toxicidade>10){
                        toxicidade=pedeInteiro();
                    }
                    prodLimp = new Limpeza(toxicidade, id, nome, preco, criaPromo(id));
                    StockProduto prodLimpAdicionar = new StockProduto(quant, prodLimp);
                    produtos.add(prodLimpAdicionar);
                    break;
                case 3:
                    Mobilia prodMobilia;
                    System.out.print("Peso:");
                    int peso=pedeInteiro();
                    System.out.print("Largura:");
                    int largura=pedeInteiro();
                    System.out.println("Comprimento:");
                    int comprimento=pedeInteiro();
                    System.out.print("Altura:");
                    int Altura=pedeInteiro();
                    prodMobilia = new Mobilia(peso, Altura, largura, comprimento, id, nome, preco, criaPromo(id));
                    StockProduto prodMobiAdicionar = new StockProduto(quant, prodMobilia);
                    produtos.add(prodMobiAdicionar);
                    break;
                default:
                    System.out.println("Identificador inválido");
                    return;
            }
        }
    }
    
    /**
     * function that makes a new promotion depending on the id of the produtc
     * @param id the id of the produtc
     * @return the promo
     */
    private Promocao criaPromo(int id){
        int promo = id/1000;
        
        if(promo == 0){
            return null;
        }
        ArrayList<Data> dataini = new ArrayList<>();
        ArrayList<Data> datafim = new ArrayList<>();
        int controlo = 1;
        do{
            System.out.println("Insira a data de inicio da promoção: ");
            Data datainicio = new Data();
            System.out.println("Insira a data final da promoção: ");
            Data datafinal = new Data();
            
            if(datainicio.comparaDatas(datainicio, datafinal)){
                dataini.add(datainicio);
                datafim.add(datafinal);
            }else{
                dataini.add(datafinal);
                datafim.add(datainicio);
            }
            
            System.out.println("Quer adicionar mais uma promoção? ");
            System.out.println("Sim - 1");
            System.out.println("Não - Outro");
            controlo = pedeInteiro();
            
        }while(controlo == 1);
        
        
        if(promo == 1){
            PromoPagaMenos p = new PromoPagaMenos();
            p.setDatainicio(dataini);
            p.setDatafim(datafim);
            return p;
        }else if(promo == 2){
            Promo4para3 p = new Promo4para3();
            p.setDatainicio(dataini);
            p.setDatafim(datafim);
            return p;
        }
        return null;
    }
    
    
    /**
     * function responsible to add more produts to the stock
     */
    private void aumentaStock() {
        System.out.println("Insira o nome do produto: ");
        Scanner stdin = new Scanner(System.in);
        String nome = stdin.next();
        System.out.println("Insira a quatidade: ");
        int quant = pedeInteiro();
        Produto p = procuraProd(nome,-quant);
        if (p == null) {
            System.out.println("O Produto não existe");
        }
    }
}