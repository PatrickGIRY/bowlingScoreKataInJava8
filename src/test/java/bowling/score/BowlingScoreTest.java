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

    @Test
    void one_strike_followed_by_two_rolls_with_3_and_4_knock_down_pins_has_score_of_24() {
        rollStrike();
        game.roll(3);
        game.roll(4);
        rollMany(16, 0);

        assertThat(game.score()).isEqualTo(24);
    }

    @Test
    void two_strikes_followed_by_two_rolls_with_3_and_4_knock_down_pins_has_score_of_47() {
        rollStrike();
        rollStrike();
        game.roll(3);
        game.roll(4);
        rollMany(14, 0);

        assertThat(game.score()).isEqualTo(47);
    }

    @Test
    void a_perfect_game_has_score_of_300() {
        rollMany(12, 10);

        assertThat(game.score()).isEqualTo(300);
    }

    private void rollStrike() {
        game.roll(10);
    }

    private void rollaSpare() {
        game.roll(5);
        game.roll(5);
    }

    private void rollMany(int times, int knockDownPins) {
        IntStream.range(0, times).forEach(index -> game.roll(knockDownPins));
    }

}
