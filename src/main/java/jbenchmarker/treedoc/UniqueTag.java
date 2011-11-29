/**
 * Replication Benchmarker
 * https://github.com/score-team/replication-benchmarker/
 * Copyright (C) 2011 INRIA / LORIA / SCORE Team
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
package jbenchmarker.treedoc;

/**
 * Unique tag of an operation, implemented as timestamp pair: replica identifier
 * and counter.
 * 
 * @author mzawirski
 */
public class UniqueTag implements Comparable<UniqueTag> {
	private final int replicaId;
	private final int counter;

	public UniqueTag(final int replicaId, final int counter) {
		this.replicaId = replicaId;
		this.counter = counter;
	}

	@Override
	public int compareTo(UniqueTag o) {
		if (replicaId != o.replicaId) {
			return replicaId - o.replicaId;
		}
		return counter - o.counter;
	}

	public UniqueTag clone() {
		return new UniqueTag(replicaId, counter);
	}
}