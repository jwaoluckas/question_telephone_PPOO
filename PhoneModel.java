import java.util.ArrayList;
import java.util.List;

/**
 * Store a phone number, digit-by-digit
 */
public class PhoneModel {
    private List<Integer> digits = new ArrayList<>();
    private final List<PhoneObserver> observers = new ArrayList<>();

    public interface PhoneObserver {
        void onDigitAdded(int digit);
    }

    public void addDigit(int newDigit) {
        digits.add(newDigit);
        notifyAllObservers(newDigit);
    }

    public List<Integer> getDigits() {
        return digits;
    }

    public void addDigitObserver(PhoneObserver observer) {
        observers.add(observer);
    }

    private void notifyAllObservers(int newDigit) {
        for (PhoneObserver obs : observers) {
            obs.onDigitAdded(newDigit);
        }
    }
}
