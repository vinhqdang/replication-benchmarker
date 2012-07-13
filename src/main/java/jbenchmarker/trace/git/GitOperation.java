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

import collect.VectorClock;
import crdt.CRDT;
import crdt.simulator.TraceOperation;
import java.util.List;
import jbenchmarker.core.LocalOperation;
import jbenchmarker.core.Operation;
import jbenchmarker.core.SequenceOperation;
import jbenchmarker.trace.git.model.Edition;
import jbenchmarker.trace.git.model.FileEdition;

/**
 * Mapping between couch extracted git trace and SequenceOperation
 * @author urso
 */
public class GitOperation extends TraceOperation {
    SequenceOperation<String> sop;
        
    public GitOperation(int replica, VectorClock VC, FileEdition f, Edition e) {
        super(replica, new VectorClock(VC));
        SequenceOperation.OpType type;
        switch (e.getType()) {
        case DELETE: 
            type = SequenceOperation.OpType.del;
            break;
        case INSERT:
            type = SequenceOperation.OpType.ins;
            break;
        case REPLACE:
            type = SequenceOperation.OpType.update;
            break;
        default:
            type = SequenceOperation.OpType.unsupported;    
        }
        sop = new SequenceOperation<String>(type, /*replica,*/ e.getBeginA(), 
                e.getEndA() - e.getBeginA(), e.getCb()/*, new VectorClock(VC)*/);
    }

    @Override
    public LocalOperation getOperation() {
        return sop;
    }

    @Override
    public String toString() {
        return "GitOperation{" + "sop=" + sop + '}';
    }

   
}
