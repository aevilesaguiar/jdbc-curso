package application;

import db.DB;
import db.DbException;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ProgramTransacao {
    public static void main(String[] args) {

        //transações

        //conectar o banco
        Connection conn = null;

        //prepara um consulta sql
        Statement st = null;



        try {
            //CONECTO AO BD
            conn = DB.getConnection();

            //não é para confirmar as operações automaticamente
            //todas as operações por padrão vão ficar pendentes de uma confirmação explícita do programador
            conn.setAutoCommit(false);

            //instanciar o statment
            st= conn.createStatement();


            int rows1 = st.executeUpdate("UPDATE seller SET Basesalary=2090 WHERE DepartmentId = 1");

           /* //simular uma falha
            int x = 1;
            if(x<2){
                throw  new SQLException("Fake error");
            }*/

            int rows2 = st.executeUpdate("UPDATE seller SET Basesalary=3090 WHERE DepartmentId = 2");


            //confirmar se as transações terminaram
            conn.commit();

            System.out.println("rows 1: "+rows1);
            System.out.println("rows 2: "+rows2);

        } catch (SQLException e) {
           try {
               //voltar a transação se ela tiver um erro
               conn.rollback();
               throw new DbException("Transaction rolled back! Caused by: "+e.getMessage());
           }catch (SQLException e1){
              throw new DbException("Error trying to rollback! Caused by: "+e1.getMessage());
           }
        } finally {

            DB.closeStatment(st);
            DB.closeConnection();

        }
    }

}
