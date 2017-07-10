# muxixonga

* Explicações gerais

Atualmente não trabalho com MVP mas sempre gostei da aplicabilidade deste por conta dos testes, código com qualidade só com testes. 
Era necessário então com o pouco tempo focar ou na integração com C ou no desenvolvimento de MVP que se alinha com testes, portanto, optei pelo desenvolvimento do MVP. 

Pensando nisso tive que tirar estes dias em que não trabalho (fim_de_semana) para estudar e aplicar o conceito de MVP com testes.

* Do MVP
- Classes providers utilizando Retrofit e Picasso com cache.
- As activities possuem todos métodos que são chamados do presenter mesmo quando estão em transição de estado (outBundle) ou de telas.
1. Por falar em transição de telas, optei por uso de Parceler (já trabalho há algum tempo), a implementação pode ser chata mas se ponderarmos entre Serializable e Parcelable a velocidade em que é trafegado os dados com o uso Parceler é absurdamente maior que Serializable.
2. Tentei fazer com que todas as Activities possuam métodos que sejam todos testáveis mesmo com o uso de Parcelables.
- Testes Unitários com: JUnit, Mockito e Powermock.
- Testes de UI com: Espresso
- Classes de injeções feitas sem o uso de Dagger (tenho que dar uma lida sobre).
- Tentei fazer a integração do C mas não deu muito certo a ideia de tratar a recursividade para cuidar de uma Thread.

** adicionar mais conteúdo
