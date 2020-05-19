#include <glob.h>

int is_glob(char *s);

void parse_glob(char **argv, glob_t *globbuf);

int execute_with_redir_glob(char **argv, char *in_file, char *out_file, glob_t* buf);

int execute_with_pipe_and_redir_glob(char **argv1, char **argv2, char *in_file1, char *out_file1, char *in_file2, char *out_file2, glob_t* buf1, glob_t* buf2);