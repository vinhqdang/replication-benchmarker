/**
 * Replication Benchmarker
 * https://github.com/score-team/replication-benchmarker/
 * Copyright (C) 2013 LORIA / Inria / SCORE Team
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
package jbenchmarker.ot.ttf;

import jbenchmarker.ot.soct2.OTMessage;
import crdt.Operation;
import jbenchmarker.core.SequenceOperation;

/**
 * This object is only here for compatibility of sequence message
 * its a couple with SOCT2Message and original operation which has generated this message 
 * @author Stephane Martin
 */
public class TTFSequenceMessage implements Operation {

    OTMessage soct2Message;

    /**
     * Soct2Message with original operation 
     * @param soct2Message Message generated by soct2
     * @param o original Operation which have generate the message
     */
    public TTFSequenceMessage(OTMessage soct2Message) {
        this.soct2Message = soct2Message;
    }

    /**
     * 
     * @return the message of operation
     */
    public OTMessage getSoct2Message() {
        return soct2Message;
    }

    /**
     * 
     * @return clone this operation
     */
    @Override
    public Operation clone() {
        return new TTFSequenceMessage((OTMessage)soct2Message.clone());
    }

    @Override
    public String toString() {
        return soct2Message.toString();
    }
}
