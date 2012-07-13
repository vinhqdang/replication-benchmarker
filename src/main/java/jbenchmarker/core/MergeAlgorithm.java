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
package jbenchmarker.core;

import crdt.*;
import crdt.simulator.IncorrectTraceException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Squeleton of mergeAlgorithm
 * @author urso
 */
public abstract class MergeAlgorithm extends CRDT<String> implements Serializable{

    private static final boolean DEBUG = false;
    
    // Supported Document
    final private Document doc;
       
    final private List<String> states  = new ArrayList<String>(1);  
    
  /*  private void applyOneRemote(CommutativeMessage mess){
        
    }*/
    /*
     * Constructor
     */
    /**
     * 
     * @param doc Document of this merge algorithm
     * @param siteId SiteID or replicat number
     */
    public MergeAlgorithm(Document doc, int siteId) {
        this.doc = doc;
        this.setReplicaNumber(siteId);
    }

    public MergeAlgorithm(Document doc) {
        this.doc = doc;
    }

    /**
     * Integrate remote message from another replicas
     * To be define by the concrete merge algorithm
     * @param message from another replicas
     * @throws IncorrectTraceException 
     */
    protected abstract void integrateRemote(SequenceMessage message) throws IncorrectTraceException;
            
    /**
     * 
     * @return the document
     */
    public Document getDoc() {
        return doc;
    }

    /**
     * This the mergeAlgorithm return in initial state
     */
    public void reset() {
    }

    /**
     * This function to stay compatible with causal dispatcher 
     * localmodification is performed and a sequence message is transformed to CRDTMessage.
     * @param op local operation
     * @return CRDT message will be sent to another
     * @throws PreconditionException
     */
    @Override
    public CRDTMessage applyLocal(LocalOperation op) throws PreconditionException {
        if (!(op instanceof SequenceOperation)) {
            throw new PreconditionException("Not a sequenceOperation : " + op);
        }
        SequenceOperation opt = (SequenceOperation) op;
        List<SequenceMessage> l;
        switch (opt.getType()) {
        case ins:
            l = localInsert(opt);
            break;
        case del:
            l =  localDelete(opt);
            break;
        case update:
            l = localUpdate(opt);
            break;
        case noop:
            return CRDTMessage.emptyMessage;
        default:
            throw new IncorrectTraceException("Unsupported operation : " + opt);
        }
        
        // FIXME: when l is an empty list, a NullPointerException is thrown...
        
        CRDTMessage m = null;
        for (SequenceMessage n : l) {
            if (m == null) { 
                m = new OperationBasedOneMessage(n);
            } else {
                m=m.concat(new OperationBasedOneMessage(n));
            }
        }        
        if (DEBUG) states.add(doc.view());
        return m;
    }

    final public CRDTMessage insert(int position, String content) throws PreconditionException {
        return applyLocal(SequenceOperation.insert( position, content));
    }

    final public CRDTMessage remove(int position, int offset) throws PreconditionException {
        return applyLocal(SequenceOperation.delete(position, offset));
    }
    
    /**
     * integrate remote operations.
     * @param msg message generated by another replicas
     */
    /*@Override
    public void applyRemote(CRDTMessage msg) {
        ((CommutativeMessage)msg).execute(this);
        
        if (DEBUG) states.add(doc.view());
    }*/
    @Override
    public void applyOneRemote(CRDTMessage mess){
         try {            
             integrateRemote(CRDTMessage2SequenceMessage(mess));
         }catch (IncorrectTraceException ex) {
            throw new IllegalStateException(ex);
        }
    }
    
    public static SequenceMessage CRDTMessage2SequenceMessage(CRDTMessage mess){
        return (SequenceMessage) ((OperationBasedOneMessage) mess).getOperation();
    }
    /**
     * return document 
     * @return
     */
    @Override
    public String lookup() {
        return doc.view();
    }

    /**
     * The defaut behavior of localInsert and localDelete is to call generateLocal.
     * Either localXXXs or generateLocal should be overrriden.
     * @param opt local modification
     * @return Messages generated by replication algorithm will sent to another replicas
     * @throws IncorrectTraceException by default
     */
    protected List<SequenceMessage> localInsert(SequenceOperation opt) throws IncorrectTraceException {
        return generateLocal(opt);
    }

    protected List<SequenceMessage> localDelete(SequenceOperation opt) throws IncorrectTraceException {
        return generateLocal(opt);
    }
    
    /**
     * Default behavior of localUpdate is to throw IncorrectTraceException.
     */
    protected List<SequenceMessage> localUpdate(SequenceOperation opt) throws IncorrectTraceException {
        List<SequenceMessage> lop = localDelete(opt);
        lop.addAll(localInsert(opt));
        return lop;
    }
    
    /**
     * Integreate local modification of the document.
     * To be define by the concrete merge algorithm
     * @param opt local modification
     * @return Messages generated by replication algorithm will sent to another replicas
     * @throws IncorrectTraceException by default
     */
    protected List<SequenceMessage> generateLocal(SequenceOperation opt) throws IncorrectTraceException {
        throw new IncorrectTraceException("Unsupported operation " + opt);
    }
}
