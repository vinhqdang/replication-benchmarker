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
package jbenchmarker.rga;

import crdt.Operation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import jbenchmarker.trace.TraceGenerator;
import jbenchmarker.core.SequenceOperation;
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RGADocumentTest {

//  LogootOperation ins(LogootIdentifier n,char c) {
//  return LogootOperation.insert(SequenceOperation.insert(0, 0, null, null), 
//          n,c);
//}
//LogootOperation del(LogootIdentifier n) {
//  return LogootOperation.Delete(SequenceOperation.delete(0, 0, 0, null), n);
//}
//
//
	@Test
	public void testview() 	{
		System.out.println("Test RGADocument ...");
		RGADocument doc = new RGADocument();

		assertEquals("", doc.view());
	}
//
//@Test
//public void testApplyLocal1() 
//{         
// LogootMerge LM = new LogootMerge(new LogootDocument(), 1);
// LogootDocument lg = (LogootDocument) (LM.getDoc());
//        
// LogootIdentifier P = new LogootIdentifier();LogootIdentifier A = new LogootIdentifier();
// LogootIdentifier Q = new LogootIdentifier();LogootIdentifier B = new LogootIdentifier();
//   
// Component c1 = new Component(BigInteger.valueOf(20),4,50);
// Component c2 = new Component(BigInteger.valueOf(21),4,50);
// Component c3 = new Component(BigInteger.valueOf(22),2,100);   
// Component c4 = new Component(BigInteger.valueOf(23),4,50);
//
//   
// P.addComponent(c1);
// Q.addComponent(c2); 
//
// LM.getDoc().apply(ins(P,'e')); //ApplyLocal
// LM.getDoc().apply(ins(Q,'c')); //ApplyLocal
// 
// assertEquals(true,P.isLessThan(Q));
// assertEquals(false,Q.isLessThan(P));
//      
// assertEquals(" ec ", lg.view());
//
// 
// //------   
// A.addComponent(c3);
// B.addComponent(c4); 
//
// LM.getDoc().apply(ins(A,'K')); //ApplyLocal
// LM.getDoc().apply(ins(B,'L')); //ApplyLocal
// 
// assertEquals(true,A.isLessThan(B));
// assertEquals(false,B.isLessThan(B));
// 
// assertEquals(" ecKL ", lg.view());
// 
// //--------
//
// LM.getDoc().apply(del(P)); //ApplyLocal
// LM.getDoc().apply(del(Q)); //ApplyLocal
// 
// assertEquals(" KL ", lg.view());
// 
//}
//
}
