package bowling.score;

import java.util.stream.IntStream;

public class Game {

    private final Rolls rolls = new Rolls();

    public void roll(int knockDownPins) {
        rolls.append(knockDownPins);
    }

    public int score() {
        return rolls.stream().sum();
    }

    private class Rolls {
        private final int[] rolls = new int[21];
        private int nextRollIndex = 0;

        void append(int knockDownPins) {
            rolls[nextRollIndex++] = knockDownPins;
        }

        IntStream stream() {
            return IntStream.of(rolls);
        }
    }
}
