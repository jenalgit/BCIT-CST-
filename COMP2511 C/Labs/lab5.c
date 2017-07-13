#include <stdio.h>
#include <ctype.h>
#define LINESIZE 1024


int menu (const char *choices[], const char *prompt);

int main(void){
	const char *choices[] = {"Enter data",
							 "Display Data",
							 "Quit",
							  0};
	
		
	menu(choices,">Select an option");
	return 0;
	
}
int menu (const char *choices[], const char *prompt){
	int i = 0;
	int c, check;
	char line[LINESIZE];

	while(choices[i] != 0) {
		/*Displays the menu number and strings*/
		printf("(%d) %s\n", (int)i+1, choices[i]);
		i++;
	}
	printf("%s\n", prompt);

	while (fgets(line, LINESIZE, stdin) != NULL) {
		if ((check = sscanf(line, "%d", &c)) == 1) { /*Checking the whole line if it's digit*/
			
			if(c > 0 && c <= i) { 
				/*if the number is more than zero and less than index given */
				/*if we input c = 3 we print choices[2] */
				printf("You chose: (%d) %s", c, choices[c-1]);
				return 1;
			}

			printf("Number is not in the range 1&3! Please try again\n"); /*prompt again if input is not valid*/
		}
		else { /*prompt user again when it's not digit*/
			printf("Wrong input! Please enter a digit.\n");
		}	
	}
	clearerr(stdin);
	return -1;

}