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
        return lineWithMinDiff(lines, new F<String, P2<Integer, String>>() {
            @Override public P2<Integer, String> f(String s) {
                return parseFootballLine(s);
            }
        });
    }

    public static String lineWithMinDiff(Stream<String> lines, final F<String, P2<Integer, String>> parseLineFunction) {
        return filterNumberedLines(lines).map(new F<String, P2<Integer, String>>() {
            @Override public P2<Integer, String> f(String s) {
                return parseLineFunction.f(s);
            }
        }).sort(p2Ord(intOrd, stringOrd)).head()._2();
    }

    public static Stream<String> filterNumberedLines(Stream<String> lines) {
        return lines.filter(new F<String, Boolean>() {
            @Override public Boolean f(String s) {
                return s.matches("^\\s+\\d+.+$");
            }
        });
    }

    public static P2<Integer, String> parseFootballLine(String line) {
        return parseLine(line, 2, 7, 9);
    }

    public static P2<Integer, String> parseLine(String line, int nameColumn, int valueColumn1, int valueColumn2) {
        final String[] fields = line.split("\\s+");
        return p(Math.abs(Integer.valueOf(fields[valueColumn1].replace("*", "")) - Integer.valueOf(fields[valueColumn2].replace("*", ""))),
                fields[nameColumn]);
    }
}
