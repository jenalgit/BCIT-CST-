
#include <stdio.h>
#include <string.h>
#define LINESIZE 1024

int is_permutation(unsigned long n1, unsigned long n2);
int is_permutation_int(unsigned long n1, unsigned long n2);
int is_staggering(unsigned long n);
int num_staggering(int n);

int main (void){
	
	/*unsigned long integer = 144358;*/
	int result;
	/*unsigned long permutation = 321;
	result = is_permutation_int(integer, permutation);
	printf("%d", result);

	result = is_staggering(integer);
	printf("is staggering: %d", result);
	*/
	
	result = num_staggering(1000000000);
	printf("no of items staggering: %d", result);
	return 0;
}

int is_permutation(unsigned long n1, unsigned long n2){
	char integerAsString[LINESIZE];
	char permutationAsString[LINESIZE];
	int numberOfOccurence[10];
	size_t i;
	int character;
	
	sprintf(integerAsString,"%lu",n1);
	sprintf(permutationAsString,"%lu",n2);
	/*printf("integerString: %s, permutationString: %s", integerAsString, permutationAsString);*/
	for(i=0; i<10; i++){
		numberOfOccurence[i]=0;
	}
	
	for(i = 0; i<strlen(integerAsString); i++){
		character = integerAsString[i]-'0';
		numberOfOccurence[character]++;
	}
	
	for(i = 0; i<strlen(permutationAsString); i++){
		character = permutationAsString[i]-'0';
		numberOfOccurence[character]--;
	}
	
	for(i=0; i<10; i++){
		if(numberOfOccurence[i] !=0){
			return 0;
		}
	}
	
	return 1;
}

int is_permutation_int(unsigned long n1, unsigned long n2){
	
	int numberOfOccurence[10];
	size_t i;
	int remainder;	

	for(i=0; i<10; i++){
		numberOfOccurence[i]=0;
	}
	
	while(n1!=0){
		remainder = n1%10;
		n1 = n1/10;
		numberOfOccurence[remainder]++;
	}
	
	while(n2!=0){
		remainder = n2%10;
		n2 = n2/10;
		numberOfOccurence[remainder]--;
	}
		
	for(i=0; i<10; i++){
		if(numberOfOccurence[i] !=0){
			return 0;
		}
	}
	
	return 1;
}	

int is_staggering(unsigned long n){
	int currentDigit;
	int previousDigit=n%10;
	int increasing=0;
	int decreasing=0;
	
	do{
		currentDigit = n%10;
		n=n/10;
		if(currentDigit>previousDigit){
			decreasing=1;
		}else if(currentDigit<previousDigit){
			increasing=1;
		}
		if(decreasing == 1 && increasing == 1){
			return 1;
		}
		
		previousDigit=currentDigit;
	}while(n!=0);
		
	return 0;
}

int num_staggering(int n){
	int i;
	int count=0;
	for(i=0; i<n; i++){
		if(is_staggering(i)==1){
			count++;
		}
	}
	return count;
}