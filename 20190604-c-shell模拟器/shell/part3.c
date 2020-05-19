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
#include "part3.h"

int is_glob(char *s)
{
    int i = 0;
    while (s[i] != '\0')
    {
        switch (s[i])
        {
        case '*':
        case '?':
        case '^':
        case '#':
        case '&':
        case '[':
        case ']':
        case '{':
        case '}':
        case '$':
        case '~':
            return 1;

        default:
            break;
        }
        i++;
    }
    return 0;
}

void parse_glob(char **argv, glob_t *buf)
{
    int i = 0, j = 0, k = 0;
    int glob_pos = -1;

    while (argv[i] != NULL)
    {
        if (is_glob(argv[i]) == 1)
        {
            glob_pos = i;
            break;
        }
        i++;
    }
    buf->gl_offs = glob_pos;
    // buf->gl_pathc = glob_pos;

    i = glob_pos;

    // fprintf(stderr, "glob_pos = %d\n", glob_pos);

    while (argv[i] != NULL)
    {
        if (i == glob_pos)
        {
            glob(argv[i], GLOB_DOOFFS, NULL, buf);
        }
        else
        {
            glob(argv[i], GLOB_DOOFFS | GLOB_APPEND, NULL, buf);
        }

        i++;
    }

    for (i = 0; i < glob_pos; i++)
    {
        buf->gl_pathv[i] = argv[i];
    }

    // buf->gl_pathv[i] = NULL;
    // while (argv[i] != NULL)
    // {
    //     argv[i] = NULL;
    //     i++;
    // }

    // execvp(buf->gl_pathv[0], buf->gl_pathv);
}

int execute_with_redir_glob(char **argv, char *in_file, char *out_file, glob_t *buf)
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
            org_stdin = dup(fileno(stdin));
            oldifd = fileno(freopen(in_file, "r", stdin));
        }
        if (strcmp("\0", out_file) != 0)
        {
            org_stdout = dup(fileno(stdout));
            oldofd = fileno(freopen(out_file, "w", stdout));
        }
        //execute external call by execvp
        parse_glob(argv, buf);
        result = execvp(buf->gl_pathv[0], buf->gl_pathv);

        if (result < 0)
        {
            perror(buf->gl_pathv[0]);
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
            if (fdopen(org_stdin, "r") == NULL)
            {
                perror("fdopen org_stdin");
            }
        }

        if (strcmp("\0", out_file) != 0)
        {
            if (fdopen(org_stdout, "w") == NULL)
            {
                perror("fdopen org_stdout");
            }
        }
    }
    return child_info;
}

int execute_with_pipe_and_redir_glob(char **argv1, char **argv2, char *in_file1, char *out_file1, char *in_file2, char *out_file2, glob_t *buf1, glob_t *buf2)
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
            execute_with_redir_glob(argv1, in_file1, out_file1, buf1);
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
            execute_with_redir_glob(argv2, in_file2, out_file2, buf2);
            exit(0);
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