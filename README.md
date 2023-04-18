# builders-pay

### Tecnologias utilizadas:
- Java
- Springboot
- Gson
- Database H2

A documentação da API está disponibilizada em um site estático hospedado na AWS, podendo ser acessado por meio do link: http://builders-pay.s3-website-us-east-1.amazonaws.com/

A estrutura da documentação está no arquivo [api_docs.yml](https://github.com/rafaelbcastilhos/builders-pay/blob/main/api_docs.yml)

A aplicação possui cobertura de testes em todos os endpoints, conforme testes de mesa disponibilizado.

Pode ser construído com:
```
mvn clean package
```

E executado com:
```
mvn spring-boot:run
```
