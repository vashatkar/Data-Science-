

Hadoop/MapReduce PageRank algorithm code

Code author
-----------
Vashatkar

Execution
---------
All of the build & execution commands are organmvn -vized in the Makefile.
1) Unzip project file.
Also, download and put the input file in the 'input' folder.
2) Open command prompt.
3) Navigate to directory where project files unzipped.
4) Edit the Makefile to customize the environment at the top.
	Sufficient for standalone: hadoop.root, jar.name, local.input
	Other defaults acceptable for running standalone.

5) For running the Standalone Hadoop version, use the following command:
	make switch-standalone		-- set standalone Hadoop environment (execute once)
	make local
6) For Pseudo-Distributed Hadoop version, use the following commands: (https://hadoop.apache.org/docs/current/hadoop-project-dist/hadoop-common/SingleCluster.html#Pseudo-Distributed_Operation)
	make switch-pseudo			-- set pseudo-clustered Hadoop environment (execute once)
	make pseudo					-- first execution
	make pseudoq				-- later executions since namenode and datanode already running 
7) For AWS EMR Hadoop execution, use the following commands: (you must configure the emr.* config parameters at top of Makefile)
	make upload-input-aws		-- only before first execution
	make aws					-- check for successful execution with web interface (aws.amazon.com)
	make download-output-aws			-- after successful execution & termination
}


Input:
-------------------------
For input, Create "input" directory and place the dataset into that directory for execution

