package application;

import db.DB;
import db.DbIntegrityException;

import java.sql.*;

public class ProgramDeletarDados {
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
                    "DELETE FROM department "
                    +" WHERE "
                    +"Id=?"
                    );

            //atribuir valores para as duas interrogações
            st.setInt(1,2);

            int rowsAffected = st.executeUpdate();

            System.out.println("Done ! Rows Affected: "+rowsAffected);

        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {

            DB.closeStatment(st);
            DB.closeConnection();

        }
    }

}
