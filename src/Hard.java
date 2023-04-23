import ExceptionPackage.DuplicateDataException;

import java.util.ArrayList;
import java.util.List;

public class Hard extends Computer {
    private List<int[]> codeCandidates = new ArrayList<int[]>();
    int[] placeholderGuess = new int[codeLength];

    public Hard() {
        String s;
        for (int i = 123; i <= 9876; i++) {
            s = Integer.toString(i);
            if (s.length() == 3) {
                s = "0" + s;
            }
            try {
                for (int j = 0; j < codeLength; j++) {
                    int value = Integer.parseInt(s.charAt(j) + "");
                    for (int k = 0; k < j; k++) {
                        if (value == placeholderGuess[k]) {
                            throw new DuplicateDataException();
                        }
                    }
                    placeholderGuess[j] = value;
                }
                codeCandidates.add(placeholderGuess.clone());
            } catch (DuplicateDataException e) {
                continue;
            }
        }

    }

    @Override
    public void guess(Player player, Player answer) {
        int randomIndex = (int)(Math.random() * this.codeCandidates.size());
        for (int i = 0; i < codeLength; i++){
            this.guess[i] = this.codeCandidates.get(randomIndex)[i];
        }
        this.checkAnswer(player.guess, answer.secretCode);
        List<int[]> tempPossibleCodes = new ArrayList<int[]>();
        int tempBulls = this.bulls;
        int tempCows = this.cows;
        for (int i = 0; i < this.codeCandidates.size(); i++){
            this.checkAnswer(this.codeCandidates.get(i), player.guess);
            if (tempBulls != this.bulls || tempCows != this.cows){
                tempPossibleCodes.add(this.codeCandidates.get(i));
            }
        }
        this.bulls = tempBulls;
        this.cows = tempCows;
        for (int i = 0; i < tempPossibleCodes.size(); i++){
            this.codeCandidates.remove(tempPossibleCodes.get(i));
        }
    }
}
