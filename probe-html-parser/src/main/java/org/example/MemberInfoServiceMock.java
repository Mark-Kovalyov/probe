package org.example;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemberInfoServiceMock implements MemberInfoService {

    private Random random = new Random();

    @Inject @Named("")
    NameGenerator nameGenerator;

    @Override
    public MemberInfo getMemberInfo(int id) {

        List<Pair<Forum, Integer>> pairList = new ArrayList();

        for(int i = 0; i < 20; i++ ) {
            pairList.add(ImmutablePair.of(
                    Forum.values()[(random.nextInt(Forum.values().length))],
                    (int) (500.0 + 250.0 * random.nextGaussian())
            ));
        }

        MemberInfo memberInfo = new MemberInfo(id,
                true,
                nameGenerator.next(),
                (int) (500.0 + 250.0 * random.nextGaussian()),
                LocalDate.now(), LocalDate.now(), pairList);

        return memberInfo;
    }
}
