package bowling.score;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingScoreTest {

    private Game game = new Game();

    @Test
    void a_gutter_game_has_a_score_of_0() {
        rollMany(20, 0);

        int score = game.score();

        assertThat(score).isEqualTo(0);
    }

    @Test
    void all_rolls_with_one_knock_down_pin_has_a_score_of_20() {
        rollMany(20, 1);

        int score = game.score();

        assertThat(score).isEqualTo(20);
    }

    @Test
    void one_spare_followed_by_a_roll_with_3_knock_down_pins_has_score_of_16() {
        rollaSpare();
        game.roll(3);
        rollMany(17, 0);

        int score = game.score();

        assertThat(score).isEqualTo(16);
    }

    private void rollaSpare() {
        game.roll(5);
        game.roll(5);
    }

    private void rollMany(int times, int knockDownPins) {
        IntStream.range(0, times).forEach(index -> game.roll(knockDownPins));
    }

}
