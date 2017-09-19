package br.com.treinamento.javamysql;

import br.com.treinamento.javamysql.model.beans.Contato;
import br.com.treinamento.javamysql.model.dao.ContatoDao;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class JDBCExemplo {
    
    public static void main(String[] args) throws SQLException {

        System.out.println("   _____ _     _                          _____          _           _             \n" +
        "  / ____(_)   | |                        / ____|        | |         | |            \n" +
        " | (___  _ ___| |_ ___ _ __ ___   __ _  | |     __ _  __| | __ _ ___| |_ _ __ ___  \n" +
        "  \\___ \\| / __| __/ _ \\ '_ ` _ \\ / _` | | |    / _` |/ _` |/ _` / __| __| '__/ _ \\ \n" +
        "  ____) | \\__ \\ ||  __/ | | | | | (_| | | |___| (_| | (_| | (_| \\__ \\ |_| | | (_) |\n" +
        " |_____/|_|___/\\__\\___|_| |_| |_|\\__,_|  \\_____\\__,_|\\__,_|\\__,_|___/\\__|_|  \\___/ \n" +
        "                                                                                   \n" +
        "                                                                                   ");
        
        int opcao = 0;
        Scanner leia= new Scanner(System.in);
        do {            
            System.out.println("Bem vindo ao sistema de Contato!");
            System.out.println("Escolha uma opção: ");
            System.out.println("Digite [1] - Para listar os Contato");
            System.out.println("Digite [2] - Para Cadastrar novo");
            System.out.println("Digite [3] - Alterar um Contato");
            System.out.println("Digite [4] - Excluir um Contato");
            System.out.println("Digite [5] - Sair do sistema");
            opcao = leia.nextInt();
            limparTela();
            switch(opcao) {
                case 1:
                    listar();
                break;
                case 2:
                    cadastar();
                break;
                case 3:
                    alterar();
                break;
                case 4:
                   deletar();
                break;
                case 5:
                    
                break;
                default:
                    System.out.println("Opção inválida! XD ");
            }
        } while (opcao != 5);
        
        System.out.println("Até a próxima!! o/");
        System.exit(0);
    }
    public static void listar(){
         ContatoDao dao = new ContatoDao();
        List<Contato> contatos = dao.getLista();
        System.out.println("| Id \t| Nome \t| Email \t| Endereço \t");
        for (Contato contato : contatos) {
            System.out.println("| "+contato.getId()+" \t| "+contato.getNome()+" \t| "+ contato.getEmail()+" \t| "
                +contato.getEndereco());
        }
    }
    public static void cadastar(){
        Scanner leia= new Scanner(System.in);
        Contato contato1 = new Contato();
        System.out.println("Digite o nome");
        String nome = leia.nextLine();
        contato1.setNome(nome);
        System.out.println("Digite o email");
        String email = leia.nextLine();
        contato1.setEmail(email);
        System.out.println("Digite o endereço");
        String endereco = leia.nextLine();
        contato1.setEndereco(endereco);
        ContatoDao salvarContato = new ContatoDao();
        salvarContato.adicionar(contato1);
    }
    
    public static void alterar() {
        int id;
        Scanner leia= new Scanner(System.in);
        System.out.println("Digite o id do contato a ser alterado:");
        id =  leia.nextInt();
        ContatoDao daoPesquisa = new ContatoDao();
        Contato contato = daoPesquisa.getContatoById(id);
        if(contato != null) {
            System.out.println("| Id \t| Nome \t| Email \t| Endereço \t");
            System.out.println("| "+contato.getId()+" \t| "+contato.getNome()+" \t| "+ contato.getEmail()+" \t| "
                +contato.getEndereco());
            System.out.println("Esse contato que você deseja alterar? [1] - Sim [2] - Não");
            int opt = leia.nextInt();
            if (opt == 1) {
                limparTela();
                Contato atContato = new Contato();
                atContato.setId(contato.getId());
                System.out.println("Alterar o Nome: "+ contato.getNome());
                leia.nextLine();
                String n = leia.nextLine();
                atContato.setNome(n);
                System.out.println("Alterar o Email: "+ contato.getEmail());
                String email = leia.nextLine();
                atContato.setEmail(email);                
                System.out.println("Alterar o Endereço: "+ contato.getEndereco());
                String endereco = leia.nextLine();
                atContato.setEndereco(endereco);
                ContatoDao daoAlt = new ContatoDao();
                daoAlt.altera(atContato);
                limparTela();
                System.out.println("Alterado com Sucesso");
            }
            
        } else {
            System.out.println("Contato não encontrado!");
        }
        
    }
    
    public static void deletar(){
        int id;
        Scanner leia= new Scanner(System.in);
        System.out.println("Digite o id do contato a ser deletado:");
        id =  leia.nextInt();
        ContatoDao daoPesquisa = new ContatoDao();
        Contato contato = daoPesquisa.getContatoById(id);
        if (contato != null) {
            System.out.println("| Id \t| Nome \t| Email \t| Endereço \t");
            System.out.println("| "+contato.getId()+" \t| "+contato.getNome()+" \t| "+ contato.getEmail()+" \t| "
                +contato.getEndereco());
           ;
            System.out.println("Esse contato que você deseja excluir????? [1] - Sim [2] - Não");
            int opt = leia.nextInt();
            if (opt == 1) {
                ContatoDao daoRemove = new ContatoDao();
                daoRemove.remove(contato);
                limparTela();
                System.out.println("Removido com Sucesso!");
            }            
        } else {
            System.out.println("Contato não encontrado!");
        }
    }
    private static void limparTela(){
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
}
