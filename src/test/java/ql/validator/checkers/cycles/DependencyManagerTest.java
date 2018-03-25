package ql.validator.checkers.cycles;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class DependencyManagerTest {

    @Test
    public void shoudReturnCorrectCircularDependencies() {
        final int EXPECTED_REFLEXIVE_PAIRS = 3;

        DependencyManager dependencyManager = new DependencyManager();
        dependencyManager.addDependency("a","b");
        dependencyManager.addDependency("b","c");
        dependencyManager.addDependency("c","a");

        Set<DependencyPair> circularDependencies = dependencyManager.getCircularDependencies();

        assertEquals(EXPECTED_REFLEXIVE_PAIRS, circularDependencies.size());
    }

}