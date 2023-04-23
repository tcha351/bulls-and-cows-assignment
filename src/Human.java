public class Human extends Player {

    public Human() {
        this.setName("Human");
    }

    public void setCode(int[] target){
        String input;
        while (true) {
            if (this.codeFromFile.size() > 0){
                input = this.codeFromFile.removeFirst();
            }
            else {
                input = Keyboard.readInput();
            }
            if (this.checkInput(input)) {
                break;
            }
        }
        for(int i = 0; i < codeLength; i++){
            target[i] = Integer.parseInt(input.charAt(i) + "");
        }
    }

    @Override
    public void guess(Player player, Player answer) {
        this.setCode(this.guess);
        this.checkAnswer(player.guess, answer.secretCode);
    }

    @Override
    public void setSecretCode() {
        this.setCode(this.secretCode);
    }
}

