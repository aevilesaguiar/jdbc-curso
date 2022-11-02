package application;

import db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ProgramInserirDados {
    public static void main(String[] args) {

        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");

        Connection conn=null;

        //Objeto que permite montar a consulta sql deixando os paramentros para colocar depois
        PreparedStatement st = null;


        //inseção simples com PreparedStatment
        try{
            //conectar o banco
            conn= DB.getConnection();

            st = conn.prepareStatement(
                    "INSERT INTO seller "
                    +"(Name,Email, BirthDate, BaseSalary, DepartmentId) "
                    +"VALUES"
                    +"(?,?,?,?,?)"  );

            st.setString(1,"Carl Purple");
            st.setString(2,"carl@mail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
            st.setDouble(4,3000.00);
            st.setInt(5,4);

            //quando é uma operação que você vai alterar os dados você chama executeUpdate();
            int rowsAffected= st.executeUpdate();

            System.out.println("Done! Rows affected:"+rowsAffected);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        finally {

           DB.closeStatment(st);
           //a conexao é sempre fechada por último
           DB.closeConnection();
        }


    }
}
