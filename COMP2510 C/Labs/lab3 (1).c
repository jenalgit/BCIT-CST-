 #include <stdio.h>
 #include <ctype.h>
 #define CHECK(PRED) printf("%s ... %s\n" , (PRED)? "passed" : "FAILED", #PRED)


 void uppercase_first(void);
 void uppercase_last(void);
 void squeez_spaces(void);

 int main(void){
  /*uppercase_first();
  squeez_spaces();*/
  uppercase_last();
  /*squeez_spaces();

  getchar();*/
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
 	int first = 1;
 	while ((c = getchar()) != EOF) {
 		if (c == '\"') {
 			putchar(c);
 			first = 1;
 		}
 		else { /* if not a quote */
 			if (isspace(c)) {  /* if it is a space */
 				first = 1;
 				while (isspace(c))
 					c = getchar();
 				printf(" ");
 			}
 			if (!isspace(c)) { /* if it is not a space */
 				if (first == 1) {
 					putchar(toupper(c));
 					first = 0;
 				}
 				else {
 					putchar(tolower(c));
 				}
 			}
 		}

 	}
 }

/* This function is similar to uppercase_first except that the last character of a word is echoed back in uppercase;
   the rest in unchaged.
*/

int c;
int first_letter = -1 ;
while ((c = getchar()) != EOF )
{
  /* it has to be an alphabet and cannot be a space  */
  if( ( isalpha(c) && !isspace(c) ) && first_letter == -1 )
  {
    putchar(toupper(c));
    first_letter++;
  }
  else
  {
    putchar(c);
  }
}
}


void uppercase_last(void){
int c;
int last_letter = -1;
 while ((c = getchar()) != EOF) {
   if (c == '\"') {
     putchar(c);
     last_letter = -1;
   }
   else { /* if not a quote */
     if (isspace(c)) {  /* if it is a space */
       last_letter = -1;
       while (isspace(c))
         c = getchar();
       printf(" ");
     }
     if (!isspace(c)) { /* if it is not a space */
       if (last_letter == -1) {
         putchar(toupper(c));
         last_letter = 0;
       }
       else {
         putchar(tolower(c));
       }
     }
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
