/*
 Student : Kunlaya Kobunnoi
 Student no : A00959419
 Subject: COMP2510 Lab 4
*/
#include <stdio.h>
#include <ctype.h>
#define CHECK(PRED) printf("%s ... %s\n" , (PRED)? "passed" : "FAILED", #PRED)

int is_valid(const char s[]);
int display_type(const char s[]);


int main(void){

  char question1[]="Homer";
  char question1_2[]="Homer-J";
  char question1_3[]="D'Arcy";
  char question1_4[]="B";
  char question1_5[]="-homer";
  char question1_6[]="bart-";

  CHECK(is_valid(question1)==1);
  CHECK(is_valid(question1_2)==1);
  CHECK(is_valid(question1_3)==0);
  CHECK(is_valid(question1_4)==0);
  CHECK(is_valid(question1_5)==0);
  CHECK(is_valid(question1_6)==0);

  CHECK(display_type("5") == 1);
  CHECK(display_type("5+") == 2);
  CHECK(display_type("5-") == 3);
  CHECK(display_type("Grace") == 0);
    return 0;
  }

/* Validate the string s. The function returns 1 if s is valid; otherwise, it return 0.
   -It has at least 2 characters & at most 19 characters
   -Each character of the string must be either an alphabet or a hyphen.
   -But the string cannot start or end with a hyphen.
*/
int is_valid(const char s[]){

    size_t i;
    for(i = 0; s[i] != '\0'; i++){
      if (!isalpha(s[i]) && s[i] != '-')
        return 0;
      else if(s[0]=='-')
        return 0;
    }

    if(s[i-1] == '-')
      return 0;
    if(i <= 2 || i >= 19)
      return 0;

    return 1;
    }

/* Returns 0 if the string is not valid.
   Returns 1 if the string s is a positive integer.
   Returns 2 if the string s is apositive integer immediately followed by a plus.
   Returns 3 if the string s is a positive integer immediately followed by a minus.
*/
int display_type(const char s[]){
      size_t i;
      			if (!isdigit(s[0]))
      			return 0;

      	for (i=0; s[i] != '\0'; i++){
      		if (s[i] != '+' && s[i] != '-' && !isdigit(s[i]))
      			return 0;
      		else if ((s[i-1] == '+' || s[i-1] == '-') && isdigit(s[i]))
      			return 0;
      		else if ((s[i-1] == '+' || s[i-1] == '-') && (s[i] == '+' || s[i] == '-'))
      			return 0;

      	}
      	if (s[i-1] == '\0')
      		return 0;
      	if (s[i-1] == '+')
      		return 2;
      	if (s[i-1] == '-')
      		return 3;


        return 1;
      }
