#include <iostream>
#include <fstream>
#include <string>
#include <cstdio>
#include <cstdlib>
#include <cstring>

#ifndef __USE_GNU
#define __USE_GNU
#endif

#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <fcntl.h>
#include <signal.h>
#include <errno.h>

#include "shell.h"

//define some macro to set font color
#define TEXT_CLOSE()   \
    printf("\033[0m"); \
    fprintf(stderr, "\033[0m") //close colorful font
#define TEXT_RED()      \
    printf("\033[31m"); \
    fprintf(stderr, "\033[31m") //red font
#define TEXT_GREEN()    \
    printf("\033[32m"); \
    fprintf(stderr, "\033[32m") //green font
#define TEXT_YELLOW()   \
    printf("\033[33m"); \
    fprintf(stderr, "\033[33m") //yellow font
#define TEXT_BLUE()     \
    printf("\033[34m"); \
    fprintf(stderr, "\034[34m") //blue font

using namespace std;
/**
 * function for external call
*/
int execute_cmd(struct command *cmd);
int execute_back_cmd(struct command *cmd);

int main(int argc, char *argv[])
{
    //cmd string
    char cmds[512] = {0};

    //if no arguments
    if (argc == 1)
    {
        //set green font
        TEXT_GREEN();

        fprintf(stderr, "shell> ");

        //loop until eof
        while (!cin.eof())
        {
            //get cmds from stdin
            cin.getline(cmds, 513);
            struct command *cmd = parse_command(cmds);

            //cmds are whitespace characters
            if (cmd->args[0] == NULL)
            {
                free_command(cmd);
                fprintf(stderr, "shell> ");
                continue;
            }

            //built-in command cd
            if (strcmp(cmd->args[0], "cd") == 0)
            {
                if (cmd->args[1] == NULL)
                {
                    char *home = getenv("HOME");
                    if (home != NULL)
                    {
                        chdir(home);
                    }
                    else
                    {
                        perror("No home directory?");
                    }
                }
                else
                {
                    if (chdir(cmd->args[1]) < 0)
                    {
                        char s[200] = "Could not chdir to ";
                        strcat(s, cmd->args[1]);
                        perror(s);
                    }
                }
                free_command(cmd);
            }
            //built-in command setenv
            else if (strcmp(cmd->args[0], "setenv") == 0)
            {
                if (cmd->args[1] != NULL && cmd->args[2] != NULL)
                {
                    if (setenv(cmd->args[1], cmd->args[2], 1) < 0)
                    {
                        perror("Could not setenv");
                    }
                }
                else if (cmd->args[1] != NULL && cmd->args[2] == NULL)
                {
                    unsetenv(cmd->args[1]);
                }
                else
                {
                    perror("setenv arguments error");
                }
                free_command(cmd);
            }
            //built-in command jobs
            else if (strcmp(cmd->args[0], "jobs") == 0)
            {
                FILE *fp = popen("jobs -l", "r");
                char buf[300] = {0};
                while (fgets(buf, 301, fp) != NULL)
                {
                    fprintf(stderr, "%s\n", buf);
                }
                free_command(cmd);
            }
            //built-in command exit
            else if (strcmp(cmd->args[0], "exit") == 0)
            {
                free_command(cmd);
                exit(0);
            }
            //command external call
            else
            {
                // if(cmds[strlen(cmds)-1]=='&'){
                //     execute_back_cmd(cmd);
                // }else{
                execute_cmd(cmd);
                // }
                free_command(cmd);
            }

            fprintf(stderr, "shell> ");
        }
        //close font color
        TEXT_CLOSE();
    }
    else
    {
        /**
         * code of this branch is similar to the above
        */
        //open file input stream
        ifstream infile(argv[1]);
        if (!infile.is_open())
        {
            perror("Error opening file");
            exit(1);
        }
        while (!infile.eof())
        {
            infile.getline(cmds, 513);
            struct command *cmd = parse_command(cmds);
            if (cmd->args[0] == NULL)
            {
                free_command(cmd);
                continue;
            }
            if (strcmp(cmd->args[0], "cd") == 0)
            {
                if (cmd->args[1] == NULL)
                {
                    char *home = getenv("HOME");
                    if (home != NULL)
                    {
                        chdir(home);
                    }
                    else
                    {
                        perror("No home directory?");
                    }
                }
                else
                {
                    if (chdir(cmd->args[1]) < 0)
                    {
                        char s[200] = "Could not chdir to ";
                        strcat(s, cmd->args[1]);
                        perror(s);
                    }
                }
                free_command(cmd);
            }
            else if (strcmp(cmd->args[0], "setenv") == 0)
            {
                if (cmd->args[1] != NULL && cmd->args[2] != NULL)
                {
                    if (setenv(cmd->args[1], cmd->args[2], 1) < 0)
                    {
                        perror("Could not setenv");
                    }
                }
                else if (cmd->args[1] != NULL && cmd->args[2] == NULL)
                {
                    unsetenv(cmd->args[1]);
                }
                else
                {
                    perror("setenv arguments error");
                }
                free_command(cmd);
            }
            else if (strcmp(cmd->args[0], "exit") == 0)
            {
                free_command(cmd);
                exit(0);
            }
            else
            {
                execute_cmd(cmd);
                free_command(cmd);
            }
        }
        infile.close();
    }
    return 0;
}

/**
 * function for external call
*/
int execute_cmd(struct command *cmd)
{
    //create child process
    pid_t pid = fork();
    //child process's status
    int status = 1;
    //input file descriptor
    int oldifd = 1;
    //output file descriptor
    int oldofd = 1;
    int newifd = 1;
    int newofd = 1;

    if (pid < 0) //child process creation failed.
    {
        perror("fork failed");
    }
    else if (pid == 0) //child process
    {
        if (cmd->in_redir != NULL)
        {
            //open input redirection file
            oldifd = open(cmd->in_redir, O_RDONLY);
            if (oldifd < 0)
            {
                signal(SIGINT, SIG_DFL); //reset interrupt signal
                perror("open in_redir failed");
                exit(1);
                return -1;
            }
            else
            {
                //redirect from stdin
                newifd = dup2(oldifd, STDIN_FILENO);
                if (newifd < 0) //redirect failed
                {
                    signal(SIGINT, SIG_DFL);
                    perror("dup2 in_redir failed");
                    exit(1);
                    return -1;
                }
            }
        }
        if (cmd->out_redir != NULL)
        {
            //open output redirection file
            oldofd = open(cmd->out_redir, O_WRONLY | O_CREAT | O_TRUNC, 0666);
            if (oldifd < 0)
            {
                signal(SIGINT, SIG_DFL);
                perror("open out_redir failed");
                exit(1);
                return -1;
            }
            else
            {
                newofd = dup2(oldofd, STDOUT_FILENO);
                if (newofd < 0)
                {
                    signal(SIGINT, SIG_DFL);
                    perror("dup2 out_redir failed");
                    exit(1);
                    return -1;
                }   
            }
        }

        //execute external call by execvp
        if (execvp(cmd->args[0], cmd->args) < 0)
        {
            signal(SIGINT, SIG_DFL);
            perror("execvp failed");
            exit(1);
            return -1;
        }

        signal(SIGINT, SIG_DFL); //reset interrupt signal
    }
    else //parent process
    {
        //wait specifited process exit
        if (waitpid(pid, &status, 0) < 0)
        {
            if (errno == ECHILD)
            {
                // perror("Command returned 3");
            }
            else if (errno == EINTR)
            {
                char e[200] = "Command killed:";
                perror(strcat(e, strsignal(WTERMSIG(status))));
            }
            else
            {
                //EINVAL
            }
        }
        if (status != 0)
        {
            perror("Command returned 3");
        }
        signal(SIGINT, SIG_IGN);
    }
    return 0;
}

int execute_back_cmd(struct command *cmd)
{
    //create child process
    pid_t pid = fork();
    //child process's status
    int status = 1;
    //input file descriptor
    int oldifd = 1;
    //output file descriptor
    int oldofd = 1;
    int newifd = 1;
    int newofd = 1;

    if (pid < 0) //child process creation failed.
    {
        perror("fork failed");
    }
    else if (pid == 0) //child process
    {
        if (cmd->in_redir != NULL)
        {
            //open input redirection file
            oldifd = open(cmd->in_redir, O_RDONLY);
            if (oldifd < 0)
            {
                signal(SIGINT, SIG_DFL); //reset interrupt signal
                perror("open in_redir failed");
                exit(1);
                return -1;
            }
            else
            {
                //redirect from stdin
                newifd = dup2(oldifd, STDIN_FILENO);
                if (newifd < 0) //redirect failed
                {
                    signal(SIGINT, SIG_DFL);
                    perror("dup2 in_redir failed");
                    exit(1);
                    return -1;
                }
            }
        }
        if (cmd->out_redir != NULL)
        {
            //open output redirection file
            oldofd = open(cmd->out_redir, O_WRONLY | O_CREAT | O_TRUNC, 0666);
            if (oldifd < 0)
            {
                signal(SIGINT, SIG_DFL);
                perror("open out_redir failed");
                exit(1);
                return -1;
            }
            else
            {
                newofd = dup2(oldofd, STDOUT_FILENO);
                if (newofd < 0)
                {
                    signal(SIGINT, SIG_DFL);
                    perror("dup2 out_redir failed");
                    exit(1);
                    return -1;
                }
            }
        }

        //execute external call by execvp
        if (execvp(cmd->args[0], cmd->args) < 0)
        {
            signal(SIGINT, SIG_DFL);
            perror("execvp failed");
            exit(1);
            return -1;
        }

        signal(SIGINT, SIG_DFL); //reset interrupt signal
    }
    else //parent process
    {
        //wait specifited process exit
        if (waitpid(pid, &status, WNOHANG) < 0)
        {
            if (errno == ECHILD)
            {
                // perror("Command returned 3");
            }
            else if (errno == EINTR)
            {
                char e[200] = "Command killed:";
                perror(strcat(e, strsignal(WTERMSIG(status))));
            }
            else
            {
                //EINVAL
            }
        }
        if (status != 0)
        {
            perror("Command returned 3");
        }
        signal(SIGINT, SIG_IGN);
    }
    return 0;
}