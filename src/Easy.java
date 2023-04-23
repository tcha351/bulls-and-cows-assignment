public class Easy extends Computer{
    @Override
    public void guess(Player player, Player answer) {
        this.guess = this.makeRandomNum();
        this.checkAnswer(player.guess, answer.secretCode);
    }
}