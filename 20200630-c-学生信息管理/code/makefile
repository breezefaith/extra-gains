main : main.o func.o student.o
	gcc -o main main.o func.o student.o

main.o : main.c func.h student.h
	gcc -fexec-charset=gbk -finput-charset=gbk -c main.c

func.o : func.c func.h student.h
	gcc -fexec-charset=gbk -finput-charset=gbk -c func.c

student.o : student.c student.h
	gcc -fexec-charset=gbk -finput-charset=gbk -c student.c

clean :
	del main.o func.o student.o main.exe
