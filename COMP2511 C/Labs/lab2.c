#include <stdio.h>
#include <ctype.h>
#define CHECK(PRED) printf("%s ... %s\n", (PRED) ? "passed" : "FAILED", #PRED)


int str_all_digits(const char s[]);
int str_has_digits(const char s[]);
size_t str_replace_all(char s[], int oldc, int newc);
int str_replace_first(char s[], int oldc, int newc);
int str_replace_last(char s[], int oldc, int newc); 
int main(void){ 
	char p[] = "1234556";
	char q[] = "hello";
	char replace[] = "kunlaya kobunnoi";
	char a[] = "hello1";
	char b[] = "okayk";
	char c[] = "Graece";
	
	CHECK(str_all_digits(p)==1);
	CHECK(str_all_digits(q)==0);
	CHECK(str_has_digits(p)==1);
	CHECK(str_has_digits(replace)==0);
	CHECK(str_has_digits(a)==1);
	CHECK(str_replace_all(replace,'k','a')==2);
	
	CHECK(str_replace_first(b,'k','a')==1);
	CHECK(str_replace_last(c,'e','a')==1);
	return 0;
}

int str_all_digits(const char s[]){
	size_t i;
	for(i = 0; s[i] != '\0'; i++){
		if(!isdigit(s[i]))
			return 0;
		
	}
	return 1;
}
int str_has_digits(const char s[]){
	size_t i;
	for(i = 0; s[i] != '\0'; i++){
		if(isdigit(s[i]))
			return 1;
		
	}
	return 0;
} 
size_t str_replace_all(char s[], int oldc, int newc){
	size_t i;
	size_t numOfReplace = 0;
	for(i = 0; s[i] != '\0'; i++){
		if(s[i] == oldc){
			s[i]=newc;
			numOfReplace++;
		}
	}
	return numOfReplace;
}
int str_replace_first(char s[], int oldc, int newc){
	size_t i;
	/*size_t replace; */
	
	for(i = 0; s[i] != '\0'; i++){
		if(s[i] == oldc){
			s[i] = newc;-
			
			return 1;
		}
	}
	return 0;
}
int str_replace_last(char s[], int oldc, int newc){
	size_t i;
	size_t replace;
	size_t changedLast = 0;
	for (i = 0; s[i] != '\0'; i++) {
		if (s[i] == oldc) { 
			replace = i;
			changedLast = 1;
		}
	}

	if (changedLast == 1) {
		s[replace] = newc;
	}
	return changedLast;
}
