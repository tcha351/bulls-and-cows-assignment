import ExceptionPackage.DuplicateDataException;

import java.util.*;

public abstract class Player {
    static final int codeLength = 4;
    protected int bulls, cows;
    protected int[] secretCode = new int[codeLength];
    protected int[] guess = new int[codeLength];
    private String name;
    Deque<String> codeFromFile = new ArrayDeque<>();

    public boolean checkInput(String codeInput) {
        try {
            if (codeInput.length() > codeLength || codeInput.length() < codeLength) {
                throw new IndexOutOfBoundsException();
            }
            for (int i = 0; i < codeLength; i++) {
                for (int j = 0; j < codeLength; j++) {
                    if (i != j && codeInput.charAt(i) == codeInput.charAt(j)) {
                        throw new DuplicateDataException();
                    }
                }
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Code length invalid, please re-enter.");
            return false;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + "Invalid code, please re-enter");
            return false;
        } catch (DuplicateDataException e) { // MyExceptions
            System.out.println("Code contains duplicate number, please re-enter");
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Invalid code, please re-enter");
            return false;
        }
        return true;
    }

    public abstract void guess(Player player, Player answer);

    public void checkAnswer(int[] guess, int[] secretCode) {
        this.bulls = 0;
        this.cows = 0;
        // check bulls
        for (int i = 0; i < codeLength; i++) {
            if (guess[i] == secretCode[i]) {
                this.bulls++;
            }
        }
        // check cows
        for (int i = 0; i < codeLength; i++) {
            for (int j = 0; j < codeLength; j++) {
                if (i != j && guess[i] == secretCode[j]) {
                    this.cows++;
                }
            }
        }
    }

    public abstract void setSecretCode();


    public String toStringForCode(int[] code) {
        String s = "";
        for (int i = 0; i < codeLength; i++) {
            s = s + code[i];
        }
        return s;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
