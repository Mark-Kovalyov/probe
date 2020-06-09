package mayton.html.mocks;

import mayton.html.entities.MemberInfo;
import mayton.html.MemberInfoService;
import mayton.html.NameGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Random;

@Component
@Qualifier("mock")
public class MemberInfoServiceMock implements MemberInfoService {

    private Random random = new Random();

    @Autowired
    NameGenerator nameGenerator;

    @Override
    public Optional<MemberInfo> getMemberInfo(int id) {

        if (id > 250_00) {
            return Optional.empty();
        }

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

        return Optional.of(memberInfo);
    }


}
