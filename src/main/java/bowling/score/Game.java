package bowling.score;

import java.util.stream.IntStream;

public class Game {

    private final Rolls rolls = new Rolls();

    public void roll(int knockDownPins) {
        rolls.append(knockDownPins);
        if (isStrike(knockDownPins) && rolls.notLastFrame()) {
            rolls.append(0);
        }
    }

    public int score() {
        return IntStream.range(0, 20).reduce(0, (score, rollIndex) -> {
            int scoreRoll = rolls.get(rollIndex);
            return score + score(scoreRoll, rollIndex) - spareDiff(scoreRoll, rollIndex) + bonus(scoreRoll, rollIndex);
        });
    }

    private int score(int scoreRoll, int rollIndex) {
        return (isStrike(scoreRoll) || isSpare(scoreRoll, rollIndex)) ? 10 : scoreRoll;
    }

    private int bonus(int scoreRoll, int rollIndex) {
        int next = score(rolls.next(rollIndex + 2), rollIndex + 2);
        if (isStrike(scoreRoll)) {
            int nextOne = rolls.next(rollIndex + 3);
            return next + score(nextOne, rollIndex + 3) - spareDiff(nextOne, rollIndex + 4);
        }
        if (isSpare(scoreRoll, rollIndex)) {
            return next;
        } else {
            return 0;
        }
    }

    private boolean isStrike(int scoreRoll) {
        return scoreRoll == 10;
    }

    private int spareDiff(int scoreRoll, int rollIndex) {
        return isSpare(scoreRoll, rollIndex) ? rolls.next(rollIndex) : 0;
    }

    private boolean isSpare(int scoreRoll, int rollIndex) {
        return scoreRoll + rolls.next(rollIndex) == 10;
    }

    private class Rolls {
        static final int NORMAL_FRAME = 18;
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

        boolean notLastFrame() {
            return nextRollIndex < NORMAL_FRAME;
        }
    }
}
