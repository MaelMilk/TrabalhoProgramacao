package model.DAO;

import java.sql.*;
import java.util.ArrayList;

public class ConexaoDAO {
    private Connection Conn;
    private Statement s;
    private DatabaseMetaData meta;
    private ResultSet rs;

    public void ConSV() throws ClassNotFoundException, SQLException {
        /*Conectando ao Servidor de Banco de Dados*/
        Class.forName("com.mysql.jdbc.Driver");
        this.Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","");//cria conexão com o servidor
        this.s= Conn.createStatement();

        System.out.println("BD Conectado");
        /*****************************************/

    }


    public void CriaDB(String database) throws SQLException {
        /*Criando Base de Dados*/
        ArrayList list = new ArrayList();
        this.meta = this.Conn.getMetaData();//Seleciona todas as bases de dados do servidor
        this.rs = meta.getCatalogs(); //faz um ponteiro para cada database
        while (this.rs.next()) { //enquanto houverem databases
            String listofDatabases=this.rs.getString("TABLE_CAT"); //resgata o nome da database
            list.add(listofDatabases); //adiciona a uma lista
        }
        if(list.contains(database)){ //se a base de dados 'teste' já existir
            this.s.executeUpdate("DROP DATABASE "+database);//deleta database existente
            this.s.executeUpdate("CREATE DATABASE "+database);//cria uma nova database com o mesmo nome
            System.out.println("Database criada!");
        }
        else{
            this.s.executeUpdate("CREATE DATABASE "+database);//cria uma nova database de nome 'teste'
            System.out.println("Database criada!");
        }
        rs.close();//fecha conexão com a lista de bases de dados
    }

    public Connection ConDB(String database) throws SQLException {
        this.Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database,"root","");//cria conexão com a base de dados criada
        this.s= this.Conn.createStatement(); //cria objeto Statement
        System.out.println("Conectado à base de dados "+database);
        return this.Conn;

        /*****************************************/
    }

    public void closeConn() throws SQLException {
        this.Conn.close();
    }
    public Connection getConn() {
        return Conn;
    }

    public void setConn(Connection conn) {
        Conn = conn;
    }

    public Statement getS() {
        return s;
    }

    public void setS(Statement s) {
        this.s = s;
    }

    public DatabaseMetaData getMeta() {
        return meta;
    }

    public void setMeta(DatabaseMetaData meta) {
        this.meta = meta;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
}
