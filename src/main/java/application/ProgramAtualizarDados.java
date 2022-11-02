package application;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProgramAtualizarDados {
    public static void main(String[] args) {



        /*Connection conn = DB.getConnection();
        DB.closeConnection();*/

        //Connction/Statment/Result são recursos externos não controlados pela JVM então é indicado que fechemos esses recursos
        //para não ter vazamento de dados

        //conectar o banco
        Connection conn = null;
        //prepara um consulta sql
        Statement st = null;

        //o resultado da consulta eu guardo na variavel rs
        ResultSet rs = null;

        try{
            //CONECTO AO BD
            conn= DB.getConnection();

            //intanciei o objeto do tipo statment
            st= conn.createStatement();

            //esse comando espera que eu entre com uma String
            rs = st.executeQuery("select * from department");

            //percorrer um result set
            //funciona enquanto existir um próximo
            while (rs.next()){
                System.out.println(rs.getInt("Id") + ", "+rs.getString("Name"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DB.closeResultSet(rs);
            DB.closeStatment(st);
            DB.closeConnection();

        }
    }

}
