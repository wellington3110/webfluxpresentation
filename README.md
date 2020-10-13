# Webflux presentation

Esse repositório contém todo o código utilizado na apresentação de [Introdução à Spring Web Flux][].

## Estrutura

### [Aplicação de exemplo](./src/main/kotlin/br/com/wellington/webfluxpresentation)

Contém o código exemplificando a utilização do Spring Web Flux, comunicando-se com o banco H2 utilizando um driver
reativo, que permite realizar Non Blocking IO.

#### Tecnologias utilizadas

- [Kotlin](https://kotlinlang.org/)
- [Gradle](https://gradle.org/)
- [Spring Boot](https://projects.spring.io/spring-boot/)
- [Spring WebFlux](https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/web-reactive.html)
- [Spring data](https://spring.io/projects/spring-data)
- [R2DBC](https://r2dbc.io/)
- [H2](https://www.h2database.com/)
- [Reactor](https://projectreactor.io/docs/core/release/reference/)

#### Como executar

```bash
./gradlew bootRun
```

---

### [Exemplo de Reactive Programming](./src/main/resources/scripts/reactiveprogrammingexample)

Contém a implementação de uma aplicação de console utilizando o padrão de projeto [Pub/Sub](https://en.wikipedia.org/wiki/Publish%E2%80%93subscribe_pattern)
visando exemplificar a programação reativa.

#### Como executar

```bash
kotlinc -script ./src/main/resources/scripts/reactiveprogrammingexample/PubSubExample.kts
```

**OBS:** Você necessita estar no [root](.) do projeto para executar esse comando.

---

### [Event Loop](./src/main/resources/scripts/eventloop)

Contém a implementação "ingênua" de um Event loop.

---

### [Exemplo do Reactor](./src/test/kotlin/br/com/wellington/webfluxpresentation/ReactorExample.kt)

Contém alguns exemplos da utilização do Reactor. Cada teste representa um exemplo, e a escolha por escrever os exemplos dessa forma foi
para ficar mais fácil executar cada exemplo individualmente, e ainda mantê-los no mesmo arquivo. 