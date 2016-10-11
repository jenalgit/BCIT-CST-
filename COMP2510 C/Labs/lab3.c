#include <stdio.h>
#include <ctype.h>
#define CHECK(PRED) printf("%s ... %s\n" , (PRED)? "passed" : "FAILED", #PRED)


void uppercase_first(void);
void uppercase_last(void);
void squeez_spaces(void);

int main(void){
  uppercase_first();
  /*uppercase_last();
  squeez_spaces();*/


 return 0;

}
/* This function echos back what it reads but with the first character of each word printed in uppercase
  (if it is an alphabet) & the rest of the characters in the word unchanged. Except for the very first
  character in the input, we regard a character (that is not a whitespace) as the first character of
  a word if it is immediately follows a whitespace.(Note: A whitespace is a character that tests
  true by the isspace function)
  Note that if the input begins with an alphabet, that alphabet is displayed in uppercase as well.

*/
void uppercase_first(void){
  int c;
  int previous = -1;
  char previous_char;

  while((c = getchar()) != EOF )
  {
    if(!isspace(c) && previous == -1)
    {
        putchar(toupper (c));
        previous_char = c;
        previous++;
    }
    else if (isalpha(c) && !isspace(c) && isspace(previous_char))
   {
     putchar(toupper(c));
     previous_char = c;

   }
    else
   {
     putchar(c);
     previous_char = c;

   }
 }
}
/* This function is similar to uppercase_first except that the last character of a word is echoed back in uppercase;
  the rest in unchaged. */
void uppercase_last(void){
 int c; /*current char */
 char last_char;
  while((c = getchar()) != EOF )
{
  if (isalpha(c))
  {
    putchar(last_char);
    last_char = c;
  }
  else if (isspace(c))
  {
    putchar(toupper(last_char));
    last_char = c;
  }
}
}



/* For the squeez_spaces function, a consecutive sequence of the space characters in the input Will
result in a single instance of the space character in the output. All other characters are echoed back unchanged.
*/
void squeez_spaces(void){
  int c;
    while ((c = getchar()) != EOF) {
           if (isspace(c))
           {
             while (isspace(c = getchar())) ;
             printf(" ");
           }
   putchar(c);
   }
}
