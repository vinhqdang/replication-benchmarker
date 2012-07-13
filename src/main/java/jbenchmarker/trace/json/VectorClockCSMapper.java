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

import collect.VectorClock;
import java.util.HashMap;

/**
 *
 * @author damien
 */
public class VectorClockCSMapper {
    private HashMap<String, Integer> tabUId;

    public VectorClockCSMapper() {
       this.tabUId = new HashMap<String, Integer>();
    }
      
    public Integer userId(String key){
        if(!this.tabUId.containsKey(key)){           
           this.tabUId.put(key,tabUId.size());
       }
        return tabUId.get(key);
    }
    
    public VectorClock toVectorClock(VectorClockCS vcs) {
        VectorClock res = new VectorClock();
        
        for (String key : vcs.keys()) {
            res.put(userId(key), vcs.get(key));
        }
        return res;
    }
}
