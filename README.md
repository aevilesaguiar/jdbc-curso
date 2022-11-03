# jdbc-curso Nélio Alves

## Acesso a banco de dados com JDBC

JDBC (Java Database Connectivity): API padrão do Java para acesso a dados


Criando a aplicação java -> o jdbc permite você programar o acesso a dados de uma forma única. e na sua aplicação você vai instalar um driver do JDBC . O JDBC Driver Manager vai converter o código único (Java application) para os BD específicos, no exemplo da figura Postgres, Oracle...) e cada um desses Drivers vai converter o código que fiz (java application) usando a API do JDBC para o código nativo para acessar o BD específico.

Operações básicas da álgebra Relacional

Restrição
Projeção
Produto Cartesiano
Junção (produto cartesiano + restrição de chaves correspondentes)
Operação produto cartesiano -> SELECT * FROM PRODUCT, CATEGORY

Operação junção:

SELECT *
FROM PRODUCT, CATEGORY
WHERE
PRODUCT.CATEGORY_ID = CATEGORY.ID

SELECT *
FROM PRODUCT
INNER JOIN CATEGORY cat
ON PRODUCT.CATEGORY_ID = cat.ID
Operação de Restrição:

SELECT *
FROM PRODUCT
INNER JOIN CATEGORY cat ON PRODUCT.CATEGORY_ID = cat.ID
WHERE CATEGORY.NAME = 'Computers'
A clausura Where é o que faz a restrição.

Operação de Projeção

SELECT PRODUCT.*, CATEGORY.NAME
FROM PRODUCT
INNER JOIN CATEGORY cat ON PRODUCT.CATEGORY_ID = cat.ID
WHERE CATEGORY.NAME = 'Computers'


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


  
##  inserir dados

API:

     PreparedStatement (Objeto que permite montar a consulta sql deixando os paramentros para colocar depois
     executeUpdate
     Statement.RETURN_GENERATED_KEYS(permite que você recupere o ID do novo objeto inserido
     getGeneratedKeys


(?) -> Interrogação é o que chamamos de placeholder, é o lugar onde depois eu coloco o valor




##  deletar dados

Checklist:

     Criar DbIntegrityException : é por que é muito comum quando vamos excluir um dado
    do banco ocorrer um problema chamado integridade referencial( é quando por exemplo um
    dado está associado há uma chave estrangeira que não existe, pois ela ficará com uma referencia
    que não existe)

     Tratar a exceção de integridade referencial


API:

     setAutoCommit(false)
     commit()
     rollback()


## Demo: transações

Referências: https://www.ibm.com/support/knowledgecenter/en/SSGMCP_5.4.0/product-overview/acid.html


- ACID properties of transactions

No contexto de processamento de transações, a sigla ACID refere-se às quatro propriedades principais de 
uma transação: atomicidade, consistência, isolamento e durabilidade.

    Atomicidade
    Todas as alterações nos dados são executadas como se fossem uma única operação. Ou seja, todas as alterações são executadas, 
    ou nenhuma delas é.
    Por exemplo, em um aplicativo que transfere fundos de uma conta para outra, a propriedade atomicity garante que, se um 
    débito for feito com sucesso de uma conta, o crédito correspondente seja feito na outra conta.

    Consistência
    Os dados estão em um estado consistente quando uma transação começa e quando termina.
    Por exemplo, em um aplicativo que transfere fundos de uma conta para outra, a propriedade de consistência garante 
    que o valor total dos fundos 
    em ambas as contas seja o mesmo no início e no final de cada transação.

    Isolamento
    O estado intermediário de uma transação é invisível para outras transações. Como resultado, as transações executadas simultaneamente parecem ser serializadas.
    Por exemplo, em um aplicativo que transfere fundos de uma conta para outra, a propriedade de isolamento garante que 
    outra transação veja os fundos transferidos em uma conta ou na outra, mas não em ambas, nem em nenhuma.

    Durabilidade
    Após a conclusão bem-sucedida de uma transação, as alterações nos dados persistem e não são desfeitas, mesmo em caso 
    de falha do sistema.
    Por exemplo, em um aplicativo que transfere fundos de uma conta para outra, a propriedade de durabilidade garante
    que as alterações feitas em cada conta não serão revertidas.


API:

     setAutoCommit(false) -> cada operação isolada que você fizer ela vai ser confirmada automaticamente se isso estiver
    valendo verdadeiro . Se você colocar como falso cada operação não está confirmada, só quando eu confirmar é que
    você fecha a transação.
     commit() -> confirmar a transação
     rollback() - > desfazer o que foi feito, volta o banco de dados no estado antes da alteração garantindo a integridade.




