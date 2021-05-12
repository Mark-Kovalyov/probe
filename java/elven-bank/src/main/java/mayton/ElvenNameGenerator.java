package mayton;

import java.util.Iterator;
import java.util.Random;

public class ElvenNameGenerator implements Iterator<String> {

    public static String[] prefixes = { "Ael", "Aer", "Af", "Ah", "Al", "Am", "Ama", "An", "Ang", "Ansr", "Ar", "Ari", "Arn", "Aza", "Bael", "Bes", "Cael", "Cal", "Cas", "Cla", "Cor", "Cy", "Dae", "Dho", "Dre", "Du", "Eil", "Eir", "El", "Er", "Ev", "Fera", "Fi", "Fir", "Fis", "Gael", "Gar", "Gil", "Ha", "Hu", "Ia", "Il", "Ja", "Jar", "Ka", "Kan", "Ker", "Keth", "Koeh", "Kor", "La", "Laf", "Lam", "Lue", "Ly", "Mai", "Mal", "Mara", "My", "Na", "Nai", "Nim", "Nu", "Ny", "Py", "Raer", "Re", "Ren", "Rhy", "Ru", "Rua", "Rum", "Rid", "Sae", "Seh", "Sel", "Sha", "She", "Si", "Sim", "Sol", "Sum", "Syl", "Ta", "Tahl", "Tha", "Tho", "Ther", "Thro", "Tia", "Tra", "Ty", "Uth", "Ver", "Vil", "Von", "Ya", "Za", "Zy" };
    public static String[] suffixes = { "ae", "ael", "aer", "aias", "ah", "aith", "al", "ali", "am", "an", "ar", "ari", "aro", "as", "ath", "avel", "brar", "dar", "deth", "dre", "drim", "dul", "ean", "el", "emar", "en", "er", "ess", "evar", "fel", "hal", "har", "hel", "ian", "iat", "ik", "il", "im", "in", "ir", "is", "ith", "kash", "ki", "lan", "lam", "lar", "las", "lian", "lis", "lyn", "mah", "mil", "mus", "nal", "nes", "nin", "nis", "on", "or", "oth", "que", "quis", "rah", "rad", "rail", "ran", "reth", "ro", "ruil", "sal", "san", "sar", "sel", "sha", "spar", "tae", "tas", "ten", "thal", "thar", "ther", "thi", "thus", "ti", "tril", "ual", "uath", "us", "van", "var", "vain", "via", "vin", "wyn", "ya", "yr", "yth", "zair" };
    Random random = new Random();

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public String next() {
        return prefixes[random.nextInt(prefixes.length)] + suffixes[random.nextInt(suffixes.length)];
    }
}
