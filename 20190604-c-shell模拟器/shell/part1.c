#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>
#include <string.h>
#include <arpa/inet.h>

int is_pipe(char **argv)
{
    int i = 0;
    //judge if cmd has pipe
    while (argv[i] != NULL)
    {
        if (strcmp("|", argv[i]) == 0)
        {
            //record next cmd start position
            // if (argv[i + 1] != NULL && strcmp("|", argv[i + 1]) != 0)
            // {
            return i + 1;
            // }
        }
        i++;
    }
    return -1;
}

void parse_pipe(char *input[], char *output1[], char *output2[])
{
    int i = 0;
    int size1 = 0;
    int size2 = 0;
    int ret = is_pipe(input);

    while (strcmp(input[i], "|") != 0)
    {
        output1[size1++] = input[i++];
    }
    output1[size1] = NULL;

    int j = ret;
    while (input[j] != NULL)
    {
        output2[size2++] = input[j++];
    }

    output2[size2] = NULL;
}

int execute_with_pipe(char **argv1, char **argv2)
{
    int child_info = -1;
    char *argv21[10];
    char *argv22[10];
    pid_t pid = fork();
    if (pid == -1)
    {
        perror("fork()");
    }
    else if (pid == 0)
    {
        //child process: create a pipe
        int fd[2];
        int ret = pipe(fd);

        if (fork() == 0)
        {
            //child process create a child to execute the argv1
            close(fd[0]);   //grandson closes reading port
            dup2(fd[1], 1); //redirection
            execvp(argv1[0], argv1);
        }
        if (wait(&child_info) == -1)
            perror("wait");

        close(fd[1]); //son closes writing port

        dup2(fd[0], 0); //redirection
        if (is_pipe(argv2) == -1)
        {
            execvp(argv2[0], argv2);
        }
        else
        {
            parse_pipe(argv2, argv21, argv22);
            child_info = execute_with_pipe(argv21, argv22);
        }
        exit(0);
    }
    else
    {
        //parent
        if (wait(&child_info) == -1)
            perror("wait");
    }

    return child_info;
}