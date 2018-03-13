package ql.validator.cycles;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class DependencyManagerTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shoudReturnCorrectCircularDependencies() {
        final int EXPECTED_REFLEXIVE_PAIRS = 3;
        final int EXPECTED_TRANSITIVE_PAIRS = 9;

        DependencyPair pair1 = new DependencyPair("a", "b");
        DependencyPair pair2 = new DependencyPair("b", "c");
        DependencyPair pair3 = new DependencyPair("c", "a");

        Set<DependencyPair> dependencySet = new HashSet<DependencyPair>() {{
            add(pair1);
            add(pair2);
            add(pair3);
        }};

        DependencyManager dependencyManager = new DependencyManager(dependencySet);

        Set<DependencyPair> transitiveClosure = dependencyManager.makeTransitiveClosure(dependencySet);
        Set<DependencyPair> circularDependencies = dependencyManager.getCircularDependencies();

        assertEquals(EXPECTED_TRANSITIVE_PAIRS, transitiveClosure.size());
        assertEquals(EXPECTED_REFLEXIVE_PAIRS, circularDependencies.size());
    }

}