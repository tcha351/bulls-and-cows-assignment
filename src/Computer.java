public abstract class Computer extends Player {
    private String level;

    public Computer() {
        this.setName("Computer");
    }

    public int[] makeRandomNum() {
        int[] randomNumbers = new int[codeLength];
        for (int i = 0; i < codeLength; i++) {
            randomNumbers[i] = (int) (Math.random() * 10);
            for (int j = 0; j < i; j++) {
                if (randomNumbers[i] == randomNumbers[j]) {
                    i--;
                    break;
                }
            }
        }
        return randomNumbers;
    }

    @Override
    public void setSecretCode() {
        this.secretCode = makeRandomNum();
    }

    public void setLevel(String input) {
        this.level = input;
    }

    public String getLevel() {
        return this.level;
    }

}
