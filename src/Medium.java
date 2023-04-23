import java.util.ArrayList;
import java.util.List;
public class Medium extends Computer {
    private List guesses = new ArrayList();

    @Override
    public void guess(Player player, Player answer) {
        int[] tempGuess = this.makeRandomNum();
        if (! guesses.contains(tempGuess)){
            guesses.add(tempGuess.clone());
            this.guess = tempGuess;
        }
        else {
            this.guess(player, answer);
        }

        this.checkAnswer(player.guess, answer.secretCode);
    }
}

