Replication Benchmarker
=======

https://github.com/score-team/replication-benchmarker/
Copyright (C) 2015 Université de Lorraine / LORIA / Inria / COAST Team

ReplicationBenchmarker is a performance evaluation framework for replication mechanisms
used in collaborative applications.

=======


 The main class for evaluation is crdt.simulator.CausalSimulator

* You create a Simulator associated to a replica factory (crdt.Factory) that creates CRDT (crdt.CRDT) instances. There are factories for
- Treedoc  : jbenchmarker.factories.TreedocFactory
- Logoot : several versions, best one is jbenchmarker.factories.LogootListFactory.ByteList
- WOOT : several versions, best one is jbenchmarker.factories.WootFactories.WootHFactory
- others : sequences, sets, unordered and ordered tree, ... see jbenchmarker.factories....
- your(s)

* You run it on a trace (a list of operations) that can be
- extracted from a XML file jbenchmarker.trace.TraceGenerator
- generated randomly crdt.simulator.random.RandomTrace
- extracted from git repository jbenchmarker.trace.git.GitTrace
To use git extraction you a need a CouchDB instance accessible.

* You get the result with Simulator methods :
- details : getMemUsed(), replicaGenerationTimes(), getRemoteTimes()
- sum and average : getSumXXX getAvgXXX

======

Update on 12/Aug/2015

Now you can run with MainResult class. It will get the parameters and iterate through different parameters to generate multiple result test.
