package view;

import model.*;
import model.DAO.UsuarioDAOImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class UsuariosView {
    public static void menu() throws SQLException {
        Saida s = new Saida();
        Entrada e = new Entrada();
        ProdutoEntrada pe = new ProdutoEntrada();
        ProdutoSaida ps = new ProdutoSaida();
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("MENU USUARIOS\n\n1. Inserir entrada\n2. Inserir produto no estoque\n3. Inserir saida\n" +
                    "4. Baixar produto do estoque\n5. Ver produto no estoque\n6. Excluir saida\n7. Excluir entrada\n" +
                    "9. Sair");
            opcao = Integer.parseInt(scanner.nextLine());
            int identrada, idproduto, idsaida, qtd, valor;
            String dataped, dataentr, datasaid;
            switch (opcao) {
                case 1:
                    System.out.println("Insira o ID da entrada");
                    identrada = Integer.parseInt(scanner.nextLine());
                    System.out.println("Insira a data do pedido (YYYY-MM-DD)");
                    dataped = scanner.nextLine();
                    System.out.println("Insira a data de entrada (YYYY-MM-DD)");
                    dataentr = scanner.nextLine();
                    e.setIdentrada(identrada);
                    e.setDataped(dataped);
                    e.setDataentr(dataentr);
                    usuarioDAO.inserirentrada(e);
                    break;
                case 2:
                    System.out.println("Informe o ID do produto para inserir no estoque");
                    idproduto = Integer.parseInt(scanner.nextLine());
                    System.out.println("Insira o ID de entrada desejado");
                    identrada = Integer.parseInt(scanner.nextLine());
                    System.out.println("Informe a quantidade do produto a ser inserido no estoque");
                    qtd = Integer.parseInt(scanner.nextLine());
                    System.out.println("Informe o valor total do produto inserido");
                    valor = Integer.parseInt(scanner.nextLine());
                    pe.setIdentrada(identrada);
                    pe.setIdproduto(idproduto);
                    pe.setQtd(qtd);
                    pe.setValor(valor);
                    usuarioDAO.inserirestoque(pe);
                    break;

                case 3:
                    System.out.println("Insira o ID de saida");
                    idsaida = Integer.parseInt(scanner.nextLine());
                    System.out.println("Insira a data da saida (YYYY-MM-DD)");
                    datasaid = scanner.nextLine();
                    s.setIdsaida(idsaida);
                    s.setDatasaida(datasaid);
                    usuarioDAO.inserirsaida(s);
                    break;
                case 4:
                    System.out.println("Informe o ID do produto para ser baixado do estoque");
                    idproduto = Integer.parseInt(scanner.nextLine());
                    System.out.println("Insira o ID de saida desejado");
                    idsaida = Integer.parseInt(scanner.nextLine());
                    System.out.println("Informe a quantidade do produto para baixar no estoque");
                    qtd = Integer.parseInt(scanner.nextLine());
                    System.out.println("Informe o valor total dos produto baixado");
                    valor = Integer.parseInt(scanner.nextLine());
                    ps.setIdsaida(idsaida);
                    ps.setIdproduto(idproduto);
                    ps.setQtd(qtd);
                    ps.setValor(valor);
                    usuarioDAO.baixarestoque(ps);
                    break;

                case 5:
                    usuarioDAO.verestoqueproduto();
                    break;

                case 6:
                    System.out.println("Insira o ID de produto");
                    idproduto = Integer.parseInt(scanner.nextLine());
                    System.out.println("Insira o ID da saida");
                    idsaida = Integer.parseInt(scanner.nextLine());
                    ps.setIdsaida(idsaida);
                    ps.setIdproduto(idproduto);
                    usuarioDAO.Deletarregistrosaida(ps);
                    break;

                case 7:
                    System.out.println("Insira o ID de produto");
                    idproduto = Integer.parseInt(scanner.nextLine());
                    System.out.println("Insira o ID da entrada");
                    identrada = Integer.parseInt(scanner.nextLine());
                    pe.setIdproduto(idproduto);
                    pe.setIdentrada(identrada);
                    usuarioDAO.Deletarregistroentrada(pe);
            }
        }while (opcao != 9);
    }
}