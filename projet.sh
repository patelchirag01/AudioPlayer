#!/bin/bash

set -x

param=""
while test $# -gt 0
do
	param="$param $1"
	shift
done

java -cp bin/jl1.0.jar:bin/tritonus_share.jar:bin/jogg-0.0.7.jar:bin/jorbis-0.0.15.jar:bin/vorbisspi1.0.3.jar:bin/mp3spi1.9.4.jar:bin/jflac-1.3.jar:bin Main `echo -n $param`


