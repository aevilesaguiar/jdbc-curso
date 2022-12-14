package application;

import db.DB;

import java.sql.*;

public class ProgramAtualizarDados {
    public static void main(String[] args) {

        //atualizar dados

        //conectar o banco
        Connection conn = null;

        //prepara um consulta sql
        PreparedStatement st = null;



        try {
            //CONECTO AO BD
            conn = DB.getConnection();

            //intanciei o objeto do tipo statment
            st = conn.prepareStatement(
                    "UPDATE seller "
                            + "SET BaseSalary = BaseSalary + ?"
                            + "WHERE "
                            + "(DepartmentId = ?)");

            //atribuir valores para as duas interrogações
            st.setDouble(1,200.00);
            st.setInt(2,2);

            int rowsAffected = st.executeUpdate();

            System.out.println("Done ! Rows Affected: "+rowsAffected);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            DB.closeStatment(st);
            DB.closeConnection();

        }
    }

}
