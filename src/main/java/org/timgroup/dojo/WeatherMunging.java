package org.timgroup.dojo;

import fj.F;
import fj.P2;
import fj.data.Stream;

import static org.timgroup.dojo.FootballMunging.lineWithMinDiff;
import static org.timgroup.dojo.FootballMunging.parseLine;

public class WeatherMunging {
    private final Stream<String> lines;

    public WeatherMunging(Stream<String> lines) {
        this.lines = lines;
    }

    public int dayWithTheSmallestTemperatureSpread() {
        return Integer.valueOf(lineWithMinDiff(lines, new F<String, P2<Integer, String>>() {
            @Override public P2<Integer, String> f(String s) {
                return parseWeatherLine(s);
            }
        }));
    }

    public static P2<Integer, String> parseWeatherLine(String line) {
        return parseLine(line, 1, 2, 3);
    }

}
