package com.steven;

import com.steven.exceptions.EmptyNameException;
import com.steven.exceptions.InvalidDartsAtDoubleException;
import com.steven.exceptions.InvalidScoreException;
import com.steven.exceptions.ScoreException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {


    @Test
    void starting_score_intitialised(){
        Match match = new Match(501, "Ste");
        int expected = 501;
        int actual = match.getStartingScore();

        assertEquals(expected, actual);
    }

    @Test
    void starting_score_minimum(){
        ScoreException thrown = assertThrows(
                ScoreException.class,
                () -> new Match(168, "Ste"));
        System.out.println(thrown.getMessage());
        assertTrue(thrown.getMessage().contains("Starting score below 170"));
    }

    @ParameterizedTest
    @EmptySource
    void name_set(String name){
        EmptyNameException thrown = assertThrows(
                EmptyNameException.class,
                () -> new Match(501, name));
        System.out.println(thrown.getMessage());
        assertTrue(thrown.getMessage().contains("Name is empty"));
    }

    @Test
    void current_score_set_as_starting(){
        Match match = new Match(501, "Ste");
        int expected = 501;
        int actual = match.getCurrentScore();

        assertEquals(expected, actual);
    }

    @Test
    void remove_valid_score(){
        Match match = new Match(501, "Ste");
        match.removeScore(60);
        int expected = 441;
        int actual = match.getCurrentScore();

        assertEquals(expected, actual);
    }

    @Test
    void remove_invalid_score(){
        Match match = new Match(170, "Ste");
        match.removeScore(60);
        System.out.println(match.getCurrentScore());
        InvalidScoreException thrown = assertThrows(
                InvalidScoreException.class,
                () -> match.removeScore(120));

        assertTrue(thrown.getMessage().contains("Invalid score"));
    }

    @Test
    void check_score_is_valid() {
        Match match = new Match(201, "Ste");
        assertTrue(match.isScoreValid(60));
    }

    @Test
    void score_invalid_over_max(){
        Match match = new Match(201, "Ste");
        match.removeScore(100);
        assertEquals(101, match.getCurrentScore());
        assertFalse(match.isScoreValid(120));
    }

    @Test
    void score_over_max(){
        Match match = new Match(501, "Ste");
        assertFalse(match.isScoreValid(200));
    }

    @Test
    void score_less_than_zero(){
        Match match = new Match(501, "Ste");
        assertFalse(match.isScoreValid(-10));
    }

    @Test
    void multiple_scores_and_current_given(){
        Match match = new Match(501, "Ste");
        match.removeScore(60);
        assertEquals(441, match.getCurrentScore());
        match.removeScore(60);
        assertEquals(381, match.getCurrentScore());
        match.removeScore(60);
        assertEquals(321, match.getCurrentScore());
        match.removeScore(140);
        assertEquals(181, match.getCurrentScore());
    }

    @Test
    void three_darts_counted(){
        Match match = new Match(501, "Ste");
        match.removeScore(100);
        int expected = 3;
        int actual = match.getDartsThrown();
        assertEquals(expected, actual);
    }

    @Test
    void six_darts_counted(){
        Match match = new Match(501, "Ste");
        match.removeScore(100);
        match.removeScore(100);
        int expected = 6;
        int actual = match.getDartsThrown();
        assertEquals(expected, actual);
    }

    @Test
    void nine_darts_counted(){
        Match match = new Match(501, "Ste");
        match.removeScore(100);
        match.removeScore(100);
        match.removeScore(100);
        int expected = 9;
        int actual = match.getDartsThrown();
        assertEquals(expected, actual);
    }

    @Test
    void invalid_darts_not_counted(){
        Match match = new Match(501, "Ste");
        match.removeScore(100);
        assertEquals(401, match.getCurrentScore());
        assertEquals(3, match.getDartsThrown());
        InvalidScoreException thrown = assertThrows(
                InvalidScoreException.class,
                () -> match.removeScore(200));
        assertTrue(thrown.getMessage().contains("Invalid score"));
        assertEquals(401, match.getCurrentScore());
        assertEquals(3, match.getDartsThrown());
        match.removeScore(100);
        assertEquals(301, match.getCurrentScore());
        assertEquals(6, match.getDartsThrown());

    }

    @ParameterizedTest
    @ValueSource(ints = {152, 133, 82, 99, 45, 22, 2})
    void confirms_on_finish(int currentScore){
        Match match = new Match(201, "Ste");
        assertTrue(match.isOnFinish(currentScore));
    }

    @ParameterizedTest
    @ValueSource(ints = {169, 168, 166, 165, 163, 162, 159})
    void confirm_not_on_finish(int currentScore){
        Match match = new Match(201, "Ste");
        assertFalse(match.isOnFinish(currentScore));
    }

    @Test
    void confirm_one_not_finish(){
        Match match = new Match(201, "Ste");
        assertFalse(match.isOnFinish(1));
    }

    @Test
    void confirm_leg_complete(){
        Match match = new Match(201, "Ste");
        match.removeScore(121);
        assertEquals(80, match.getCurrentScore());
        match.removeScore(80);
        assertEquals(0, match.getCurrentScore());
        assertTrue(match.isLegComplete(match.getCurrentScore()));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 10, 40, 66, 120})
    void confirm_leg_not_complete(int currentScore){
        Match match = new Match(201, "Ste");
        assertFalse(match.isLegComplete(currentScore));
    }

    @ParameterizedTest
    @MethodSource("validDartsAtDouble")
    void record_darts_at_double(int dartsAtDouble, int expected) {
        Match match = new Match(201, "Ste");
        assertEquals(expected, match.setDartsAtDouble(dartsAtDouble));
    }

    private static Stream<Arguments> validDartsAtDouble() {
        return Stream.of(
                Arguments.of(1, 1),
                Arguments.of(2, 2),
                Arguments.of(3, 3)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, 0, 5, 20})
    void record_darts_at_double(int dartsAtDouble) {
        Match match = new Match(201, "Ste");
        InvalidDartsAtDoubleException thrown = assertThrows(
                InvalidDartsAtDoubleException.class,
                () -> match.setDartsAtDouble(dartsAtDouble));
        assertTrue(thrown.getMessage().contains("Invalid number of darts"));
    }

    @Test
    void total_dats_for_leg(){
        Match match = new Match(501, "Ste");
        match.removeScore(100);
        assertEquals(401, match.getCurrentScore());
        match.removeScore(100);
        assertEquals(301, match.getCurrentScore());
        match.removeScore(100);
        assertEquals(201, match.getCurrentScore());
        match.removeScore(100);
        assertEquals(101, match.getCurrentScore());
        match.removeScore(61);
        assertEquals(40, match.getCurrentScore());
        match.removeScore(40);
        match.setDartsAtDouble(2);
        int expected = 17;
        int actual = match.getDartsThrown();
        assertEquals(expected, actual);
    }

    @Test
    void darts_thrown(){
        Match match = new Match(501, "Ste");
        match.removeScore(100);
        assertEquals(401, match.getCurrentScore());
        match.removeScore(100);
        assertEquals(301, match.getCurrentScore());
        int expected = 6;
        int actual = match.getDartsThrown();
        System.out.println(match.getDartsAtDouble());
        assertEquals(expected, actual);
    }

    @Test
    void average_calculated(){
        Match match = new Match(501, "Ste");
        match.removeScore(100);
        assertEquals(401, match.getCurrentScore());
        match.removeScore(100);
        assertEquals(301, match.getCurrentScore());
        match.removeScore(100);
        assertEquals(201, match.getCurrentScore());
        match.removeScore(100);
        assertEquals(101, match.getCurrentScore());
        match.removeScore(61);
        assertEquals(40, match.getCurrentScore());
        match.removeScore(40);
        match.setDartsAtDouble(2);
        double expected = (double) match.getStartingScore() / match.getDartsThrown() * 3;
        double actual = match.calculateAverage();
        System.out.println(actual);
        assertEquals(expected, actual);
    }



}