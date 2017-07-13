#include <stdio.h>
#include <ctype.h>
#define LINESIZE 1024 

int get_last_first(FILE *fp);
int get_first_last(FILE *fp);
void question1();

int main(void){
/*
	char line[LINESIZE];
	char first[16];
	char last[16];
	char comma[1]; */
	
	
	printf("Enter your name\n");
	while(1){
    	
		get_last_first(stdin);
		/*get_first_last(stdin);*/
	}
	
	return 0;
}

int get_last_first(FILE *fp){
	
	char line[LINESIZE];.
	char last[16], first[16];
	int  n;
	
/* returns -1 if the input is not in the correct format
			or the name is not valid */
		if(fgets(line, LINESIZE, fp) == NULL) {
			return -1;
		}
		n = sscanf(line, "  %[a-z || A-Z||-] , %[a-z || A-Z||-] ", last, first);
		/* returns 0 on EOF */
		if((n = sscanf(line, "  %[a-z || A-Z||-] , %[a-z || A-Z||-] ", last, first)) == EOF) {
			return 0;
		}
		/* prints the name if it's valid */
		if(n == 2) {
			fprintf(stderr,"first:\t%s last: %s\n",first,last);
		}
	clearerr(fp);
	return 1;
}

int get_first_last(FILE *fp) {
	char line[LINESIZE];
	char first[16], last[16];
	int n;

	/*if(fgets(line, LINESIZE, fp) == NULL) {
		return -1;
	} */
	n = sscanf(line," %[a-z || A-Z||-] %[a-z || A-Z||-] ",first,last);
	/* returns 0 on EOF */
	if((n = sscanf(line," %[a-z || A-Z||-]  %[a-z || A-Z||-] ",first,last)) == EOF) {
		return 0;
	}
	/* prints the name if it's valid */
	if(n == 2) {
		fprintf(stderr,"first:\t%s last: %s\n",first,last);
	}
	clearerr(fp);
	return 1;

}

void question1() {
	int i, n; 
	double d; 
	char s1[32], s2[32];

	n = sscanf("hello", "%4s", s1);
	printf("(a)\ts1 = %s, n = %d\n", s1, n);
	/*	s1 = "hell", n = 1
		stores the frist 4 chars to s1. */

	n = sscanf("12345 32 12abc", "%2d %lf %*d %s", &i, &d, s1);
	printf("(b)\ti = %d, d = %f, s1 = %s, n = %d\n", i, d, s1, n);
	/*	i = 12, d = 345.000000 s1 = 12abc n = 3
		3 variables from the string.
		the first 2 digits to i, the rest of digits to d, and it skips '32' in the middle 
		and store the last 5 chars to s1. */

	i = 0, d = 0.0;

	n = sscanf("12 / 345", "%d/%lf", &i, &d);
	printf("(c)\t12 / 345:  i = %d, d = %f, n = %d\n", i, d, n);
	/*	i = 12, d = 0.000000, n = 1
		stores the digits before '/' to i, but stores nothing to d
		because there is a blankspace before '/'. */

	n = sscanf("12/345", "%d/%lf", &i, &d);
	printf("(d)\t12/345:  i = %d, d = %f, n = %d\n", i, d, n);
	/*	i = 12, d = 345.000000, n = 2
		stores the digits before '/' to i, and stores the digits as a floating point number
		after '/'. */
	n = 0;
	n = sscanf("dos2unix", "%[a-z] %s", s1, s2);
	printf("(e)\ts1 = %s, s2 = %s, n = %d\n", s1, s2, n);
	/*	s1 = "dos", s2 = "2unix" n = 2
		stores only alphabets to s1, and the rest of chars to s2. */
}