package org.example;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public interface MemberInfoService {

    @NotNull MemberInfo getMemberInfo(int id);

}
