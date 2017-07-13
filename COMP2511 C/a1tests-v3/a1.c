/*
Kunlaya Kobunnoi
A00959419
COMP 2511
Assignment1
*/
#include  <stdio.h>
#include  <string.h>
#include  <ctype.h>
#define LINESIZE 1024
#define NAMESIZE 16


/*Function Prototypes*/
int display_menu(const char *menu[], const char prompt[], FILE *fp);
int enter_records(FILE *fp);
int display_records(FILE *fp);
int get_score();
void firstName_tolower(char *firstName);
void lastName_tolower(char *firstName);

int main(void) {
	
	FILE *fp;
	const char *menu[] = {"Enter records", "Display records", "Quit", 0};
	char prompt[] = "Choose an index > ";
	
	/*Open the file*/
	if((fp = fopen("students.txt", "wb+")) != NULL) {
		display_menu(menu, prompt, fp);
	} else {
		perror("fopen"); /*Error-handling*/
	}
	/*Close the file*/
	if(fclose(fp) != 0) {
		perror("fclose");
	}
	return 0;
}

/* This function displays the first menu and controls the other functions.*/
int display_menu(const char *menu[], const char prompt[], FILE *fp) {
	
	int i, check;
	char index;
	char line[LINESIZE];	
	
	/*Display the menu*/
	for (i = 0; menu[i] != 0; i++) {
		printf("%d. %s\n", i+1, menu[i]);
	}
	printf("%s", prompt);

	/*Take the input from user*/
	while (fgets(line, LINESIZE, stdin) != NULL) {
		
		/*Scan the input (index: 1-3)*/
		if((check = sscanf(line, " %[1-3] ", &index)) == 1) { 
			/*Enter records*/
			if(index == '1') { 
				enter_records(fp);
			}
			/*Display records*/
			if(index == '2') { 
				display_records(fp);
			}
			/*Quit*/
			if(index == '3') { 
				printf("< END >");
				return 1;
			}
		}
		/*Display the menu & Prompt user again*/
		for (i = 0; menu[i] != 0; i++) {
			printf("%d. %s\n", i+1, menu[i]);
		}
		printf("%s", prompt);
	}
	clearerr(stdin);
	return -1;
}

/* When user chooses index 1, prompt user to enter name and score and store data.*/
int enter_records(FILE *fp) {
	
	char overflow;
	char line[LINESIZE], firstName[NAMESIZE], lastName[NAMESIZE];
	int score, i = 1;
	int eof = 0;
	
	fseek(fp, 0, SEEK_END);
	
	while(1){
		printf("\nEnter the name(type !! to exit): ");
		
		if (!fgets(line, LINESIZE, stdin)) {
			clearerr(stdin);
			break;
		}
		
		if(sscanf(line,"%s",firstName)==1 && strcmp(firstName,"!!")==0)
			return 0;
		
		if(sscanf(line, " %[A-Za-z-] , %[A-Za-z-]%c ", lastName, firstName, &overflow) >= 2) {/*1st form */
			if(strlen(lastName) < 16 && strlen(firstName) < 16 &&  isspace(overflow)) { /* Name should be maximum 15 chars or shorter.*/
				i++; /*Index*/
				score = get_score(eof);
				if(score == eof){
					return 0;
				}
				firstName_tolower(firstName);
				lastName_tolower(lastName);
				fprintf(fp, "%19s %19s %4d",firstName, lastName, score); /*Save data*/
			}

		} else if(sscanf(line, " %[A-Za-z-] %[A-Za-z-]%c ", firstName, lastName, &overflow) >= 2) {/*2nd form */
			if(strlen(firstName) < 16 && strlen(lastName) < 16 && isspace(overflow)) {
				i++; /*Index*/
				score = get_score(eof);
				if(score == eof){
					return 0;
				}	
				firstName_tolower(firstName);
				lastName_tolower(lastName);
				fprintf(fp, "%19s %19s %4d",firstName, lastName, score); /*Save data*/
			}
		} 
	
	}
	return 1;
}

/*Makes the first character uppercase and the rest lowercase.*/
void firstName_tolower(char firstName[]) {
	
	size_t i = 0;
	
	for(i = 0; firstName[i]; i++) {
		firstName[i] = tolower(firstName[i]);
	}
	firstName[0] = toupper(firstName[0]);
	
}
/*Makes the first character uppercase and the rest lowercase.*/
void lastName_tolower(char lastName[]){
	
	size_t i;
	
	for (i = 1; lastName[i] != 0; i++) {
		lastName[i] = tolower(lastName[i]);
		
	}
	lastName[0] = toupper(lastName[0]);
	
}

/* This function reads input file and displays student data */
int display_records(FILE *fp) {
	
	char line[LINESIZE], firstName[NAMESIZE], lastName[NAMESIZE];
	int score, pos, recordNum;

	while(1) { /*Keeps going until user enter 0.*/
		printf ("\nEnter the record number(0 to quit): ");
		if(!fgets(line, LINESIZE, stdin)) { /* if we cant read the line */
			clearerr(stdin);
			return 1;
		}
		if(sscanf(line, "%d", &recordNum) == 1) {
			if (recordNum == 0) {
				return 0;	/*0 to quit.*/
			}
			pos = 45 * (recordNum - 1);
			fseek(fp, pos, SEEK_SET);
		}
		if(fscanf(fp, "%s %s %d ", firstName, lastName, &score) == 3) { 
			
			fprintf(stderr, "%s, %s: %3d\n", lastName, firstName, score);
		}
	}
	return 0;
}

/*This function tests if the score is between 1 and 100 inclusive. Also, if the user press -1; it prompts te user to enter the data again.*/
int get_score(int eof) {
	
	char line[LINESIZE];
	int score;
	
	while(1) {
		printf("Score(type -1 to exit): ");
		
		if(!fgets (line, LINESIZE, stdin)) { /* if we cant read the line */
			clearerr (stdin);
			return 1;
		}
		if(sscanf(line, "%d", &score) == 1) {
			if(score == -1)
				return eof;
		}
		if((sscanf(line, "%d", &score) == 1) && score >= 0 && score <= 100) {
			return score;
		}
		
	}	
}