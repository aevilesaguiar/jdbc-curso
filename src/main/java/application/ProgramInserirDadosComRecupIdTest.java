package application;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ProgramInserirDadosComRecupIdTest {
    public static void main(String[] args) {

        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");

        Connection conn=null;

        //Objeto que permite montar a consulta sql deixando os paramentros para colocar depois
        PreparedStatement st = null;


        //Inserção com recuperação de Id
        try{
            //conectar o banco
            conn= DB.getConnection();

        //Um comando sql para inserir dois departamentos D1 e D2 ao mesmo tempo
            st= conn.prepareStatement(
                    "insert INTO department (Name) values ('D1'),('D2') ",
                    Statement.RETURN_GENERATED_KEYS);

            //quando é uma operação que você vai alterar os dados você chama executeUpdate();
            int rowsAffected= st.executeUpdate();

            if(rowsAffected>0){
                //pegar o código do novo comando inserido
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()){
                    int id = rs.getInt(1);//indica que quero o valor da primeira coluna
                    System.out.println("Done! Id = "+id);
                }


            }else{
                System.out.println("Nenhuma linha foi alterada!/ No rown affected!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        finally {

           DB.closeStatment(st);
           //a conexao é sempre fechada por último
           DB.closeConnection();
        }


    }
}
