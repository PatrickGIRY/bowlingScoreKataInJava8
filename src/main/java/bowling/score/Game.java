package bowling.score;

import java.util.stream.IntStream;

public class Game {

    private final Rolls rolls = new Rolls();

    public void roll(int knockDownPins) {
        rolls.append(knockDownPins);
    }

    public int score() {
        return IntStream.range(0, 20).reduce(0, (score, rollIndex) -> {
            int scoreRoll = rolls.get(rollIndex);
            return score + score(scoreRoll, rollIndex) - spareDiff(scoreRoll, rollIndex) + bonus(scoreRoll, rollIndex);
        });
    }

    private int score(int scoreRoll, int rollIndex) {
        return isSpare(scoreRoll, rollIndex) ? 10 : scoreRoll;
    }

    private int bonus(int scoreRoll, int rollIndex) {
        return isSpare(scoreRoll, rollIndex) ? rolls.next(rollIndex + 2) : 0;
    }

    private int spareDiff(int scoreRoll, int rollIndex) {
        return isSpare(scoreRoll, rollIndex) ? rolls.next(rollIndex) : 0;
    }

    private boolean isSpare(int scoreRoll, int rollIndex) {
        return scoreRoll + rolls.next(rollIndex) == 10;
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

        int get(int rollIndex) {
            return rolls[rollIndex];
        }

        int next(int rollIndex) {
            return rollIndex < (rolls.length - 1) ? rolls[rollIndex] : 0;
        }
    }
}
