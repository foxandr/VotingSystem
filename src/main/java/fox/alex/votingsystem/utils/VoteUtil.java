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
        LinkedHashMap<Integer, Long> map = (LinkedHashMap<Integer, Long>) getCompliteResults(votes);
        ArrayList<Integer> resArray = null;
        return null;
    }

    public static void main(String[] args){
        System.out.println("Hello!");
        List<Vote> votes = Arrays.asList(new Vote(1, 1), new Vote(2, 2), new Vote(3, 3), new Vote(4, 3), new Vote(5, 2), new Vote(6, 3));
        Map<Integer, Long> test = getCompliteResults(votes);
        System.out.println(test);
    }

}
