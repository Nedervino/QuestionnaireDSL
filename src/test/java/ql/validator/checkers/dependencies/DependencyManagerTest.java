package ql.validator.checkers.dependencies;

import org.junit.Test;
import ql.validator.checkers.DependencyManager;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class DependencyManagerTest {

    @Test
    public void shoudReturnCorrectCircularDependencies() {
        final int EXPECTED_REFLEXIVE_PAIRS = 3;

        DependencyManager dependencyManager = new DependencyManager();
        dependencyManager.addDependency("a", "b");
        dependencyManager.addDependency("b", "c");
        dependencyManager.addDependency("c", "a");

        Set<DependencyManager.DependencyPair> circularDependencies = dependencyManager.getCircularDependencies();

        assertEquals(EXPECTED_REFLEXIVE_PAIRS, circularDependencies.size());
    }

}