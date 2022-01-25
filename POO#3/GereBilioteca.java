
package gerebilioteca;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that handles library book requests 
 * @author conta
 */
public class GereBilioteca {
    private Requisicao req;
    ArrayList<Requisicao> requisita;
    ArrayList<Livro> livros;
    ArrayList<Leitor> leitores;
    private Leitor l;

    
    public static void main(String[] args) {
        GereBilioteca gere = new GereBilioteca();
        gere.menu();
    }
    
    /**
     * Initializes variables
     */
    public GereBilioteca(){
        requisita = new ArrayList<>();
        leitores = new ArrayList<>();
        Leitor l1 = new Leitor (2020225542, "Rui");
        Leitor l2 = new Leitor (2020225543, "Horacio");
        leitores.add(l1);
        leitores.add(l2);
        livros = new ArrayList<>();
        Livro lv1 = new Livro ("Os Maias", "Eca de Queiroz");
        this.livros.add(lv1);
        Livro lv2 = new Livro ("Os Lusiadas", "Luis de Camoes");
        this.livros.add(lv2);
        Livro lv3 = new Livro ("O ano da morte", "Ricardo Reis");
        this.livros.add(lv3);
        Livro lv4 = new Livro ("Auto da Barca do Inferno", "Gil Vicente");
        this.livros.add(lv4);
        req = new Requisicao();
        l = new Leitor();
        
    }
    
    /**
     * Makes the menu the user will use
     */
    public void menu(){ 
        int control = 0;
        int escolha;
        do{
            //aks the user for the client usernumber
            System.out.print("Insira o numero do leitor: ");
            Scanner stdin = new Scanner(System.in);
            int userNumber = -1;
            if (stdin.hasNextInt()) {
                userNumber = stdin.nextInt();
            }


            if(!verificaLeitores(userNumber, leitores)){
                //if the usernumber is invalid the program ends
                System.out.println("Leitor inválido");
                System.exit(0);
            }else{
                for (int i = 0; i < leitores.size(); i++) {
                    if(leitores.get(i).getUserNumber() == userNumber){
                        l = leitores.get(i);
                    }
                }
            }

            System.out.println(l.toString());


            do{
                stdin = new Scanner(System.in);
                System.out.println("1 - Listar livros requisitados");
                System.out.println("2 - Listar livros disponíveis");
                System.out.println("3 - Requisitar livro");
                System.out.println("4 - Mudar de leitor");
                System.out.println("Outro - Sair");

                if (stdin.hasNextInt()) {
                    escolha = stdin.nextInt();
                }else{
                    escolha = 0;
                }
                if (escolha > 4 || escolha < 0) {
                    escolha = 0;
                }
                switch (escolha){
                    case 0 -> System.exit(0);
                    case 1 -> { 
                        Data date = new Data();
                        listaRequisitados(date);
                        System.out.println();
                        break;
                    }
                    case 2 -> {
                        listaDisponiveis();
                        System.out.println();
                        break;
                    }


                    case 3 -> {
                        System.out.println("Insira data de requisicao");
                        Data dataR = new Data(); 
                        System.out.println("Insira data de devolucao");
                        Data dataD = new Data(); 
                        if (!dataR.verificaDatas(dataR, dataD)) {
                            System.out.println("A data de devolucao nao pode ser anterior à data de requisicao\n");
                            break;
                        }
                        req = requisitaLivro(l, dataR, dataD);

                        if (req.getLivro() == null) {
                            System.out.println("O livro já está requisitado ou não existe na nossa biblioteca!!!");
                            break;
                        }


                        System.out.println(req.toString());
                        requisita.add(req);
                        System.out.println();
                        break;
                    }
                    case 4 -> {
                        escolha = 0;
                    }
                }
            }while (escolha != 0);

        }while(control != 1);
}
     
         /**
     * function that returns a complet request
     * @param l -> client
     * @param dataReq -> request date
     * @param dataDev -> return date
     * @return the reader's request or null if the book is requested or does not exist
     */
    Requisicao requisitaLivro(Leitor l, Data dataReq, Data dataDev){
        System.out.print("Insira o nome do livro: ");
        Scanner sc = new Scanner(System.in);
        String livro = sc.nextLine().toLowerCase();
       
        Requisicao reqi = new Requisicao(l, dataReq, dataDev);

        for (int i = 0; i < livros.size(); i++) {
            if(livro.equals(livros.get(i).getTitle().toLowerCase())){
                for (int j = 0; j < requisita.size(); j++) {
                    if(livros.get(i).getTitle().equals(requisita.get(j).getLivro().getTitle()))
                        //check if the date of request and return is valid for the book you want to order
                        if (!(dataReq.verificaDatas(dataReq, requisita.get(j).getDataInicio()) && dataReq.verificaDatas(dataDev, requisita.get(j).getDataInicio()) 
                                || (dataReq.verificaDatas(requisita.get(j).getDataFim(), dataReq) && dataReq.verificaDatas(requisita.get(j).getDataFim(), dataDev)))) {
                            reqi.setLivro(null);
                            return reqi;
                        }
                }
                reqi.setLivro(livros.get(i));
                break;
            }else{
                reqi.setLivro(null);
            }            
        }
        return reqi;
    }
    
    
    /**
     * function that shows the requested books in a certain date
     * @param date -> the date the user wants to check
     */
    public void listaRequisitados(Data date){
        int conta = 0;
        if (requisita.isEmpty()) {
            System.out.println("Não existe nunhum livro requisitado neste dia!!!");
        }
        for (int i = 0; i < requisita.size(); i++) {
            if ((date.getAno() <=requisita.get(i).getDataFim().getAno()) && 
                    (date.getAno()>=requisita.get(i).getDataInicio().getAno())) {
                if (date.getAno() == requisita.get(i).getDataInicio().getAno()) {
                    if(date.getMes() > requisita.get(i).getDataInicio().getMes()){
                        System.out.println("Livro: " + requisita.get(i).getLivro().getTitle() 
                                    + " requisitado por: " + requisita.get(i).getLeitor().getUsername());
                        return;
                    }if(date.getMes() == requisita.get(i).getDataInicio().getMes()){
                        if(date.getDia() >= requisita.get(i).getDataInicio().getDia()){
                            System.out.println("Livro: " + requisita.get(i).getLivro().getTitle() 
                                + " requisitado por: " + requisita.get(i).getLeitor().getUsername());
                            return;
                        }
                    }
                }
            }
            
        }
        if (conta == 0) {
            System.out.println("Não existe nunhum livro requisitado neste dia!!!");
        }
    }
    
    /**
     * function that shows the available books in a certain date
     */
    public void listaDisponiveis(){
        Data todayData = new Data();
        for (int i = 0; i < livros.size(); i++) {
            if (requisita.isEmpty()) {
               System.out.println(livros.get(i).toString()); 
            }
            else{
                for (int j = 0; j < requisita.size(); j++) {
                    if((!livros.get(i).getTitle().equals(requisita.get(j).getLivro().getTitle()))){
                        System.out.println(livros.get(i).toString());
                    }else if (livros.get(i).getTitle().equals(requisita.get(j).getLivro().getTitle()) 
                            && (todayData.verificaDatas(todayData, requisita.get(j).getDataInicio()) 
                            || todayData.verificaDatas(requisita.get(j).getDataFim(), todayData)) )
                        System.out.println(livros.get(i).toString());
                }
            }
        }
    }
    
    /**
     * function tat checks if the client is registered
     * @param user -> client number
     * @param leitores -> arraylist with all the registered clients
     * @return true or false if the client is or isn´t registered
     */
    private boolean verificaLeitores(int user, ArrayList<Leitor> leitores) {
        for (int i = 0; i < leitores.size(); i++) {
            int num = leitores.get(i).getUserNumber();
            if(user == num){
                return true;
            }
        }
        return false;
    }
}
