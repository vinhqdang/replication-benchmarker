/**
 * Replication Benchmarker
 * https://github.com/score-team/replication-benchmarker/ Copyright (C) 2013
 * LORIA / Inria / SCORE Team
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package jbenchmarker.treedoc;

import crdt.Operation;
import java.util.List;
import jbenchmarker.core.SequenceOperation.OpType;

/**
 *
 * @author mzawirski
 */
public class TreedocOperation<T> implements Operation {

    private final TreedocIdentifier id;
    private final List<T> content;
    private final OpType type;

    public TreedocOperation(OpType type, TreedocIdentifier id, List<T> content) {
        this.id = id;
        this.content = content;
        this.type = type;
    }

    /**
     * Creates Treedoc operation with dummy content.
     *
     * @param o original trace operation.
     * @param id treedoc position identifier.
     */
    public TreedocOperation(final TreedocIdentifier id) {
        this.type = OpType.delete;
        this.id = id;
        this.content = null; // Dummy.
    }

    /**
     * Creates Treedoc operation with provided content.
     *
     * @param o original trace operation.
     * @param id treedoc position identifier.
     * @param content content expressed as a single character.
     */
    public TreedocOperation(final TreedocIdentifier id, final List<T> content) {
        this.type = OpType.insert;
        this.id = id;
        this.content = content;
    }

    public OpType getType() {
        return type;
    }

    public TreedocIdentifier getId() {
        return id;
    }

    public List<T> getContent() {
        return content;
    }

    @Override
    public TreedocOperation clone() {
        return new TreedocOperation(type, id.clone(), content);
    }
}
