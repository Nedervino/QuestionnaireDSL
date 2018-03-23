package ql.validator.checkers.cycles;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class DependencyManagerTest {

    @Test
    public void shoudReturnCorrectCircularDependencies() {
        final int EXPECTED_REFLEXIVE_PAIRS = 3;

        DependencyPair pair1 = new DependencyPair("a", "b");
        DependencyPair pair2 = new DependencyPair("b", "c");
        DependencyPair pair3 = new DependencyPair("c", "a");

        Set<DependencyPair> dependencySet = new HashSet<DependencyPair>() {{
            add(pair1);
            add(pair2);
            add(pair3);
        }};

        DependencyManager dependencyManager = new DependencyManager(dependencySet);
        Set<DependencyPair> circularDependencies = dependencyManager.getCircularDependencies();

        assertEquals(EXPECTED_REFLEXIVE_PAIRS, circularDependencies.size());
    }

}