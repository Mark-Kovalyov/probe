package mayton;

import java.util.Iterator;
import java.util.Random;

public class ElvenCityGenerator implements Iterator<String> {

    public static String[] prefixes = {"Riv","Loth","Tir","Aqu","Gond","Fir","For","Mirk","Mith","Galad","Gal","Cel","Sir","Luc","Val","Glor"};
    public static String[] suffixes = {"endell","ariel","en Haven", "olin","alonde","ion","ian","en Woods","an Riverlands"};

    private Random random = new Random();

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public String next() {
        return ElvenCityGenerator.prefixes[random.nextInt(ElvenCityGenerator.prefixes.length)] + ElvenCityGenerator.suffixes[random.nextInt(ElvenCityGenerator.suffixes.length)];
    }



}
