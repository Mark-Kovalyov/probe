package mayton.parsers;

import java.util.Random;

public class FantazyGenerator {

    public static class ElvenGenerator {
        public static String[] prefixes = { "Ael", "Aer", "Af", "Ah", "Al", "Am", "Ama", "An", "Ang", "Ansr", "Ar", "Ari", "Arn", "Aza", "Bael", "Bes", "Cael", "Cal", "Cas", "Cla", "Cor", "Cy", "Dae", "Dho", "Dre", "Du", "Eil", "Eir", "El", "Er", "Ev", "Fera", "Fi", "Fir", "Fis", "Gael", "Gar", "Gil", "Ha", "Hu", "Ia", "Il", "Ja", "Jar", "Ka", "Kan", "Ker", "Keth", "Koeh", "Kor", "La", "Laf", "Lam", "Lue", "Ly", "Mai", "Mal", "Mara", "My", "Na", "Nai", "Nim", "Nu", "Ny", "Py", "Raer", "Re", "Ren", "Rhy", "Ru", "Rua", "Rum", "Rid", "Sae", "Seh", "Sel", "Sha", "She", "Si", "Sim", "Sol", "Sum", "Syl", "Ta", "Tahl", "Tha", "Tho", "Ther", "Thro", "Tia", "Tra", "Ty", "Uth", "Ver", "Vil", "Von", "Ya", "Za", "Zy" };
        public static String[] suffixes = { "ae", "ael", "aer", "aias", "ah", "aith", "al", "ali", "am", "an", "ar", "ari", "aro", "as", "ath", "avel", "brar", "dar", "deth", "dre", "drim", "dul", "ean", "el", "emar", "en", "er", "ess", "evar", "fel", "hal", "har", "hel", "ian", "iat", "ik", "il", "im", "in", "ir", "is", "ith", "kash", "ki", "lan", "lam", "lar", "las", "lian", "lis", "lyn", "mah", "mil", "mus", "nal", "nes", "nin", "nis", "on", "or", "oth", "que", "quis", "rah", "rad", "rail", "ran", "reth", "ro", "ruil", "sal", "san", "sar", "sel", "sha", "spar", "tae", "tas", "ten", "thal", "thar", "ther", "thi", "thus", "ti", "tril", "ual", "uath", "us", "van", "var", "vain", "via", "vin", "wyn", "ya", "yr", "yth", "zair" };
    }

    public static class ElvenCityGenerator {
        public static String[] prefixes = {"Riv","Loth","Tir","Aqu","Gond","Fir","For","Mirk","Mith","Galad","Gal","Cel","Sir","Luc","Val","Glor"};
        public static String[] suffixes = {"endell","ariel","en Haven", "olin","alonde","ion","ian","en Woods","an Riverlands"};
    }

    public static void main(String[] args) {
        Random random = new Random();
        for(int i = 0 ; i< 100; i++) {
            System.out.printf("%s , ", ElvenGenerator.prefixes[random.nextInt(ElvenGenerator.prefixes.length)] +
                    ElvenGenerator.suffixes[random.nextInt(ElvenGenerator.suffixes.length)]);
        }

        System.out.println("=========================================================================");

        for(int i = 0 ; i< 100; i++) {
            System.out.printf("%s , ", ElvenCityGenerator.prefixes[random.nextInt(ElvenCityGenerator.prefixes.length)] +
                    ElvenCityGenerator.suffixes[random.nextInt(ElvenCityGenerator.suffixes.length)]);
        }
    }
}
