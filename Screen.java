/**
 * Prints things out to the screen, when needed
 * Printing to the screen:
 *  System.out.println("hello");
 */
public class Screen {
    private final PhoneModel model;

    public Screen(PhoneModel model) {
        this.model = model;

        // Observador 1: imprime o dígito mais recente, sozinho na linha,
        // como no exemplo de saída do enunciado
        model.addDigitObserver(new PhoneModel.PhoneObserver() {
            @Override
            public void onDigitAdded(int digit) {
                System.out.println(digit);
            }
        });

        // Observador 2: só fala quando o número completa 12 dígitos
        // (o número do enunciado, 081999887766, tem 12 dígitos, e é essa
        // a quantidade de teclas que o Main aperta)
        model.addDigitObserver(new PhoneModel.PhoneObserver() {
            @Override
            public void onDigitAdded(int digit) {
                if (model.getDigits().size() == 12) {
                    StringBuilder numero = new StringBuilder();
                    for (int d : model.getDigits()) {
                        numero.append(d);
                    }
                    System.out.println("Agora discando " + numero + "...");
                }
            }
        });
    }
}
