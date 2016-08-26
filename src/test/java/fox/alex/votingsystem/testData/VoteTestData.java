package fox.alex.votingsystem.testData;

import fox.alex.votingsystem.matcher.ModelMatcher;
import fox.alex.votingsystem.model.Vote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static fox.alex.votingsystem.model.BaseEntity.START_INDEX;

/**
 * Created by fox on 24.08.16.
 */
public class VoteTestData {
    private static final Logger LOG = LoggerFactory.getLogger(VoteTestData.class);

    public static final int VOTE_ID = START_INDEX + 17;

    public static final Vote VOTE1 = new Vote(VOTE_ID, 4);
    public static final Vote VOTE2 = new Vote(VOTE_ID + 1, 4);
    public static final Vote VOTE3 = new Vote(VOTE_ID + 2, 5);

    public static final List<Vote> VOTE_LIST = Arrays.asList(VOTE1, VOTE2, VOTE3);

    public static final ModelMatcher<Vote> MATCHER = new ModelMatcher<>(Vote.class,
            (expected, actual) -> {
                if (expected == actual) {
                    return true;
                }
                boolean cmp = Objects.equals(expected.getId(), actual.getId())
                        && Objects.equals(expected.getRest_id(), actual.getRest_id());
                return cmp;
            }
    );
}
