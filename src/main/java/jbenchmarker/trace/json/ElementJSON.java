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
package jbenchmarker.trace.json;

/**
 * @author Damien Flament
 */

public class ElementJSON {
    private String key;
    private ElementCS val;

    public ElementJSON(String k, ElementCS v) {
        this.key = k;
        this.val = v;
    }

    public String getKey() {
        return key;
    }

    public ElementCS getVal() {
        return val;
    }
      
    @Override
    public String toString() {
        return "ElementJSON{" + "key=" + key + ", val=" + val + '}';
    }
}
