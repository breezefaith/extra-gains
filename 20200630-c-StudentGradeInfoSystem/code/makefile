main : main.o func.o student.o
	gcc -g -o main main.o func.o student.o

main.o : main.c func.h student.h
	gcc -g -fexec-charset=GBK -finput-charset=UTF-8 -c main.c

func.o : func.c func.h student.h
	gcc -g -fexec-charset=GBK -finput-charset=UTF-8 -c func.c

student.o : student.c student.h
	gcc -g -fexec-charset=GBK -finput-charset=UTF-8 -c student.c

clean :
	del main.o func.o student.o main.exe
