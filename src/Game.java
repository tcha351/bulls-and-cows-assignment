import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private int numberOfTurns = 7;
    private String winner = null;
    private int level;
    private String mode;
    private boolean saveResult;
    List<String> outputData = new ArrayList();

    public Game() {
        System.out.println("Let's play Bulls and Cows!");
        outputData.add("Game result");
    }

    public void play(Human human, Computer computer) {
        for (int i = 1; i <= this.numberOfTurns; i++){
            if (this.winner != null){
                break;
            }
            outputData.add("Turn " + Integer.toString(i) + ": ");
            System.out.println("Turn " + Integer.toString(i) + ": ");
            if (this.getMode().equals("manual") || (this.getMode().equals("automatic") && human.codeFromFile.size() == 0)){
                System.out.println("Your guess: ");
            }
            human.guess(human, computer);
            this.printResult(human);
            this.printWinner(human, i);
            if (this.winner == null) {
                computer.guess(computer, human);
                this.printResult(computer);
                this.printWinner(computer, i);
            }
        }
    }

    public void printResult(Player player){
        String output = player.getName() + "'s guess is " + player.toStringForCode(player.guess) + " with bulls & cows of " + player.bulls + " bulls & " + player.cows + " cows.";
        System.out.println(output);
        outputData.add(output);
    }

    public void printWinner(Player player, int turnsCount){
        if (player.bulls == player.codeLength){
            outputData.add(" ");
            String output = player.getName() + " wins!";
            System.out.println(output);
            outputData.add(output);
            this.winner = player.getName();
            return;
        }
        if (turnsCount == this.numberOfTurns && player.getName().equals("Computer")){
            outputData.add(" ");
            String output = "Result: draw.";
            System.out.println(output);
            outputData.add(output);
            return;
        }
    }

    public void setLevel() {
        String input;
        boolean isValid = false;
        while (!isValid) {
            System.out.println("Please type down Easy/Medium/Hard");
            input = Keyboard.readInput();
            input = input.toLowerCase();
            switch (input) {
                case "easy":
                    this.level = 1;
                    isValid = true;
                    break;
                case "medium":
                    this.level = 2;
                    isValid = true;
                    break;
                case "3":
                case "hard":
                    this.level = 3;
                    isValid = true;
                    break;
                default:
                    System.out.println("Invalid input, try again!");
                    break;
            }
        }
    }

    public int getLevel() {
        return this.level;
    }


    public void setMode(){
        String input;
        boolean isValid = false;
        while (!isValid) {
            System.out.println("Please type down automatic/manual");
            input = Keyboard.readInput();
            input = input.toLowerCase();
            switch (input) {
                case "automatic":
                    this.mode = "automatic";
                    isValid = true;
                    break;
                case "manual":
                    this.mode = "manual";
                    isValid = true;
                    break;
                default:
                    System.out.println("Invalid input, try again!");
                    break;
            }
        }
    }
    public String getMode(){
        return this.mode;
    }

    public void readFile(Player user){
        while (true){
            System.out.println("Enter file name: ");
            String filename = Keyboard.readInput();
            File inputFile = new File(filename);
            try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))){
                String line = null;
                while ((line = reader.readLine()) != null){
                    user.codeFromFile.addLast(line);
                }
            }
            catch (IOException e){
                System.out.println("Invalid file. Please enter again.");
                continue;
            }
            break;
        }
    }

    public void setSaveResult(){
        String input;
        boolean isValid = false;
        while (!isValid) {
            System.out.println("Yes/No");
            input = Keyboard.readInput();
            input = input.toLowerCase();
            switch (input) {
                case "yes":
                    this.saveResult = true;
                    isValid = true;
                    break;
                case "no":
                    this.saveResult = false;
                    isValid = true;
                    break;
                default:
                    System.out.println("Invalid input, try again!");
                    break;
            }
        }
    }

    public boolean getSaveResult(){
        return this.saveResult;
    }

    public void saveFile(){
        while (true){
            System.out.println("Please enter your file name:");
            String filename = Keyboard.readInput();
            File outputFile = new File(filename);
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                for (int i = 0; i < outputData.size(); i++){
                    writer.write(outputData.get(i));
                    writer.newLine();
                }
            }
            catch (IOException e){
                System.out.println("Error " + e.getMessage());
                continue;
            }
            break;
        }
    }

}
