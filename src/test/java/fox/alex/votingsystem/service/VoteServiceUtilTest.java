package fox.alex.votingsystem.service;

import fox.alex.votingsystem.model.Vote;
import fox.alex.votingsystem.utils.VoteUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by fox on 29.08.16.
 */
public class VoteServiceUtilTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    public void getCompliteResultsTest() throws Exception {
        List<Vote> votes = service.getAllByDate(LocalDateTime.now());
        Map<Integer, Long> results = VoteUtil.getCompliteResults(votes);
        Map<Integer, Long> expected = new LinkedHashMap<>();
        expected.put(4, 2L);
        expected.put(5, 1L);
        Assert.assertEquals(expected, results);
    }

    @Test
    public void getVotingResultTest() throws Exception {
        List<Vote> votes = service.getAllByDate(LocalDateTime.now());
        Integer result = VoteUtil.getVotingResult(votes);
        Assert.assertEquals(Integer.valueOf(4), result);
    }

}