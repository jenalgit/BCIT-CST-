#include <stdio.h>
#include <string.h>
#include <ctype.h>
#define LINESIZE 1024
#define CHECK(PRED) printf("%s ... %s\n", (PRED) ? "passed" : "FAILED", #PRED)

void min_max(const int a[], size_t n, int *pmin, int *pmax);
void first_last(const int a[],size_t n, int x,size_t *pfirst,size_t *plast);
void str_reverse(char s[]);
unsigned long num_reverse(unsigned long n);


int main(void){
	int a[] = {9, 3, 4, 4, 6, 7, 12, 4, 3, 7};
	size_t n = 10; /*length of the array*/
	char s[] = "Hello World";
	char reversed[] = "dlroW olleH";
	int pmin = a[0];
	int pmax = a[0];
	int x = 3; /*integer x*/
	size_t pfirst, plast;

	min_max(a, n, &pmin, &pmax);
	CHECK(pmin == 3 && pmax == 12);

	first_last(a, n, x, &pfirst, &plast);
	CHECK(pfirst == 1 && plast == 8);
	
	str_reverse(s);
	CHECK(*s == *reversed);


	CHECK(num_reverse(12345678) == 87654321);
	
	return 0;
	
}

void min_max(const int a[], size_t n, int *pmin, int *pmax){
	size_t i;
	*pmin = a[0];
	*pmax = a[0];
	for(i=0;i < n ;i++){
		if(a[i] >= *pmax)
			*pmax = a[i];
		if(a[i] <= *pmin)
			*pmin = a[i];
	}
}

void first_last(const int a[],size_t n, int x,size_t *pfirst,size_t *plast){
	size_t i;
	size_t count = 0;
	for(i=0;i < n ;i++){
		if(a[i]== x){
			if(count == 0)
				*pfirst = i;
			*plast =i;
			count++;
		}
		
	}
}
/*	reverses the string s passed into it. (shouldn't make a copy of s)*/
void str_reverse(char s[]) {
	size_t i;
	char temp;

	for(i = 0; i < strlen(s)/2; i++) {
		temp = s[i];
		s[i] = s[strlen(s)-1-i];
		s[strlen(s)-1-i] = temp;
	}
}

/* 	pre-condition: n % 10 != 0
	given a non-negative integer n, 
	returns a number whose digits are those of n in reverse order.
	implement this function without converting the number to a string. 
	ex. f(1234) = 4321 */
unsigned long num_reverse(unsigned long n) {
	unsigned long num = 0;
	while (n != 0) {
		num *=  10;
		num = num + n % 10;

		n /= 10;
	}
	return num;
}
