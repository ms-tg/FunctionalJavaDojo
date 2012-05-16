package org.timgroup.dojo;

import fj.P2;
import fj.data.Stream;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.timgroup.dojo.Files.readLinesFrom;

public class FootballMungingTest {
    private Stream<String> footballLines;

    @Before
    public void setup() {
        footballLines = readLinesFrom("football.dat");
    }

    @Test
    public void teamWithSmallestDifferenceBetweenForAndAgainstIsAstonVilla() {
        assertThat(new FootballMunging(footballLines).teamWithSmallestDifferenceBetweenForAndAgainst(), is("Aston_Villa"));
    }

    @Test
    public void there_are_20_lines_of_football_stats() {
        assertThat(FootballMunging.filterNumberedLines(footballLines).length(), is(20));
    }

    @Test
    public void parses_relevant_info_out_of_a_line() {
        final String firstLine = "    1. Arsenal         38    26   9   3    79  -  36    87";
        final P2<Integer, String> arsenal = FootballMunging.parseFootballLine(firstLine);
        assertThat(arsenal._1(), is(43));
        assertThat(arsenal._2(), is("Arsenal"));
    }
}
