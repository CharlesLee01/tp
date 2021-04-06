//@@author ZhangAnli
package dog.pawbook.model.managedentity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import javafx.util.Pair;

/**
 * Tests that the ID of an Entity is within a supplied list of IDs.
 */
public class IdMatchPredicate implements Predicate<Pair<Integer, Entity>> {

    private final Set<Integer> relatedIds = new HashSet<>();

    /**
     * Construct a predicate from a list of IDs.
     */
    public IdMatchPredicate(Collection<Integer> idList) {
        assert (!idList.isEmpty());
        this.relatedIds.addAll(idList);
    }

    /**
     * Construct a predicate for a single ID.
     */
    public IdMatchPredicate(Integer id) {
        assert(!id.equals(null));
        this.relatedIds.add(id);
    }

    @Override
    public boolean test(Pair<Integer, Entity> idEntityPair) {
        assert(!idEntityPair.equals(null));
        return relatedIds.stream().anyMatch(id -> idEntityPair.getKey().equals(id));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IdMatchPredicate // instanceof handles nulls
                && relatedIds.equals(((IdMatchPredicate) other).relatedIds)); // state check
    }

}
