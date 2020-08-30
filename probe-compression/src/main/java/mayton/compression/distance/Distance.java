package mayton.compression.distance;

import org.apache.commons.text.similarity.CosineDistance;
import org.apache.commons.text.similarity.EditDistance;
import org.apache.commons.text.similarity.HammingDistance;
import org.apache.commons.text.similarity.LevenshteinDistance;

public class Distance {

    static void printf(String format, Object ...arg) {
        System.out.printf(format, arg);
    }

    public static void main(String[] args) {
        
        String[] s = { "андрей", "армия", "бенигсен", "божество", "буародное",
                "будет", "бы", "было", "вам", "ваше", "вашей", "война",
                "все", "вывозили", "выезжала", "выйдет", "говорили", "государь",
                "граф", "графиня", "даже", "дать", "действителен", "десятого",
                "его", "есть", "завещание", "засну", "затруднительно"};

        EditDistance<Integer> distanceFun = LevenshteinDistance.getDefaultInstance();
        //EditDistance<Integer> distanceFun = new HammingDistance();
        //EditDistance<Double> distanceFun = new CosineDistance();

        printf("[CSV=,]\n");
        for(int j = 0; j < s.length ; j ++) {
            printf(",%s",s[j]);
        }
        printf("\n");
        for(int i = 0 ; i < s.length ; i++) {
            for(int j = 0; j < s.length ; j ++) {
                if (j == 0) {
                    printf("%s", s[i]);
                }
                if (j < i) {
                    printf(",");
                } else {
                    printf(",%s", distanceFun.apply(s[i], s[j]));
                }
            }
            printf("\n");
        }
        printf("[/CSV]\n");

    }

}
