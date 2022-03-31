package ru.job4j.dreamjob.persistence;

import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Candidate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CandidateDBStoreTest {
    @Test
    public void whenFindAll() {
        CandidateDBStore cdbstore = new CandidateDBStore(new Main().loadPool());
        Candidate c1 = cdbstore.add(new Candidate(1, "Candidate", "Candidate_desc", "2020-12-10 15:15:15"));
        Candidate c2 = cdbstore.add(new Candidate(1, "Candidate", "Candidate_desc", "2020-12-10 15:15:15"));
        assertTrue(cdbstore.findAll().contains(c1));
        assertTrue(cdbstore.findAll().contains(c2));
    }

    @Test
    public void whenAddCandidate() {
        CandidateDBStore cdbstore = new CandidateDBStore(new Main().loadPool());
        Candidate c1 = cdbstore.add(new Candidate(1, "Candidate", "Candidate_desc", "2020-12-10 15:15:15"));
        assertThat(cdbstore.findById(c1.getId()).getName(), is(c1.getName()));
    }

    @Test
    public void whenFindById() {
        CandidateDBStore cdbstore = new CandidateDBStore(new Main().loadPool());
        Candidate c1 = cdbstore.add(new Candidate(1, "Candidate1", "Candidate_desc", "2020-12-10 15:15:15"));
        Candidate c2 = cdbstore.add(new Candidate(1, "Candidate2", "Candidate_desc", "2020-12-10 15:15:15"));
        assertThat(cdbstore.findById(c1.getId()).getName(), is(c1.getName()));
        assertThat(cdbstore.findById(c2.getId()).getName(), is(c2.getName()));
    }

    @Test
    public void whenUpdateCandidate() {
        CandidateDBStore cdbstore = new CandidateDBStore(new Main().loadPool());
        Candidate newCandidate = new Candidate(1, "candidate", "Candidate_desc", "2020-12-10 15:15:15");
        Candidate storedCandidate = cdbstore.add(newCandidate);
        storedCandidate.setName("new_candidate");
        cdbstore.update(storedCandidate);
        assertThat(cdbstore.findById(storedCandidate.getId()).getName(), is("new_candidate"));
    }
}
