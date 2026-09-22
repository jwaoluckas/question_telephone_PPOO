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
    }
}
