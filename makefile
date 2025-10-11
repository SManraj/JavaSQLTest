JAR = mysql-connector-j-9.4.0/mysql-connector-j-9.4.0.jar
WORDS = words_sql.java
WORDSCLASS = words_sql

TEXT = text_files_sql.java
TEXTCLASS = text_files_sql


words:
	javac -cp .:$(JAR) $(WORDS)
	java -cp .:$(JAR) $(WORDSCLASS)

text: 
#	javac -cp .:$(JAR) $(TEXT)
#	java -cp .:$(JAR) $(CLASS)

	javac $(TEXT)
	java $(TEXTCLASS)

clean: 
	rm -rf *.class