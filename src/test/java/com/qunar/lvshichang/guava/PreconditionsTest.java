package com.qunar.lvshichang.guava;

import com.google.common.base.Preconditions;
import org.junit.Test;

/**
 * Created by lvshi on 17/07/17.
 */
public class PreconditionsTest {

    @Test
    public void checkNull() {

        Preconditions.checkNotNull(null, "args is null");
    }

    @Test
    public void checkArgs() {
        int i = 5;
        Preconditions.checkArgument(i > 6,"args is error");
    }
}
