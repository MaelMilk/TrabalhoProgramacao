package model.DAO;
import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UsuarioDAO {
    void inserirentrada(Entrada entrada) throws SQLException;
    void inserirestoque(ProdutoEntrada produtoEntrada) throws SQLException;
    void inserirsaida(Saida saida) throws SQLException;
    void baixarestoque(ProdutoSaida produtoSaida) throws SQLException;
    void verestoqueproduto() throws SQLException;
    void Deletarregistrosaida(ProdutoSaida ps) throws SQLException;
    void Deletarregistroentrada(ProdutoEntrada pe) throws SQLException;
}
