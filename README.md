# Telephone - Observer

## O problema

O `KeyPad` simula a digitação de um número de telefone, dígito por dígito,
chamando `PhoneModel.addDigit(int)`. A `Screen` precisa reagir a cada dígito
digitado (para ecoar na tela) e também precisa saber quando o número atingiu
11 dígitos (para anunciar que a ligação está sendo discada), sem que o
`PhoneModel` precise conhecer a `Screen` nem saber o que fazer com a tela.

O padrão Observer resolve isso: o `PhoneModel` apenas avisa "um dígito foi
adicionado" para quem estiver inscrito, e quem estiver inscrito decide o que
fazer com essa informação.

## Quem é Subject e quem é Observer

- **Subject**: `PhoneModel`. Mantém a lista de observadores (`observers`),
  oferece `addDigitObserver` para inscrição e dispara `notifyAllObservers`
  toda vez que `addDigit` é chamado.
- **Observer**: a interface `PhoneModel.PhoneObserver` (aninhada no próprio
  Subject, para o `PhoneModel` não depender de `Screen`). A `Screen` registra
  duas implementações anônimas dela no seu construtor:
  1. uma que imprime `"Digitou: X"` a cada dígito;
  2. uma que consulta `model.getDigits()` e, quando o tamanho chega a 11,
     imprime `"Agora discando XXXXXXXXXXX..."`.

## Como compilar e rodar

```
javac -d /tmp/out *.java
cd /tmp/out
java Main
```

## Saída de exemplo

```
Pressing: 4
Digitou: 4
Pressing: 6
Digitou: 6
Pressing: 3
Digitou: 3
Pressing: 6
Digitou: 6
Pressing: 6
Digitou: 6
Pressing: 2
Digitou: 2
Pressing: 3
Digitou: 3
Pressing: 0
Digitou: 0
Pressing: 0
Digitou: 0
Pressing: 11
Digitou: 11
Pressing: 2
Digitou: 2
Agora discando 463662300112...
Pressing: 4
Digitou: 4
```

(os dígitos são gerados aleatoriamente pelo `KeyPad`, então cada execução
produz uma sequência diferente; o anúncio "Agora discando ..." sempre
aparece assim que o 11º dígito é digitado).
