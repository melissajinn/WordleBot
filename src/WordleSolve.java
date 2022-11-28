public interface WordleSolve {

    String name();
    boolean won();
    String nextGuess();

    void useGuess(Guess guess);

}
