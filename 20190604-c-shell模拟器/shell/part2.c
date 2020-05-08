#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>
#include <string.h>
#include <arpa/inet.h>
#include <sys/types.h>
#include <fcntl.h>
#include <errno.h>

#include "smsh.h"
#include "part2.h"

int has_in_redir(char **argv)
{
    int in_redir_pos = -1;
    int in_redir_num = 0;
    int i = 0;

    while (argv[i] != NULL)
    {
        if (strcmp("<", argv[i]) == 0)
        {
            in_redir_pos = i + 1;
            in_redir_num++;
        }
        i++;
    }

    if (in_redir_num > 1 || argv[in_redir_pos] == NULL || in_redir_pos == 0)
    {
        in_redir_pos = -2;
    }

    return in_redir_pos;
}

int has_out_redir(char **argv)
{
    int out_redir_pos = -1;
    int out_redir_num = 0;
    int i = 0;

    while (argv[i] != NULL)
    {
        if (strcmp(">", argv[i]) == 0)
        {
            out_redir_pos = i + 1;
            out_redir_num++;
        }
        i++;
    }

    if (out_redir_num > 1 || argv[out_redir_pos] == NULL || out_redir_pos == 0)
    {
        out_redir_pos = -2;
    }

    return out_redir_pos;
}

void parse_redir(char **argv, char **argv1, int in_pos, char *in_file, int out_pos, char *out_file)
{
    int i = 0;
    int end_pos = -1;

    if (in_pos == -2)
    {
        perror("in redir error!");
    }
    else if (in_pos == -1)
    {
    }
    else
    {
        strcpy(in_file, argv[in_pos]);
        end_pos = in_pos - 1;
    }

    if (out_pos == -2)
    {
        perror("out redir error!");
    }
    else if (out_pos == -1)
    {
    }
    else
    {
        strcpy(out_file, argv[out_pos]);
        if (in_pos > 0)
        {
            end_pos = in_pos < out_pos ? end_pos : out_pos - 1;
        }
        else
        {
            end_pos = out_pos - 1;
        }
    }
    // printf("%d\n", end_pos);
    if (end_pos == -1)
    {
        while (argv[i] != NULL)
        {
            argv1[i] = argv[i];
            i++;
        }
    }
    else
    {
        for (i = 0; i < end_pos; i++)
        {
            argv1[i] = argv[i];
        }
    }

    argv1[i] = NULL;
}

int execute_with_redir(char **argv, char *in_file, char *out_file)
{
    //create child process
    pid_t pid = fork();
    //child process's status
    int result = 0;
    //input file descriptor
    int oldifd = 1;
    //output file descriptor
    int oldofd = 1;
    int newifd = 1;
    int newofd = 1;
    int org_stdout = 1;
    int org_stdin = 0;

    int child_info = -1;

    if (pid < 0) //child process creation failed.
    {
        perror("fork()");
    }
    else if (pid == 0) //child process
    {
        if (strcmp("\0", in_file) != 0)
        {
            org_stdin =  dup(fileno(stdin));
            oldifd = fileno(freopen(in_file, "r", stdin));
        }
        if (strcmp("\0", out_file) != 0)
        {
            org_stdout = dup(fileno(stdout));
            oldofd = fileno(freopen(out_file, "w", stdout));
        }
        //execute external call by execvp
        result = execvp(argv[0], argv);

        if (result < 0)
        {
            perror("execvp");
            exit(1);
        }
        exit(0);
    }
    else //parent process
    {
        //parent
        if (wait(&child_info) == -1)
            perror("wait 195");

        if (strcmp("\0", in_file) != 0)
        {
            if(fdopen(org_stdin, "r") == NULL)
            {
                perror("fdopen org_stdin");
            }
        }

        if (strcmp("\0", out_file) != 0)
        {
            if(fdopen(org_stdout, "w") == NULL)
            {
                perror("fdopen org_stdout");
            }
        }
    }
    return child_info;
}

int execute_with_pipe_and_redir(char **argv1, char **argv2, char *in_file1, char *out_file1, char *in_file2, char *out_file2)
{
    int child_info = -1;

    pid_t pid = 0;
    pid = fork();

    if (pid == -1)
    {
        perror("fork()");
    }
    else if (pid == 0)
    {
        //child process: create a pipe
        int fd[2];
        int ret = pipe(fd);
        int child_pid = fork();
        if (child_pid == -1)
        {
            perror("fork 235");
        }
        else if (child_pid == 0)
        {
            //child process create a child to execute the argv1
            close(fd[0]);               //grandson closes reading port
            dup2(fd[1], STDOUT_FILENO); //redirection
            // close(fd[1]);
            // dup2(fd[1], 1); //redirection
            execute_with_redir(argv1, in_file1, out_file1);
            exit(0);
        }
        else
        {
            if (wait(&child_info) == -1)
                perror("wait 249");

            close(fd[1]); //son closes writing port

            dup2(fd[0], STDIN_FILENO); //redirection
            // close(fd[0]);
            // dup2(fd[0], 0); //redirection
            // execvp(argv2[0], argv2);
            execute_with_redir(argv2, in_file2, out_file2);
            exit(errno);
        }
    }
    else
    {
        //parent
        if (wait(&child_info) == -1)
            perror("wait");
    }

    return child_info;
}