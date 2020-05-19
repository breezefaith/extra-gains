int has_in_redir(char **argv);

int has_out_redir(char **argv);

void parse_redir(char **argv, char **argv1, int in_pos, char *in_file, int out_pos, char *out_file);

int execute_with_redir(char **argv, char *in_file, char *out_file);

int execute_with_pipe_and_redir(char **argv1, char **argv2, char *in_file1, char *out_file1, char *in_file2, char *out_file2);