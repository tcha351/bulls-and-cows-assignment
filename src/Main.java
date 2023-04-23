public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    public void start() {
        Game game = new Game();

        Computer computer = null;
        System.out.println("Pick your level of choice!");
        game.setLevel();
        switch (game.getLevel()) {
            case 1:
                computer = new Easy();
                computer.setLevel("Easy");
                break;
            case 2:
                computer = new Medium();
                computer.setLevel("Medium");
                break;
            case 3:
                computer = new Hard();
                computer.setLevel("Hard");
                break;
            default:
                break;
        }
        System.out.println("Level: " + computer.getLevel());

        Human human = new Human();

        System.out.println("Enter your code here = ");
        human.setSecretCode();
        System.out.println("Your code is " + human.toStringForCode(human.secretCode));
        game.outputData.add("Your code is " + human.toStringForCode(human.secretCode));

        computer.setSecretCode();
        game.outputData.add("Computer's code is " + computer.toStringForCode(computer.secretCode));

        System.out.println("Do you want to play in automatic or manual?");
        game.setMode();

        if (game.getMode() == "automatic"){
            game.readFile(human);
        }

        game.play(human, computer);

        System.out.println("Save result?");
        game.setSaveResult();
        if (game.getSaveResult()){
            game.saveFile();
        }

    }
}
