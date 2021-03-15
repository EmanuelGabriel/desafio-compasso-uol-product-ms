# desafio-compasso-uol-product-ms

## Desafio proposto por Compasso UOL

Criando microserviço de catálogo de produtos.


## product-ms
Neste microserviço deve ser possível criar, alterar, visualizar e excluir um determinado produto, além de visualizar a lista de produtos atuais disponíveis. Também deve ser possível realizar a busca de produtos filtrando por name, description e price.



### Recursos/endpoints do microserviço "desafio-compasso-uol-product-ms"

### Modelo que devem ser disponibilizados os endpoints

![image](https://user-images.githubusercontent.com/16214525/111205439-ffa47c80-85a5-11eb-90c5-c1f1722b4c84.png)


## Desenvolvimento

Para iniciar o projeto, é necessário clonar o projeto do GitHub num diretório de sua preferência:

```shell
cd "diretorio de sua preferencia"
git clone https://github.com/EmanuelGabriel/desafio-compasso-uol-product-ms.git
```

### Execução/Construção

Para construir o projeto com o Maven, executar os comando abaixo:

```shell
mvn clean install
```

O comando irá baixar todas as dependências do projeto e criar um diretório *target* com os artefatos construídos, que incluem o arquivo jar do projeto. Além disso, serão executados os testes unitários (obs.: ainda em fase de construção), e se algum falhar, o Maven exibirá essa informação no console.

Para executar o projeto execute o seguinte comando:
```shell
mvn clean package spring-boot:run
```


#### Imagens:

Recursos/endpoints com passagem de parâmetros

![image](https://user-images.githubusercontent.com/16214525/111205086-9b81b880-85a5-11eb-83bc-ae3bc5d71e39.png)


### 
Esta API de catálogo de produtos usa uma ferramenta de migração de banco de dados de código aberto chamado FLYWAY. 

![image](https://user-images.githubusercontent.com/16214525/111208096-1a2c2500-85a9-11eb-9f2a-b23e2d8432c5.png)

 Saiba mais sobre em https://flywaydb.org/
 
O flyway é uma excelente ferramenta para versionamento de scripts de banco de dados. Por padrão, ele utiliza a tabela schema_version para fazer o gerenciamento dos scripts executados, no entanto, podemos precisar utilizar mais que uma estrutura de versionamento por exemplo: pode ser preciso versionar os scripts por cliente ou separar scripts de estrutura e dados.

![image](https://user-images.githubusercontent.com/16214525/111208273-4cd61d80-85a9-11eb-9c73-4915fa668459.png)



<h3>Ferramentas e tecnologias utilizadas</h3>

:ballot_box_with_check: Java 11

:ballot_box_with_check: Spring Boot

:ballot_box_with_check: Spring Data JPA

:ballot_box_with_check: Swagger

:ballot_box_with_check: Banco de dados H2 (in-memory)

:ballot_box_with_check: Modelo arquitetural REST

:ballot_box_with_check: Flyway - controle de versionamento da base de dados


