package ql.validator.cycles;

import java.util.HashSet;
import java.util.Set;

public class DependencyManager {

    private Set<DependencyPair> dependencies;

    public DependencyManager() {
        this.dependencies = new HashSet<>();
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


    public Set<DependencyPair> getCircularDependencies(Set<DependencyPair> input) {
        Set<DependencyPair> closure = new HashSet<>();

        for (DependencyPair pair : getTransitiveClosure(input)) {
            if (pair.isReflexive())
                closure.add(pair);
        }

        return closure;
    }

    public Set<DependencyPair> getTransitiveClosure(Set<DependencyPair> input) {
        Set<DependencyPair> closure = new HashSet<>();
        closure.addAll(input);

        while (true) {
            Set<DependencyPair> newEdges = generateTransitiveEdges(closure);
            newEdges.addAll(closure);

            if (newEdges.equals(closure))
                break;

            closure = newEdges;
        }

        return closure;
    }

//
//     public Set<DependencyPair> makeTransitiveClosure (Set<DependencyPair> input) {
//         Set<DependencyPair> transitiveClosure = new HashSet<>(input);
//
// /*        while(!nextTransitiveAddition.equals(transitiveClosure)) {
//             // nextTransitiveAddition = generateTransitiveEdges(input);
//             transitiveClosure.addAll(nextTransitiveAddition);
//             nextTransitiveAddition = generateTransitiveEdges(transitiveClosure);
//
//         }*/
//         Set<DependencyPair> newTransitiveEdges;
//         do {
//             newTransitiveEdges = generateTransitiveEdges(transitiveClosure);
//             newTransitiveEdges.addAll(transitiveClosure);
//
//         } while (newTransitiveEdges.equals(transitiveClosure));
//
//
//         while (true) {
//             Set<DependencyPair> newTransitiveEdges = generateTransitiveEdges(transitiveClosure);
//             newTransitiveEdges.addAll(transitiveClosure);
//
//             if (newTransitiveEdges.equals(transitiveClosure))
//                 break;
//
//             transitiveClosure = newTransitiveEdges;
//         }
//
//
//         return transitiveClosure;
//     }

}
