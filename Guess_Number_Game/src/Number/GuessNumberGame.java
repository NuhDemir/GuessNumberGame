package Number;

import java.util.Random;
import java.util.Scanner;

// Rastgele sayı üretimini yönetmek için bir sınıf
class RandomNumberGenerator {
    private int lowerBound;
    private int upperBound;
    private int secretNumber;

    public RandomNumberGenerator(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        generateSecretNumber();
    }

    public void generateSecretNumber() {
        Random random = new Random();
        secretNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    public int getSecretNumber() {
        return secretNumber;
    }
}

// Kullanıcı girişlerini yönetmek için bir sınıf
class UserInputHandler {
    private Scanner scanner;

    public UserInputHandler() {
        scanner = new Scanner(System.in);
    }

    public int getUserGuess() {
        return scanner.nextInt();
    }

    public void closeScanner() {
        scanner.close();
    }
}

// Ana oyun mantığını içeren sınıf
public class GuessNumberGame {
    private static final int lowerBound = 1;
    private static final int upperBound = 200;
    private static final int maxAttempts = 10;

    public static void main(String[] args) {
        RandomNumberGenerator rng = new RandomNumberGenerator(lowerBound, upperBound);
        UserInputHandler inputHandler = new UserInputHandler();

        System.out.println("Tahmin Oyununa Hoşgeldiniz!");
        System.out.println("1 ile 200 arasında bir sayı seçtim. Tahmin etmeye çalış!");

        for (int attempts = 1; attempts <= maxAttempts; attempts++) {
            System.out.println("Deneme #" + attempts + ": Tahmininizi girin: ");
            int playerGuess = inputHandler.getUserGuess();

            // Kullanıcının girdiği değeri kontrol ediyoruz
            if (playerGuess < lowerBound || playerGuess > upperBound) {
                System.out.println("Lütfen 1 ile 200 arasında bir sayı girin.");
                continue;
            }

            // Kullanıcının tahminine göre geri bildirim veriyoruz
            if (playerGuess < rng.getSecretNumber()) {
                System.out.println("Çok düşük! Tekrar deneyin.");
            } else if (playerGuess > rng.getSecretNumber()) {
                System.out.println("Çok yüksek! Tekrar deneyin.");
            } else {
                System.out.println("Tebrikler! " + rng.getSecretNumber() + " sayısını " + attempts + " denemede doğru tahmin ettiniz.");
                break;
            }

            // Kullanıcının deneme hakkı bitmişse
            if (attempts == maxAttempts) {
                System.out.println("Üzgünüm, deneme hakkınız bitti. Gizli sayı " + rng.getSecretNumber() + " idi.");
            }
        }

        inputHandler.closeScanner();
    }
}
