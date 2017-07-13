#include <stdio.h>
#include <string.h>
#include <ctype.h>
#define LINESIZE 1024

int menu (const char *choices[],const char prompt[]);
int get_int(const char prompt[], int eof);
int get_valid_int(const char prompt[],int eof, int min, int max);

int main (){

	const char *choices[] = {"Enter data",
							 "Display Data",
							 "Quit",
							  0};
							  
	menu (choices,"Please enter an integer: ");
	
	return 0;
}

int get_int(const char prompt[], int eof){
	
	int	number;
	char character;
	char line [LINESIZE];
	char string[LINESIZE];
	
	
	while (1){
		    if(prompt != 0){
				printf("%s",prompt);
			}
			if (!fgets(line,LINESIZE,stdin)){
				clearerr(stdin);
				return eof; 
			}
			if (sscanf(line,"%s",string) == 1) {
				if(sscanf(string,"%d%c",&number,&character) == 1)
					return number;
				else
					printf("Numbers only!\n");
			}
		}
} 

int get_valid_int(const char prompt[],int eof, int min, int max){
	int x;
	
	while(1){	
		x = get_int(prompt,eof);
		if(min <= x &&  x <= max){
			return x;
		}
		else{
			printf("Exceeds the range\n");
		}
		if(x == eof)
			return eof;
	}
	
}

int menu (const char *choices[],const char prompt[]){
   
	int i;
	int y;
	while (1) { 
		for (i = 0; choices[i] != 0; i++ ) {
			printf("%i.%s\n",i+1,choices[i]);
		}
		y = get_valid_int(prompt,0, 1, i);
		
		
		return y;
			
	}
}