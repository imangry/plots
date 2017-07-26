package com.qunar.lvshichang.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lvshi on 17/07/17.
 */
public class SplitterAndJoiner {


    @Test
    public void splitString() {
        List<String> list = Splitter.on("--").omitEmptyStrings().trimResults().splitToList("qwer--asdf--zxcv--");
        System.out.println(list);


    }

    @Test
    public void joinerTest() {

        HashMap<String, String> params = Maps.newHashMap();

        params.put("username", "shichang.lv");
        params.put("password", "1234");

        String str = Joiner.on("&").withKeyValueSeparator("=").join(params);
        System.out.println(str);

        Map<String, String> map = Splitter.on("&").withKeyValueSeparator("=").split(str);
        System.out.println(map);
    }


    private static final String WHITESPACE_TABLE =
            "\u2002\u3000\r\u0085\u200A\u2005\u2000\u3000"
                    + "\u2029\u000B\u3000\u2008\u2003\u205F\u3000\u1680"
                    + "\u0009\u0020\u2006\u2001\u202F\u00A0\u000C\u2009"
                    + "\u3000\u2004\u3000\u3000\u2028\n\u2007\u3000";
    private static final int WHITESPACE_SHIFT = Integer.numberOfLeadingZeros(WHITESPACE_TABLE.length() - 1);

    @Test
    public void find() {

        System.out.println(WHITESPACE_TABLE.length());
        System.out.println(Integer.numberOfLeadingZeros(WHITESPACE_TABLE.length() - 1));
        char[] charsReq = WHITESPACE_TABLE.toCharArray();
        Arrays.sort(charsReq);
        OUTER:
        for (int WHITESPACE_MULTIPLIER_WANTED = 1682553701; WHITESPACE_MULTIPLIER_WANTED <= 1682554834; WHITESPACE_MULTIPLIER_WANTED++) {
            int matchCnt = 0;
            for (int c = 0; c <= Character.MAX_VALUE; c++) {
                int position = Arrays.binarySearch(charsReq, (char) c);
                char index = WHITESPACE_TABLE.charAt((WHITESPACE_MULTIPLIER_WANTED * c) >>> WHITESPACE_SHIFT);
                if (position >= 0 && index == c) {
                    matchCnt++;
                } else if (position < 0 && index != c) {
                    matchCnt++;
                } else {
                    continue OUTER;
                }
            }
            // all valid
            if ((matchCnt - 1) == (int) (Character.MAX_VALUE)) {
                System.out.println(WHITESPACE_MULTIPLIER_WANTED);
            }
        }
    }
}
