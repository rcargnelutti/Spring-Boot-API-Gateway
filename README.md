# API com Spring Boot

- Arquivo **pom.xml** -> dependências do projeto;

- Arquivo src/main/resources/**application.properties** -> configurar acesso ao banco de dados;

- Criar o banco de dados “services” no Postgres antes de rodar a aplicação;

- Arquivo src/main/java/com/example/springboot/gateway/**ServiceController.java**
  - saveService
  - getOneService
  - getAllServices
  - updateService
  - deleteService

- Arquivo src/main/java/com/example/springboot/gateway/**ServiceModel.java**
  - private UUID idService;
  - private String name;
  - private String enabled;
  - private String port;
  - private String host;
  - private String path;
  - private String protocol;

- Arquivo src/main/java/com/example/springboot/gateway/**ServiceRecordDto.java**
  - ServiceRecordDto

- Arquivo src/main/java/com/example/springboot/gateway/**ServiceRepository.java**
  - ServiceRepository
