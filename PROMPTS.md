# Documentação do uso de IA — Questão 2 (Observer)

Conforme o enunciado, a IA foi usada para pedir **um passo-a-passo**, não a
solução pronta. Abaixo estão os prompts utilizados, o que veio de resposta e os
ajustes feitos por cima.

---

## Prompt 1 — estudo dos conteúdos da disciplina

Prompt usado uma vez, no início da lista, para revisar os padrões antes de
começar a resolver:

> Use a extensão Claude in Chrome para fazer o seguinte:
> - Entre no Classroom e veja todos os conteúdos relacionados a disciplina
>   PPOO, assim você poderá explicar melhor cada padrão que o professor ensinou
>   até agora.
> - Ainda no Classroom, veja os conteúdos da turma arquivada de Programação
>   Orientada a Objetos (POO), assim você poderá me relembrar todos os conceitos
>   de OO. Não nego, estou enferrujado!
> - Não faça nenhuma alteração, envio de mensagem ou algo que possa me
>   'prejudicar' nas turmas, apenas visualize os conteúdos e me ensine.

---

## Prompt 2 — entender o código inicial

> Me explique a questão do telefone:
> - Me mostre o código já existente e explique ele parte por parte (Explique
>   através de comentários o que cada linha / comando faz).
> - Me diga qual o padrão já aplicado (se já tiver um) e explique esse padrão.
> - Se for para aplicar outro padrão, informe qual e explique-o também.

**O que veio:** a explicação classe a classe do código inicial (`PhoneModel`,
`KeyPad`, `Screen`, `Main`) e a constatação de que ali ainda não havia padrão
nenhum: o `KeyPad` empurrava o dígito para o modelo e ninguém era avisado. O
padrão a aplicar era o Observer.

---

## Prompt 3 — o roteiro da solução

> Estou implementando o padrão Observer num exercício de telefone em Java.
> Tenho um PhoneModel que guarda uma lista de dígitos discados (método
> addDigit), um KeyPad que chama addDigit a cada tecla simulada, e uma Screen
> que só tem uma referência ao model mas não faz nada ainda. Me ajude com o
> seguinte:
> - Preciso que a Screen crie dois observadores: um que imprime o dígito assim
>   que ele é adicionado, e outro que só imprime "Agora discando XXXXXXXXXXX..."
>   quando o número atingir 11 dígitos.
> - O PhoneModel não pode conhecer a Screen nem imprimir nada na tela, só a UI
>   pode imprimir.
> - Antes de me dar código pronto, me explica em passos o raciocínio de como
>   estruturar isso (a interface do observador, onde entra o registro dos
>   observadores, como o Subject notifica, e como cada observador decide o que
>   fazer), pra eu ir implementando e entendendo cada parte.

**Etapas sugeridas:**

1. Criar a interface do observador, com um método que recebe o dígito novo.
2. Guardar no modelo a lista de observadores e um método para registrar.
3. Disparar a notificação dentro do `addDigit`, depois de guardar o dígito.
4. Criar os dois observadores na `Screen`, que é a única que pode imprimir.

---

## Ajustes feitos sobre o que a IA respondeu

### Ajuste 1 — a interface do observador ficou dentro do `PhoneModel`

A sugestão era criar a interface num arquivo separado, solto no projeto. Ela
ficou como interface interna do `PhoneModel` (`PhoneModel.PhoneObserver`).

**Por que isso melhora:** quem define o evento é o modelo, então o contrato de
quem escuta pertence a ele. Isso também mantém a mesma convenção que o professor
usou no código inicial do `websearch`, onde o `QueryObserver` é interface
interna do `WebSearchModel` — quem lê os dois exercícios encontra o mesmo
desenho.

### Ajuste 2 — o segundo observador decide sozinho quando falar

A sugestão era o modelo avisar que o número estava completo (um método do tipo
`isComplete()`, ou um evento separado de "número pronto"). Não foi seguida.

**Por que isso melhora:** "estar completo" é uma regra de apresentação, não do
telefone. Se o modelo soubesse disso, ele carregaria uma regra que é da UI, e
mudar o tamanho do número obrigaria a mexer no modelo. Do jeito que ficou, o
modelo só anuncia "entrou um dígito"; o segundo observador consulta
`model.getDigits()` e decide por conta própria quando falar.

### Ajuste 3 — o número é montado na hora de imprimir

Guardar o número já formatado como String dentro do modelo foi descartado:
formatação é assunto da UI. O observador percorre `getDigits()` e monta a String
na hora, então o modelo guarda só o dado bruto.

**Resultado:** o `PhoneModel` não tem nenhum `System.out` e não cita a `Screen`
em lugar nenhum — as duas restrições do enunciado.

### Ajuste 4 — a saída foi alinhada com o exemplo do enunciado

Conferindo a saída com o exemplo do PDF, apareceram duas diferenças que vinham
do próprio prompt:

- **12 dígitos, não 11.** O prompt pedia a discagem "quando o número atingir 11
  dígitos", mas o número do exemplo (`081999887766`) tem 12, e o `Main` aperta
  12 teclas (`NUM_DIGITS = 12`). Com 11, o "Agora discando" saía antes da última
  tecla, o número discado ficava sem o último dígito e ainda aparecia um
  `Pressing` depois da discagem.
- **Só o dígito.** O enunciado pede que o primeiro observador imprima "o dígito
  mais recente", e o exemplo mostra o número sozinho na linha. O `"Digitou: "`
  era um texto a mais que a IA sugeriu para deixar a saída "explicada".

**Por que isso melhora:** a saída passa a ter a mesma forma do exemplo do
professor, e a discagem vira a última linha, com o número completo. A estrutura
do Observer não mudou: só o que cada observador imprime e o tamanho que o
segundo observador espera.

---

## Mapa: etapa → commit

| Etapa / ajuste                              | Commit                                                            |
|---------------------------------------------|-------------------------------------------------------------------|
| Código inicial do professor                  | `codigo inicial do professor`                                     |
| Etapa 1 (**Ajuste 1**) — interface           | `adiciona a interface PhoneObserver dentro de PhoneModel`         |
| Etapas 2 e 3 — registro e notificação        | `adiciona lista de observadores e notifyAllObservers no PhoneModel` |
| Etapa 4 — primeiro observador                | `Screen registra observador que ecoa cada digito`                 |
| Etapa 4 (**Ajustes 2 e 3**) — segundo obs.   | `Screen registra observador que anuncia a discagem aos 11 digitos` |
| Documentação da solução                      | `adiciona README com explicacao do Observer`                      |
| **Ajuste 4** — saída igual à do enunciado    | `ajusta a saida da Screen ao exemplo do enunciado`                |
