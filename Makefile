CLASSPATH=./API/VorbisSPI1.0.3/lib/tritonus_share.jar:./API/VorbisSPI1.0.3/lib/jogg-0.0.7.jar:./API/VorbisSPI1.0.3/lib/jorbis-0.0.15.jar:./API/VorbisSPI1.0.3/vorbisspi1.0.3.jar:./bin

JAVAC=javac
BIN=bin

all: MusicOGG.class MusicWave.class

%.class: src/%.java
	$(JAVAC) -d $(BIN) -cp $(CLASSPATH) $<