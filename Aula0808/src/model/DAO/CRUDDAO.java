package model.DAO;

import java.sql.*;
import java.util.Scanner;

public class CRUDDAO {
    private Statement stmt;
    private String SQL;
    private ResultSet rset;
    private ResultSetMetaData rsmd;

    public void CriaTab(ConexaoDAO conexao) throws SQLException {
        String SQL = new String(); //limpando variável para receber novo valor
        /* Criando Tabela */
        SQL = "CREATE TABLE Alunos (nome varchar(50),curso varchar(50),fase int,cpf varchar(50) PRIMARY KEY)";
        conexao.getS().executeUpdate(SQL); //executa

        System.out.println("Tabela 'Alunos' Criada!");
        /*****************************************/

        this.SQL = new String(); //limpando variável para receber novo valor

        this.stmt = conexao.getConn().createStatement(); //cria objeto do tipo Statement
    }

    public void InsDados(ConexaoDAO conexao) throws SQLException {
        // Tabela a ser analisada
        String tabela = "Alunos";
        this.rset = this.stmt.executeQuery("SELECT * from "+tabela); //cria ponteiro para a tabela

        String valores = "";
        String campos="";
        Scanner leia = new Scanner(System.in);

        this.rsmd = this.rset.getMetaData(); //recupera informacoes da tabela
        // retorna o numero total de colunas
        int numColumns = this.rsmd.getColumnCount();
        System.out.println("Total de Colunas = " + numColumns);

        // loop para recuperar os metadados de cada coluna (nome da coluna, tipo do campo, etc)
        for (int i=0; i<numColumns; i++) {
            System.out.print("Insira o dado para a coluna=" + this.rsmd.getColumnName (i + 1)+": ");

            if((this.rsmd.getColumnTypeName (i + 1)).equals("INT")){ //se o campo for INT
                if(i!=numColumns-1){ //se não for a última coluna
                    valores = valores+leia.next()+",";//concatena apenas uma virgula(tipo INT nao precisa de aspas)
                    campos = campos+this.rsmd.getColumnName (i + 1)+",";//recupera nome do campo (coluna) e insere uma virgula
                }
                else{//se for a ultima coluna
                    valores = valores+leia.next();//le o dado
                    campos = campos+this.rsmd.getColumnName (i + 1);//recupera nome da coluna
                }
            }else{ //se nao for INT
                if(i!=numColumns-1){//se não for a ultima coluna
                    valores = valores+"'"+leia.nextLine()+"',"; //concatena uma aspa simples seguida de virgula
                    campos = campos+this.rsmd.getColumnName (i + 1)+",";//recupera nome da coluna e insere virgula
                }
                else{ //se for ultimo valor
                    valores = valores+"'"+leia.next()+"'"; //concatena somente aspas simples, sem virgula
                    campos = campos+this.rsmd.getColumnName (i + 1);//recupera nome da coluna
                }
            }
        }

        this.SQL = "INSERT INTO "+tabela+" ("+campos+") VALUES ("+valores+")";//INSERT
        System.out.println(SQL);//'DEBUG'


        conexao.getS().executeUpdate(SQL); //executa
        System.out.println("Dados Inseridos!");
        /*****************************************/
    }

    public void VerDado(ConexaoDAO conexao) throws SQLException {
        String dados;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome a ser buscado: ");
        String Nome = scanner.nextLine();
        this.SQL = "SELECT * FROM Alunos WHERE nome='"+Nome+"'";
        ResultSet rs = conexao.getS().executeQuery(SQL);
        while(rs.next()){
            System.out.println(rs.getString("nome"));
            System.out.println(rs.getString("curso"));
            System.out.println(rs.getString("fase"));
            System.out.println(rs.getString("cpf"));
        }
    }

    public void DeletDado(ConexaoDAO conexao) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o cpf do aluno a ser deletado: ");
        String cpf = scanner.nextLine();
        this.SQL = "DELETE FROM Alunos WHERE cpf='"+cpf+"'";
        int linhas = conexao.getS().executeUpdate(SQL);
        System.out.println("Foram deletadas " +linhas+" linhas");
    }

    public void UpdateDado(ConexaoDAO conexao) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o cpf do aluno para mudar os dados: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o novo curso: ");
        String curso = scanner.nextLine();
        System.out.print("Digite a nova fase: ");
        String fase = scanner.nextLine();
        this.SQL = "UPDATE Alunos SET nome='"+nome+"',curso='"+curso+"',fase="+fase+" WHERE  cpf='"+cpf+"'";
        int linhas = conexao.getS().executeUpdate(SQL);
        System.out.println("Foram alteradas " +linhas+" linhas");
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public ResultSet getRset() {
        return rset;
    }

    public void setRset(ResultSet rset) {
        this.rset = rset;
    }

    public ResultSetMetaData getRsmd() {
        return rsmd;
    }

    public void setRsmd(ResultSetMetaData rsmd) {
        this.rsmd = rsmd;
    }

    public String getSQL() {
        return SQL;
    }

    public void setSQL(String SQL) {
        this.SQL = SQL;
    }
}
