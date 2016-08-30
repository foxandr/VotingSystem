package fox.alex.votingsystem.utils;

import fox.alex.votingsystem.model.Vote;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by fox on 26.08.16.
 */
public class VoteUtil {

    private VoteUtil() {}

    //resurns voting results with rest_id and number of votes
    public static Map<Integer, Long> getCompliteResults(List<Vote> votes){
        Map<Integer, Long> result = votes.stream()
                .collect(Collectors.groupingBy(v -> v.getRest_id(), Collectors.counting()));
        return result.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e2, LinkedHashMap::new));
    }

    //returns restaurant id
    public static Integer getVotingResult(List<Vote> votes){
        Integer result = null;
        LinkedHashMap<Integer, Long> map = (LinkedHashMap<Integer, Long>) getCompliteResults(votes);
        long maxL = (map.isEmpty()) ? 0L : map.entrySet().iterator().next().getValue();
        List<Integer> resList = map.entrySet().stream()
                .filter(e -> e.getValue() == maxL)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        if (resList.size() == 1) {
            result = resList.get(0);
        } else {
            result = votes.stream()
                    .filter(v -> resList.contains(v.getRest_id()))
                    .sorted((v1, v2) -> v1.getVoted().compareTo(v2.getVoted()))
                    .map(Vote::getRest_id)
                    .findFirst()
                    .get();
        }
        return result;
    }
}
