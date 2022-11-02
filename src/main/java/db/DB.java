package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    //método para Conectar com o BD
    private static Connection conn=null;//Objeto de conexão com o BD do JDBC



    //a proxima vez que esse método getConection for chamado esse teste if(conn==null) vai falhar
    //ele vai pular o if e retorna a conexão já existente
    //método para conectar com o BD
    public static Connection getConnection(){
        //se o objeto estiver valendo nulo eu tenho que criar um código para conectar com o BD
        if(conn==null){
            try {
                Properties props = loadProperties();
                //url do BD
                String url = props.getProperty("dburl");
                //obter uma conexão com o BD, conectar com o JDBC é instanciar o objeto do tipo Connection
                conn = DriverManager.getConnection(url, props);//salvamos o objeto dentro da nossa variavel conn
            }catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
       // retorna o objeto conn que está declarado acima
        return conn;
    }


    //método para fechar a conexão
    public static void closeConnection(){
        //estou tentando se a minha conexão está instanciada, se estiver eu mando fechar
        if(conn!= null){
            try {
                conn.close();
            }catch (SQLException e){
                throw new DbException(e.getMessage());
            }

        }

    }

    //criar um método auxiliar para carregar as propriedades que estão salvas no db.properties

    private static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties properties = new Properties();
            properties.load(fs);
            return properties;
        }catch (IOException e){
            throw new DbException(e.getMessage());
        }

            }


//A classe FileInputStream é útil para ler dados de um arquivo na forma de sequência de bytes .
// FileInputStream destina-se à leitura de fluxos de bytes brutos, como dados de imagem.




    //fechamento dos recursos externos
    public static void closeStatment(Statement st){
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

}
