package ql.validator.checkers.cycles;

import java.util.Objects;

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
