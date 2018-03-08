package ql.validator.cycles;

public class DependencyPair {
    private final String source;
    private final String destination;

    public DependencyPair(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    public boolean isReflexive() {
        return source.equals(destination);
    }

    public boolean isTransitiveWith(DependencyPair otherPair) {
        return destination.equals(otherPair.source);
    }
}
