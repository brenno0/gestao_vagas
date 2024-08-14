# Gestão de Vagas

#### Este projeto foi desenvolvido com o objetivo de criar um sistema de gestão de vagas utilizando Java e Spring Boot. O programa está em fase inicial e possuirá ainda as funções de candidatura em uma vaga, aprovação e reprovação, edição de vagas e notificações em tempo real.


***Versão do projeto***: 1.0.0 | 
***Linguagem do projeto***: Java 17 | 
***Versão spring***: 3.3.1 | 
***Banco de dados***: PostGreSQL | 
***Versão Docker***: 3.8 | 
##
**Comandos básicos - Execução do projeto**
```

docker-compose init

docker-compose up -d

mvn spring-boot:run

docker-compose stop

```
##

#### Funcionalidades principais


##### Cadastro de vagas 
> Em um primeiro momento o projeto permite apenas a criação de vagas, tendo também a adição e exclusão em versões futuras

##### Gestão de Candidatos e empresas
> Permite a criação e autentiação de usuários e empresas, ambos possuem funcionalidades e permissões distintas que são controladas por suas respectivas *ROLES*

##### Listagem do perfil do candidato
> Permite que o usuário tenha todas as informações de seu perfil listadas (futuras adições permitiram que o mesmo aconteça com empresas)

##### Sistema de notificações (A fazer)
> Sistema de notificações com Websockets que permitirão a realização do envio de notificações em tempo real a medida que o processo do candidato for avançando


##

##### Feitos e aprendizados no projeto 

>O gerenciamento dos repositories foi realizado com a lib JPA repository tornando possível a utilização de inúmeras funcionalidades, dentre elas a grande facilitação na criação de métodos que se comuniquem com o banco de dados.

>A criptografia dos dados sensíveis para a autenticação do usuário foi realizada através da biblioteca spring security com o password encoder

>A gestão do token de acesso do usuário tanto na criação do token quanto em sua interceptação nas requisições foi realizada através da biblioteca JWT

>Foi realizada uma filtragem em cada requisição para que a interceptação garantisse duas coisas, a primeira é que o usuário possuia seu respectivo token, e a segunda é que o token possuia emsuas informações o seu tempo de duração e suas *ROLES* de usuário

>Também foi necessária a criação de DTOS e builders para que tanto os parâmetros recebidos na **request** quanto devolvidos na **response** fossem devidamente controlados

##

##### Principais desafios

1. Dominio da criptografia de senha e descobrimento do uso do algoritmo HMAC256
2. Criação de filters que interceptariam as requisições
3. Controle de usuários e diferenciação entre empresas e candidatos

##
#### Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request com melhorias ou novas funcionalidades.

