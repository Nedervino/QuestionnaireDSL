package ql.validator.checkers;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class DependencyManager {

    private final Set<DependencyPair> dependencies;

    public DependencyManager() {
        this.dependencies = new HashSet<>();
    }

    public void addDependency(String source, String location) {
        dependencies.add(new DependencyPair(source, location));
    }

    public Set<DependencyPair> getCircularDependencies() {
        Set<DependencyPair> circularPairs = new HashSet<>();
        Set<DependencyPair> transitiveClosure = makeTransitiveClosure(dependencies);
        for (DependencyPair pair : transitiveClosure) {
            if (pair.isReflexive()) {
                circularPairs.add(pair);
            }
        }

        return circularPairs;
    }

    private Set<DependencyPair> generateTransitiveEdges(Set<DependencyPair> input) {
        Set<DependencyPair> newEdges = new HashSet<>();

        for (DependencyPair first : input) {
            for (DependencyPair second : input) {
                if (first.isTransitiveWith(second)) {
                    newEdges.add(new DependencyPair(first.getSource(), second.getDestination()));
                }
            }
        }
        return newEdges;
    }

    private Set<DependencyPair> makeTransitiveClosure(Set<DependencyPair> input) {
        Set<DependencyPair> transitiveClosure = new HashSet<>(input);

        while (true) {
            Set<DependencyPair> newEdges = generateTransitiveEdges(transitiveClosure);
            newEdges.addAll(transitiveClosure);

            if (newEdges.equals(transitiveClosure))
                break;
            transitiveClosure = newEdges;
        }

        return transitiveClosure;
    }

    public class DependencyPair {

        private final String source;
        private final String destination;

        public DependencyPair(String source, String destination) {
            this.source = source;
            this.destination = destination;
        }

        public String getSource() {
            return source;
        }

        public String getDestination() {
            return destination;
        }

        public boolean isReflexive() {
            return source.equals(destination);
        }

        public boolean isTransitiveWith(DependencyPair otherPair) {
            return destination.equals(otherPair.getSource());
        }

        @Override
        public int hashCode() {
            return Objects.hash(source, destination);
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || this.getClass() != object.getClass()) return false;
            DependencyPair otherPair = (DependencyPair) object;

            if (source != null ? !source.equals(otherPair.getSource()) : otherPair.getSource() != null) return false;
            return destination != null ? destination.equals(otherPair.getDestination()) : otherPair.getDestination() == null;
        }

    }


}
