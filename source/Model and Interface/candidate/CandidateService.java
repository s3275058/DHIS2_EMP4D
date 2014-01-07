package org.hisp.dhis.candidate;

import java.util.Collection;

public interface CandidateService {
    int add(Candidate candidate);
    void update(Candidate candidate);
    void delete(int id);
    Candidate get(int id);
    Collection<Candidate> getAll();
}
