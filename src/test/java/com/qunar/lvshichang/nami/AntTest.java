package com.qunar.lvshichang.nami;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qunar.nami.ant.AntPathMatcher;
import com.qunar.nami.ant.PathMatcher;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

@Slf4j
public class AntTest {

    private static Map<String, String> patternHandler = Maps.newHashMap();

    @Test
    public void testAnt() {
        PathMatcher matcher = new AntPathMatcher();
        patternHandler.put("**/client", "**/client");
        patternHandler.put("ctrip/flight/**", "ctrip/flight/**");

        List<String> matchingPatterns = Lists.newArrayList();
        String path = "ctrip/flight/client";

        for (String pattern : patternHandler.keySet()) {
            if (matcher.match(pattern, path)) {
                matchingPatterns.add(pattern);
            }
        }
        String bestMatch = null;
        if (!matchingPatterns.isEmpty()) {
            Comparator<String> patternComparator = matcher.getPatternComparator(path);
            Collections.sort(matchingPatterns, patternComparator);
            bestMatch = matchingPatterns.get(0);
        }

        if (bestMatch != null) {
            log.info("path :{} bestMatch :{} bestHandler :{}", path, bestMatch, patternHandler.get(bestMatch));
        }

    }
}
