objects = reversepolish.o stack.o getop.o
calculator:$(objects)
	cc -o calculator $(objects)
reversepolish.o:reversepolish.c
	cc -c reversepolish.c
stack.o:stack.c
	cc -c stack.c
getop.o:getop.c getch.h
	cc -c getop.c
.PHONY:clean
clean:
	-rm calculator $(objects)
