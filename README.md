# jdbc-curso Nélio Alves

## recuperar dados

Para trabalhar com recuperação de dadso nós temos que entender duas classes da API JDBC:

- Statment (a classe Statment serve para montar o comando sql para  ser executado, no caso para
recuperar os dados do Banco de Dados)
- ResultSet (a classe ResultSet vai representar um Objeto contendo o resultado da nossa consulta
na forma de tabela você vai poder acessar os dados por colunas e linhas)
O objeto ResultSet vai ter operações para você posicionar a tabela de resultados

    - first() [move para posição 1, se houver]
    - beforeFirst() [move para posição 0]
    - next() [move para o próximo, retorna false se já estiver no último]
    - absolute(int) [move para a posição dada, lembrando que dados reais começam em 1]


## Script


CREATE TABLE department (
Id int(11) NOT NULL AUTO_INCREMENT,
Name varchar(60) DEFAULT NULL,
PRIMARY KEY (Id)
);

CREATE TABLE seller (
Id int(11) NOT NULL AUTO_INCREMENT,
Name varchar(60) NOT NULL,
Email varchar(100) NOT NULL,
BirthDate datetime NOT NULL,
BaseSalary double NOT NULL,
DepartmentId int(11) NOT NULL,
PRIMARY KEY (Id),
FOREIGN KEY (DepartmentId) REFERENCES department (id)
);

INSERT INTO department (Name) VALUES
('Computers'),
('Electronics'),
('Fashion'),
('Books');

INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES
('Bob Brown','bob@gmail.com','1998-04-21 00:00:00',1000,1),
('Maria Green','maria@gmail.com','1979-12-31 00:00:00',3500,2),
('Alex Grey','alex@gmail.com','1988-01-15 00:00:00',2200,1),
('Martha Red','martha@gmail.com','1993-11-30 00:00:00',3000,4),
('Donald Blue','donald@gmail.com','2000-01-09 00:00:00',4000,3),
('Alex Pink','bob@gmail.com','1997-03-04 00:00:00',3000,2);
  
##  inserir dados

API:

     PreparedStatement (Objeto que permite montar a consulta sql deixando os paramentros para colocar depois
     executeUpdate
     Statement.RETURN_GENERATED_KEYS(permite que você recupere o ID do novo objeto inserido
     getGeneratedKeys


(?) -> Interrogação é o que chamamos de placeholder, é o lugar onde depois eu coloco o valor

