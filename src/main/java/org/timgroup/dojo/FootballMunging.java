package org.timgroup.dojo;

import fj.F;
import fj.P2;
import fj.data.Stream;

import static fj.Ord.intOrd;
import static fj.Ord.stringOrd;
import static fj.Ord.p2Ord;
import static fj.P.p;

public class FootballMunging {
    private final Stream<String> lines;

    public FootballMunging(Stream<String> lines) {
        this.lines = lines;
    }

    public String teamWithSmallestDifferenceBetweenForAndAgainst() {
        return filterLines(lines).map(new F<String, P2<Integer, String>>() {
            @Override public P2<Integer, String> f(String s) {
                return parseLine(s);
            }
        }).sort(p2Ord(intOrd, stringOrd)).head()._2();
    }

    public static Stream<String> filterLines(Stream<String> footballLines) {
        return footballLines.filter(new F<String, Boolean>() {
            @Override public Boolean f(String s) {
                return s.contains(" - ");
            }
        });
    }

    public static P2<Integer, String> parseLine(String line) {
        final String[] fields = line.split("\\s+");
        return p(Math.abs(Integer.valueOf(fields[7]) - Integer.valueOf(fields[9])), fields[2]);
    }
}
