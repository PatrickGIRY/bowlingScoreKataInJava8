package bowling.score;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingScoreTest {

    private Game game = new Game();

    @Test
    void a_gutter_game_has_a_score_of_0() {
        IntStream.range(0, 20).forEach(index -> game.roll(0));

        int score = game.score();

        assertThat(score).isEqualTo(0);
    }

    @Test
    void all_rolls_with_one_knock_down_pin_has_a_score_of_20() {
        IntStream.range(0, 20).forEach(index -> game.roll(1));

        int score = game.score();

        assertThat(score).isEqualTo(20);
    }


}
