objects = hzip.o huffman.o wc.o hash.o
hzip:$(objects)
	cc -g -o hzip $(objects)
hzip.o:hzip.c
	cc -g -c hzip.c
huffman.o:huffman.c
	cc -g -c huffman.c
wc.o:wc.c
	cc -g -c wc.c
hash.o:
	cc -g -c hash.c
.PHONY:clean
clean:
	-rm hzip $(objects)
