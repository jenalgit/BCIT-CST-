/******************************************************************************* 
   COMP2511 Assignment 2
   Author: Kunlaya Kobunnoi
   Description: The program concatenates files and displays them to standard output.
 *******************************************************************************/
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#define LINESIZE 128
#define BUFFSIZE 1024

void handleFile(FILE *fp, int bswitch, int eswitch, int hswitch, int nswitch, int sswitch, int tswitch);

int main(int argc, char *argv[]) {
	FILE *fp;
	int i;
	int file;
	int bswitch = 0, eswitch = 0, hswitch = 0, nswitch = 0, sswitch = 0, tswitch = 0, dashflag = 0;
	char filename[LINESIZE];
	

	for(i = 1; i < argc; i++) {
  		if(argv[i][0] != '-') /* If it doesn't start with '-', it is not a switch. */
  			break;
  		else if(argv[i][2] != '\0') { /* If starts with '-' but has more than 2 chars, invalid option. eg. -help */
  			fprintf(stderr, "%s: invalid option -- -%s\nTry '%s -h' for more information.\n", argv[0], &argv[i][1], argv[0]);
			exit(1);
		}
		if(dashflag == 2)
			break;
		
		else if(argv[i][0] == '-'){
			dashflag = 1;
		}
		
		/*if dashflag is still one keep looking for another switch. */
  		if(dashflag == 1) {		
			argv[i][1] = tolower(argv[i][1]);
  			/* Handle the switches */
	  		switch (argv[i][1]) {
				case 'b' : /* Number non-blank empty output lines */
					bswitch++;
					break;
				case 'e' : /* Display $ at end of each line */
					eswitch++;
					break;
				case 'h' : /* Display help (to standard output) and exit */
					hswitch++;
					break;
				case 'n' : /* Number all output lines */
					nswitch++;
					break;
				case 's' : /* Suppress repeated empty output lines */
					sswitch++;
					break;
				case 't' : /* Display TAB characters as ^I */
					tswitch++;
					break;
				case '-' : /* Mark the end of switches; arguments that follow are regarded as file names */
					dashflag++;
					break;
				default : /* If invalid switch, error message and exit. */
					fprintf(stderr, "./concat: invalid option -- -%c\nTry './concat -h' for more information.\n", argv[i][1]);
					exit(1);
			}
  		}
		file = i + 1;
		
  	/* End the loop */
  	}
	
	/*if dashflag is more than one means it regards what after it as file names. */
	if(dashflag > 1){
		if(sscanf(argv[file], "%s", filename) == 1){
			if((fp = fopen(filename, "rb+")) == NULL)
				perror("fopen");
			/* Pass in all the switches as parameters in a big function */
			handleFile(fp, bswitch, eswitch, hswitch, nswitch, sswitch, tswitch);
			/*Close the file*/
			if(fclose(fp) != 0)
				perror("fclose");
		}
	}

  	if(i == argc) {
		/* Call the big handlnig file function, passed in stdin (no FILE specified) */
		handleFile(stdin, bswitch, eswitch, hswitch, nswitch, sswitch, tswitch);
	}
	
	
	for(; i < argc; i++) {
		/*Open each file*/
		if(argv[i][0] != '-' && sscanf(argv[i], "%s", filename) == 1) {  /*Read file names*/
				if((fp = fopen(filename, "rb+")) == NULL)
					perror("fopen");
				/* Pass in all the switches as parameters in a big function */
				handleFile(fp, bswitch, eswitch, hswitch, nswitch, sswitch, tswitch);
				
				/*Close the file*/
				if(fclose(fp) != 0)
					perror("fclose");
		}	
	}
	return 0;
}


/* This function handles the switches and print output. */
void handleFile(FILE *fp, int bswitch, int eswitch, int hswitch, int nswitch, int sswitch, int tswitch) {
	int c;
	/* reading char by char */
	while((c = fgetc(fp)) != EOF) {
		static int nextline = 1;
		static int nline = 1;
		
		/* -h display this help and exit */
		if(hswitch) {
			char h1[] = "Usage: ./concat [OPTION]... [FILE]...";
			char h2[] = "Concatenate FILE(s), or standard input, to standard output.\n";
			char h3[] = "  -b|-B  number nonempty output lines\n  -e|-E  display $ at end of each line";
			char h4[] = "  -n|-N  number all output lines\n  -s|-S  suppress repeated empty output lines";
			char h5[] = "  -t|-T  display TAB characters as ^I\n  -h|-H  display this help and exit\n  --  mark end of options";
			char h6[] = "\nWith no FILE, read standard input.";
			printf("%s\n%s\n%s\n%s\n%s\n%s\n", h1, h2, h3, h4, h5, h6);
			exit(1);
		}

		/* -s suppress repeated empty output lines. should be read first. */
		if(sswitch) {
			static int nlines = 0;
			if(c == '\n')
				nlines++;
			else
				nlines = 0;
			
			if(nlines > 2)
				c = '\0';
		}
		/* -n number all output lines */
		if(nswitch) {
			if(nextline > 0) {
				if(nline < 10)
					printf("     %d  ", nline++);
				else
					printf("    %d  ", nline++);
			} 
			if(c == '\n')
				nextline++;
			else
				nextline = 0;	
		}

		/* -b number nonempty output lines */
		if(bswitch) {
			if(nextline > 0 && c != '\n') {
				if(nline < 10)
					printf("     %d  ", nline++);
				else
					printf("    %d  ", nline++);
			}
			if(c == '\n')
				nextline++;
			else
				nextline = 0;
		}

		/* -e display $ at end of each line */
		if(eswitch) {
			if(c == '\n') {
				putchar('$');
			}
		}

		/* -t display TAB characters as ^I */
		if(tswitch) {
			if(c == '\t') { 
				putchar('^');
				putchar('I');
				c = '\0';
			}
		}

		/* After all the switches, print the character */
		if(c != '\0')
			putchar(c);

	/* End of the loop */	
	}

}
