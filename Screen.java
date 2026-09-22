/**
 * Prints things out to the screen, when needed
 * Printing to the screen:
 *  System.out.println("hello");
 */
public class Screen {
    private final PhoneModel model;

    public Screen(PhoneModel model) {
        this.model = model;

        // Observador 1: ecoa cada dígito assim que ele chega
        model.addDigitObserver(new PhoneModel.PhoneObserver() {
            @Override
            public void onDigitAdded(int digit) {
                System.out.println("Digitou: " + digit);
            }
        });

        // Observador 2: só fala quando o número completa 11 dígitos
        model.addDigitObserver(new PhoneModel.PhoneObserver() {
            @Override
            public void onDigitAdded(int digit) {
                if (model.getDigits().size() == 11) {
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
