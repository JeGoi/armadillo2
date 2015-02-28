-- 				---^^^^^^^^^---
--  ---====================================---
-- --- 	Armadillo tables creation			---
-- ---			Created 2009-06-08			---
-- ---			Copyright 2009				---
-- ---		Leclercq Mickael, Lord Etienne,	---
-- --- Banir� Abdoulaye Diallo, Boc Alix	---
--  ---====================================---
-- 				---___***___---

-- Updated: 15 June 2009
-- Updated: 7 October 2009 - Etienne

--
-- CREATE TABLE: Project (Identification of this Armadillo project)
--
CREATE TABLE Project
(
	Project_id INTEGER PRIMARY KEY,
	ProjectName TEXT,
	Author TEXT,
	Institution TEXT,
	Note TEXT,
	DateCreated TEXT
);

--
-- CREATE TABLE: RunPrograms (join table between Programs and Params)
--
CREATE TABLE RunPrograms
(
	runProgram_id INTEGER NOT NULL,
	properties_id INTEGER,
	workflows_id INTEGER,
	runProgramOutput TEXT,
	note TEXT,
	programTimeStart TIME,
	programTimeEnd TIME,
	status INTEGER,
	PRIMARY KEY (runProgram_id)
);


--
-- CREATE TABLE: Sequence, contain original sequences from genome databases. Id's databases correspond to access numbers.
-- Note: abbreviate is a name compatible with the phylip format (MAX 9 character)
-- NOTE: sequence_type IS DNA, RNA, AA....
CREATE TABLE Sequence
(
	sequence_id INTEGER NOT NULL,
	name VARCHAR(50),
	abbreviate TEXT,
	accession TEXT,
	accessionReferee TEXT,
	gi TEXT,
	sequence TEXT,
	sequence_len INTEGER,
	sequence_type TEXT,   
	note TEXT,
	timeAdded TIME,
	filename TEXT,
	runProgram_id INTEGER DEFAULT 0,
	PRIMARY KEY (sequence_id)
);

--
-- CREATE TABLE: SequenceStats, contain original statistic regarding this sequence
-- TO DO: Add protein AA

CREATE TABLE SequenceStats
(
	sequenceStats_id INTEGER NOT NULL,
	sequence_id INTEGER,
	sequence_quality TEXT,
	FOREIGN KEY (sequence_id) REFERENCES Sequence (sequence_id) ON DELETE CASCADE,
	PRIMARY KEY (sequenceStats_id)
);

--
-- CREATE TABLE: GenbankFile to keep the file with the db
-- TO DO: Add protein AA
--
CREATE TABLE GenBankFile 
(
	genbankfile_id INTEGER NOT NULL,
	sequence_id INTEGER NOT NULL,
	Genbank TEXT, 
	FOREIGN KEY (sequence_id) REFERENCES Sequence (sequence_id) ON DELETE CASCADE,
	PRIMARY KEY (genbankfile_id)
);


--
-- CREATE TABLE: NonAlignedSequences, contain several original sequences non aligned. Name can be considered as a filename.
--
-- MultipleSequences
CREATE TABLE MultipleSequences
(
	sequence_id INTEGER NOT NULL,
	multipleSequences_id INTEGER NOT NULL,
	name VARCHAR(50),
	note TEXT,
	runProgram_id INTEGER DEFAULT 0,
	FOREIGN KEY (sequence_id) REFERENCES Sequence (sequence_id) ON DELETE CASCADE
);
--CREATE INDEX NonAlignedSequences_index ON NonAlignedSequences (nonAlignedSequence_id);

--
-- CREATE TABLE: AlignedSequences, contain several aligned sequences after alignement in a program. Name can be considered as a filename where all aligned sequence from the same alignedSequence_id group are gathered. Sequence is a sequence aligned with others from the same alignedSequence_id group.
-- Note: sequence_id is this sequence original ID before alignment
-- Note: sequenceStats_id is the new alignedSequenceStats
CREATE TABLE Alignment
(
	alignment_id INTEGER NOT NULL,
	sequence_id  INTEGER NOT NULL,
	original_sequence_id INTEGER NOT NULL,
	runProgram_id INTEGER DEFAULT 0,	
	name VARCHAR(50),
	note TEXT,
	FOREIGN KEY (runProgram_id)	REFERENCES RunPrograms (runProgram_id) ON DELETE CASCADE,
	FOREIGN KEY (sequence_id) REFERENCES Sequence (sequence_id) ON DELETE CASCADE
);



--CREATE INDEX AlignedSequences_index ON AlignedSequences (AlignedSequence_id);


--
-- CREATE TABLE: SequencesAlignments. Join table between AlignedSequence and NonAlignedSequence. All sequences in a NonAlignedSequence file should be aligned and put in the AlignedSequence table. This table do the jonction between both to have the source of the alignment. 
--
CREATE TABLE SequencesAlignments
(
	alignment_id INTEGER NOT NULL,
	multipleSequence_id INTEGER NOT NULL,
	FOREIGN KEY (alignment_id) REFERENCES Alignment(alignment_id)  ON DELETE CASCADE,
	FOREIGN KEY (multipleSequence_id) REFERENCES MultipleSequences (multipleSequence_id) ON DELETE CASCADE	
);


--
-- CREATE TABLE: Trees. Contain all trees with the program used to make them
--
CREATE TABLE Tree
(
	tree_id INTEGER NOT NULL,
	runProgram_id INTEGER DEFAULT 0,
	tree TEXT,
	treeSequenceID TEXT,
	treeAbbreviate TEXT,
	note TEXT,
	rooted BOOLEAN DEFAULT FALSE,
	PRIMARY KEY (tree_id),
	FOREIGN KEY (runProgram_id) REFERENCES RunPrograms (runProgram_id) ON DELETE CASCADE
);

--
-- CREATE TABLE: TreesSequences. Join table between Trees and AlignedSequences. Allow to make the link between the tree and all aligned sequences used to make it.
--
CREATE TABLE TreesSequences
(
	alignment_id INTEGER NOT NULL,
	tree_id INTEGER NOT NULL,
	FOREIGN KEY (alignment_id) REFERENCES Alignment(alignment_id) ON DELETE CASCADE,
	FOREIGN KEY (tree_id) REFERENCES Tree (tree_id) ON DELETE CASCADE
	
);

-- MultipleSequences
CREATE TABLE MultipleTrees
(
	tree_id INTEGER NOT NULL,
	multipleTrees_id INTEGER NOT NULL,
	name VARCHAR(50),
	note TEXT,
	runProgram_id INTEGER DEFAULT 0,
	FOREIGN KEY (tree_id) REFERENCES Tree (tree_id) ON DELETE CASCADE
);

--
-- CREATE TABLE: Ancestors. Contain ancestors
--
CREATE TABLE Ancestor
(
	ancestor_id INTEGER NOT NULL,
	alignment_id INTEGER NOT NULL,
	runProgram_id INTEGER DEFAULT 0,
	ancestor TEXT,
	note TEXT,
	PRIMARY KEY (ancestor_id),
	FOREIGN KEY (alignment_id) REFERENCES Alignment (alignment_id) ON DELETE CASCADE,
	FOREIGN KEY (runProgram_id) REFERENCES RunPrograms (runProgram_id) ON DELETE CASCADE
);

--
-- CREATE TABLE: AncestorsSequence. Join table between Ancestors and Trees.
--
CREATE TABLE AncestorsSequences
(
	tree_id INTEGER NOT NULL,
	ancestor_id INTEGER NOT NULL,
	FOREIGN KEY (tree_id) REFERENCES Tree (tree_id) ON DELETE CASCADE,
	FOREIGN KEY (ancestor_id) REFERENCES Ancestor (ancestor_id) ON DELETE CASCADE
);

--
-- CREATE TABLE: Properties. Properties of differents object/Program
-- NON ACCESSIBLE
-- Note: Deprecated -Etienne
CREATE TABLE Properties
(
	properties_id INTEGER NOT NULL,
	properties_name VARCHAR(200),
	PRIMARY KEY (properties_id)
);

--
-- CREATE Table PropertiesValues. Key and Values associated to a Properties_ID
-- NON ACCESSIBLE
-- Note: Deprecated -Etienne
CREATE TABLE PropertiesValues 
(
	properties_id INTEGER NOT NULL,
	Key_name VARCHAR(200),
	Key_value VARCHAR(200),
	FOREIGN KEY (properties_id) REFERENCES Properties(properties_id) ON DELETE CASCADE
	);

--
-- CREATE TABLE: Workflows
-- 
-- Note: Etienne Lord
CREATE TABLE Workflows
(
	workflows_id INTEGER NOT NULL,
	workflows_name VARCHAR(255),
	workflows_filename VARCHAR(255),
	workflow_in_txt TEXT,
	note TEXT,
	date_created TIME,
	date_modified TIME,
	PRIMARY KEY (workflows_id)
);

--
-- CREATE TABLE: Objects. Objects in the workflow
-- NON ACCESSIBLE
-- Note: Deprecated -Etienne
CREATE TABLE Objects
(
	objects_id INTEGER NOT NULL,
	workflows_id INTEGER NOT NULL,
	objects_name VARCHAR(255),
	properties_id INTEGER NOT NULL,
	PRIMARY KEY (objects_id),
	FOREIGN KEY (workflows_id) REFERENCES Workflows (workflows_id) ON DELETE CASCADE,
	FOREIGN KEY (properties_id) REFERENCES Properties (properties_id) ON DELETE CASCADE
);

--
-- CREATE TABLE: Connectors. Connectors between objetcs in the workflow
-- NON ACCESSIBLE
-- Note: Edge between objects
-- Note: Deprecated -Etienne
CREATE TABLE Connectors
(
	connectors_id INTEGER NOT NULL,
	workflows_id INTEGER NOT NULL,
	properties_id INTEGER,
	source_id INTEGER NOT NULL,
	destination_id INTEGER NOT NULL,
	PRIMARY KEY (connectors_id),
	FOREIGN KEY (workflows_id) REFERENCES Workflows (workflows_id) ON DELETE CASCADE,
	FOREIGN KEY (properties_id) REFERENCES Properties (properties_id) ON DELETE CASCADE
);

--
-- CREATE TABLE: Matrix
-- 
CREATE TABLE Matrix
(
	matrix_id INTEGER NOT NULL,
	matrix TEXT,
	Name TEXT,
	Note TEXT,
	runProgram_id INTEGER DEFAULT 0,
	PRIMARY KEY (matrix_id)
);

--
-- CREATE TABLE: Phylip
-- 
CREATE TABLE Phylip
(
	phylip_id INTEGER NOT NULL,
	phylip_data TEXT,
	phylip_datatype TEXT,
	Name TEXT,
	Note TEXT,
	runProgram_id INTEGER DEFAULT 0,
	PRIMARY KEY (phylip_id)
);

--
-- CREATE TABLE: Unknown
-- 
CREATE TABLE Unknown
(
	Unknown_id INTEGER NOT NULL,
	Unknown TEXT, 
	Name TEXT,
	filename TEXT,
	Note TEXT,
	UnknownType TEXT,
	runProgram_id INTEGER DEFAULT 0,
	PRIMARY KEY (Unknown_id)
);

--
-- CREATE TABLE: BlastHit
--
CREATE TABLE BlastHit
(
    BlastHit_id INTEGER NOT NULL,
    dbname TEXT,
    query_id INTEGER NOT NULL,
    query_name TEXT,
	subject_id INTEGER NOT NULL,
	subject_id_gi TEXT,
	subject_accession TEXT,
	subject_accession_referree TEXT,
    subject_name TEXT,
	qstrand TEXT,
	sstrand TEXT,
	evalue DOUBLE,
	bitscore DOUBLE,
	identify FLOAT,
	alignment_length FLOAT,
    query_length FLOAT,
    subject_length FLOAT,
    positives FLOAT,
    missmatches FLOAT,
    gap FLOAT,
    qstart FLOAT,
	qend FLOAT,
	sstart FLOAT,
	ssend FLOAT,
	score FLOAT,
    PRIMARY KEY (BlastHit_id)
);

