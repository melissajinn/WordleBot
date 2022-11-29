import java.util.Scanner;

public class Wordle {

    public static final int guesses = 6;

    public static void main(String args[]){

        Scanner scan = new Scanner(System.in);
        System.out.println("Goal word known? Y or N");
        String answer = scan.nextLine().toUpperCase();
        boolean known = answer.equals("Y");

        if(known){
            playKnown(solve);
        }else{
            playUnknown(solve);
        }
    }

    public static void playKnown(WordleSolve solve){
        Scanner scan = new Scanner(System.in);
        System.out.println("enter goal word: ");
        String goal = scan.nextLine().toUpperCase();
        System.out.println("Goal: '" + goal + "'");

        for(int i = 0; i < guesses; i ++){
            Guess guess = new Guess(solver.getNextGuessWord(), null);
            guess.populateResultsAgain(goal);

            System.out.println("Guess #" + (i + 1) + ": " + guess.getWord());
            System.out.println("Results: " + Utilities.stringFromResults(getResults()));
            solver.applyGuess(guess);

            if(solver.hasWon()){
                System.out.println("\nSUCCESS in " + (i + 1) + " guesses");
                return;
            }
        }

        System.out.println("\nFAILED");
    }

    public static void playInteractiveWithUnknownGoal(WordleSolve solver){
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter guess results");
        System.out.println(". for gray letter (not in goal word)");
        System.out.println("# for green letter (correct position)");
        System.out.println("? for yellow letter (wrong position)");

        for(int i = 0; i < guesses; i ++){
            String recommended = solver.getNextGuessWord();
            System.out.println("Guess #" + (i + 1));
            System.out.println("Recommended guess: " + recommended);
            System.out.println("Enter your guess: ");
            String guessword = scan.nextLine().toUpperCase();

            System.out.println("Enter the result: ");
            String result = scan.nextLine().toUpperCase();
            Guess guess = new Guess(guessWord, Utilities.resultsFromString(resultsString));

            solver.applyGuess(guess);

            if(solver.hasWon()){
                System.out.println("SUCCESS");
                break;
            }
        }
    }

    public static WordleSolve getSolver(Scanner scan){
        WordleSolve[] solvers = {new NoDupesSolver(),};

        int choice = 0;

        while(true) {
            System.out.println("What solver/strategy should I use?");

            for (int i = 0; i < solvers.length; i++) {
                if (i == 0) {
                    System.out.println("'" + i + ": " + solvers[i].getName() + " (default)");
                } else {
                    System.out.println("'" + i + ": " + solvers[i].getName());
                }
            }

            try {
                String input = in.nextLine();

                if (input.equals("")) {
                    choice = 0;
                    break;
                } else {
                    choice = Integer.parseInt(input);
                    if (choice >= 0 && choice < solvers.length) {
                        break;
                    }
                }
            } catch (Exception e) {}

            System.out.println("Error: Invalid Solver");
        }

        WordleSolve solverChoice = solvers[choice];
        return solverChoice;
    }

}
