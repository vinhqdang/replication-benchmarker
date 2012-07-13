/**
 * Replication Benchmarker
 * https://github.com/score-team/replication-benchmarker/
 * Copyright (C) 2012 LORIA / Inria / SCORE Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package jbenchmarker.trace.git;

import java.util.List;
import jbenchmarker.trace.git.model.Commit;
import org.ektorp.CouchDbConnector;
import org.ektorp.ViewQuery;
import org.ektorp.support.CouchDbRepositorySupport;
import org.ektorp.support.View;

/**
 *
 * @author urso
 */
public class CommitCRUD extends CouchDbRepositorySupport<Commit> {

    public CommitCRUD(CouchDbConnector db) {
        super(Commit.class, db);
    }
    
    @View(name = "init", map = "function(doc) { if (empty doc.parents) { emit(null, doc) } }")
    public List<Commit> getInit() {
        ViewQuery q = createQuery("init").descending(true);
        return db.queryView(q, Commit.class);
    }
}
