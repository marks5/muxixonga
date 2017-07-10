# muxixonga

* Explicações gerais

Atualmente não trabalho com MVP mas sempre gostei da aplicabilidade deste por conta dos testes, código com qualidade só com testes. 
Era necessário então com o pouco tempo focar ou na integração com C ou no desenvolvimento de MVP que se alinha com testes, portanto, optei pelo desenvolvimento do MVP. 

Pensando nisso tive que tirar estes dias em que não trabalho (fim_de_semana) para estudar e aplicar o conceito de MVP com testes.

# Do MVP
- Classes providers utilizando Retrofit e Picasso com cache.

- As activities possuem todos métodos que são chamados do presenter mesmo quando estão em transição de estado (outBundle) ou de telas.
1. Por falar em transição de telas, optei por uso de Parceler (já trabalho há algum tempo), a implementação pode ser chata mas se ponderarmos entre Serializable e Parcelable a velocidade em que é trafegado os dados com o uso Parceler é absurdamente maior que Serializable.
2. Tentei fazer com que todas as Activities possuam métodos que sejam todos testáveis mesmo com o uso de Parcelables.

- Testes Unitários com: JUnit, Mockito e Powermock.

- Testes de UI com: Espresso

- Classes de injeções feitas sem o uso de Dagger (tenho que dar uma lida sobre).

- Tentei fazer a integração do C mas não deu muito certo a ideia de tratar a recursividade para cuidar de uma Thread.

# Sobre testes
- Criei flavors para testes no início do projeto entretanto, o PC já não aguentava mais com variações de 40 ~~ 90 minutos o build.

- Voltei atrás e deixei sem flavors mesmo:
  * Para testes unitários:
  1. Abra a aba Terminal e rode o seguinte comando: gradlew test ou ./gradlew test. Por alguma razão o JUnit tem alguma incompatibilidade com Parcelable.

  * Para testes de UI:
  1. Primeiro, altere a classe de Injection para InjectionMock em ListFruitsActivity:
    listFruitsPresenter = new ListFruitsPresenter(Injection.provideListFruits(), Schedulers.io(), AndroidSchedulers.mainThread());
  2. Vá até a pasta androidTestMock dentro de androidTests, clique com botão direito em ListFruitActivityTest e dê run neste arquivo.

** adicionar mais conteúdo
