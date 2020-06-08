package mayton.html.mocks;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import mayton.html.entities.MemberInfo;
import mayton.html.MemberInfoService;
import mayton.html.NameGenerator;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Random;

@Singleton
public class MemberInfoServiceMock implements MemberInfoService {

    private Random random = new Random();

    @Inject
    NameGenerator nameGenerator;

    @Override
    public MemberInfo getMemberInfo(int id) {

        LinkedHashMap<Integer, Double> map = new LinkedHashMap<>();

        for(int i = 0; i < 20; i++ ) {
            map.put(
                    i,
                    Math.abs(500.0 + 250.0 * random.nextGaussian())
            );
        }

        MemberInfo memberInfo = new MemberInfo(id,
                true,
                nameGenerator.next(),
                (int) (500.0 + 250.0 * random.nextGaussian()),
                LocalDate.now(), LocalDate.now(), map);

        return memberInfo;
    }


}
