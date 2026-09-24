# Telephone - Observer

## O problema

O `KeyPad` simula a digitação de um número de telefone, dígito por dígito,
chamando `PhoneModel.addDigit(int)`. A `Screen` precisa reagir a cada dígito
digitado (para ecoar na tela) e também precisa saber quando o número atingiu
12 dígitos (para anunciar que a ligação está sendo discada), sem que o
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
  1. uma que imprime o dígito mais recente, sozinho na linha;
  2. uma que consulta `model.getDigits()` e, quando o tamanho chega a 12,
     imprime `"Agora discando XXXXXXXXXXXX..."`.

## Como compilar e rodar

```
javac -d /tmp/out *.java
cd /tmp/out
java Main
```

## Saída de exemplo

```
Pressing: 0
0
Pressing: 8
8
Pressing: 1
1
Pressing: 9
9
Pressing: 9
9
Pressing: 9
9
Pressing: 8
8
Pressing: 8
8
Pressing: 7
7
Pressing: 7
7
Pressing: 6
6
Pressing: 6
6
Agora discando 081999887766...
```

Os dígitos são gerados aleatoriamente pelo `KeyPad`, então cada execução
produz uma sequência diferente. O anúncio "Agora discando ..." é sempre a
última linha: aparece quando o 12º dígito é digitado, com o número completo.

O `"Pressing: X"` em inglês e os eventuais "dígitos" 10 e 11 vêm do `KeyPad`
do código inicial do professor (`rnd.nextInt(12)`).
