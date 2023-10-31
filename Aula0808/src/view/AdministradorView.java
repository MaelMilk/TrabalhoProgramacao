package view;

import model.DAO.AdministradorDAOImpl;
import model.Login;
import model.Produto;
import model.Usuario;

import java.sql.SQLException;
import java.util.Scanner;

public class AdministradorView {
    public static void menu() throws SQLException {
        AdministradorDAOImpl a = new AdministradorDAOImpl();
        Produto p = new Produto();
        Usuario u = new Usuario();
        Login l = new Login();
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("MENU ADMINISTRADOR\n\n1. Inserir produto\n2. Deletar Produto(Nao esquecer de excluir as entradas e saidas)\n3. Registrar " +
                    "usuario\n4. Deletar usuario\n5. Alterar usuario\n6. Ver usuario\n7. Alterar produto\n" +
                    "8. Ver produto\n9. Sair");
            opcao = Integer.parseInt(scanner.nextLine());
            int id, idusuario;
            String descricao, nome, sobrenome, login, senha, confirmasenha, funcao;
            switch (opcao) {
                case 1:
                    System.out.println("Escolha o ID do produto a ser inserido");
                    id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Escolha a descricao do produto");
                    descricao = scanner.nextLine();
                    p.setIdproduto(id);
                    p.setDescricao(descricao);
                    a.criarproduto(p);
                    System.out.println("Produto inserido");
                    break;

                case 2:
                    System.out.println("Escolha o ID do produto a ser excluido");
                    id = Integer.parseInt(scanner.nextLine());
                    p.setIdproduto(id);
                    a.removerproduto(p);
                    System.out.println("Produto deletado");
                    break;

                case 3:
                    System.out.println("Insira o ID do usuario");
                    id = Integer.parseInt(scanner.nextLine());
                    u.setIdusuario(id);
                    l.setIdusuario(id);
                    System.out.println("Insira o nome do usuario a ser inserido");
                    nome = scanner.nextLine();
                    u.setNome(nome);
                    System.out.println("Insira o sobrenome do usuario");
                    sobrenome = scanner.nextLine();
                    u.setSobrenome(sobrenome);
                    System.out.println("Insira o login do usuario");
                    login = scanner.nextLine();
                    u.setLogin(login);
                    l.setidlogin(login);
                    System.out.println("Insira a senha do usuario");
                    senha = scanner.nextLine();
                    System.out.println("Confirme a senha do usuario");
                    confirmasenha = scanner.nextLine();
                    while (!confirmasenha.equalsIgnoreCase(senha)){
                        System.out.println("Senha errada, digite novamente.");
                        System.out.print("Digite a senha: ");
                        senha = scanner.nextLine();
                        System.out.print("Confirme a senha: ");
                        confirmasenha = scanner.nextLine();
                    }
                    u.setSenha(senha);
                    l.setSenha(senha);
                    System.out.println("Insira a funcao do usuario");
                    funcao = scanner.nextLine();
                    u.setFuncao(funcao);
                    a.registrarusuario(u);
                    a.registrarlogin(l);
                    System.out.println("Usuario inserido");
                    break;

                case 4:
                    System.out.println("Escolha o ID do usuario a ser exluido");
                    idusuario = Integer.parseInt(scanner.nextLine());
                    u.setIdusuario(idusuario);
                    a.excluirlogin(u,l);
                    a.excluirusuario(u);
                    System.out.println("Usuario deletado");
                    break;

                case 5:
                    a.alterarusuario(u,l);
                    a.alterarlogin(l);
                    break;

                case 6:
                    a.verususarios(u);
                    break;

                case 7:
                    a.alterarproduto();
                    break;

                case 8:
                    a.verproduto();
            }
        }while (opcao != 9);
    }
}
