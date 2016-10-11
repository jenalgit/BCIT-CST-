#include <stdio.h>
#include <string.h>
#include <ctype.h>
#define CHECK(PRED) printf("%s ... %s\n" , (PRED)? "passed" : "FAILED", #PRED)
 /* Kunlaya Kobunnoi 
    A00959419
	Set C
    COMP 2510 
*/

/* Returns the index of the last occurrence of the character ( specified by) c in the string s,
   or returns -1 if the string does not contain c */
size_t str_find_last(const char s[], int c){
    size_t i;
	size_t index = -1;
    for (i = 0 ; s[i] !='\0' ; i++){
        if(s[i] == c){
            index = i;
		}                                         
    }
	return index;
}

/* - Replace all occurrences of the character oldc in the string s by the character newc
   - Returns the number of replacements*/
size_t str_replace_all(char s[],int oldc, int newc){
    size_t i;
	int num_replace =0;
    for(i=0; s[i] != '\0'; i++){
        if (s[i]== oldc){
            s[i] = newc;
			num_replace++;
        }
    }
	return num_replace;
}
 
/* Returns 1 if the string s consist entirely of digits; otherwise, return 0*/
int str_all_digits(const char s[])
{  size_t i;
   for(i = 0; s[i] !='\0';i++){
        if(!isdigit(s[i]))
            return 0;           
    }
	return 1;
}

/* Returns 1 if the strings s & t have the same sequence of characters; 
   otherwise, returns 0*/
int str_equal(const char s[], const char t[])
{  size_t i;
   for(i=0;s[i]!='\0'|| t[i] != '\0'; i++ ){
       if (s[i] != t[i]) {
			return 0;
	   }      
   }
   return 1;
}

int main (void){
    
	char question1[]="Grace";
	char question1_2[]="Kunlaya";
	char question1_3[]="Kobunnoi";
  
	char question2[] = "hello world";
	char question2_1[] = "Grace";
	char question2_2[] = "Kunlaya";
	char question2_3[] = " ";
	
	
    char question3[] = { 'G', 'R', 'A', 'C', 'E'};
	char question3_1[] = { 'K', 'U', 'N', 'L', 'A', 'Y','A'};
	char question3_2[] = { 'A', 5, 0, 4, 3 ,2, 5 };
	char question3_3[] = " ";
	
    char question4[] = "small";
	char question4_1[] = "smaller";
	char question4_2[] = " ";
	
	CHECK(str_find_last(question1,'c')==3);
	CHECK(str_find_last(question1_2,'l')==3);
	CHECK(str_find_last(question1_3,'u')==3);
	CHECK(str_find_last(question1_3,'u')==3);
	
	CHECK(str_replace_all(question2, 'l', 'x') == 3);
	CHECK(strcmp(question2,"hexxo worxd")==0);
	CHECK(str_replace_all(question2_1, 'c', 'p') == 1);
	CHECK(strcmp(question2_1,"Grape")==0);
	CHECK(str_replace_all(question2_2, 'a', 'o') == 2);
	CHECK(strcmp(question2_2,"Kunloyo")==0);
	CHECK(str_replace_all(question2_3, 'a', 'o') == 0);

	CHECK(str_all_digits(question3) == 0);
	CHECK(str_all_digits(question3_1) == 0);
	CHECK(str_all_digits(question3_2) == 0);
	CHECK(str_all_digits(question3_3) == 0);
   
	CHECK(str_equal(question4, question4_1) == 0);
	CHECK(str_equal(question4_1, question4) == 0);
	CHECK(str_equal(question4_1, question4_2) == 0);

  return 0;
}