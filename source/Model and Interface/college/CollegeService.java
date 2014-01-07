package org.hisp.dhis.college;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: SVC
 * Date: 12/18/13
 * Time: 11:47 PM
 * To change this template use File | Settings | File Templates.
 */
public interface CollegeService {
    int add(College college);
    void update(College college);
    void delete(int id);
    College get(int id);
    Collection<College> getAll();
}
