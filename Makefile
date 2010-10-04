CLASSPATH=bin/jl1.0.jar:bin/tritonus_share.jar:bin/jogg-0.0.7.jar:bin/jorbis-0.0.15.jar:bin/vorbisspi1.0.3.jar:bin/mp3spi1.9.4.jar:bin/jflac-1.3.jar:bin

JAVAC=javac
BIN=bin
CLASS_FILES=InterfaceAudioFileDecoder.class \
	MusicFlac.class \
	AbstractNonWaveReader.class\
	MusicWave.class \
	Player.class \
	PlayListModel.class \
	Main.class

	

all: $(CLASS_FILES)

%.class: src/%.java
	$(JAVAC) -d $(BIN) -cp $(CLASSPATH) $<
