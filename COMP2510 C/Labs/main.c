#include <stdio.h>  
#define CHECK(PRED) printf("%s ... %s\n" , (PRED)? "passed" : "FAILED", #PRED)

/*	Returns the value of the smallest integer in the integer array a of n elments,
	i.e. returns the minimum value of the array
	pre-condition:n >=1 
 */
int arr_min(const int a[], size_t n){
	int min = a[0];
	size_t i;
	for (i = 0; i < n; i++)
		if(a[i] < min)
			min = a[i];
		return min;
}

/*	Returns the index of the minimum value in the integer array a of n elments; 
	if the minimum value occurs more than once in the array, returns the index of its first occurence
	pre-condition: n>=1  
*/
size_t arr_index_of_first_min(const int a[],size_t n){
	
	int min = a[0];
	int min_first_index = 0;
	size_t i;
	for(i = 0; i < n; i++)
		if(a[i] < min){
			min = a[i];
			min_first_index = i;
			
		}
		return min_first_index;
		
	
}
/*	Returns the index of them minimum value in the integer array a of n elements; 
	if the minimum value occurs more than onces in the array, 
	returns the index of its last occurence
	pre-condition: n>=1
*/

size_t arr_index_of_last_min(const int a[], size_t n)
{
	int min =a[0];
	int min_last_index = 0;
	size_t i;
	
	for(i =0; i < n; i++)
		if(min > a[i]){
			min = a[i];
			min_last_index = i;
		}
		else if(min == a[i]){
			min_last_index = i;
		}
		
		return min_last_index;
	
}

/* Returns the number of times the minimum value occurs in the integer array a of n elements 
   Pre-condition: n>=1
   (Restriction : loop through the array at most once)
*/
int arr_count_min(const int a[], size_t n){
	
	int min = a[0];
	int count = 0;
	size_t i;
	for (i = 0; i < n; i++){
		
		if(a[i] < min){
			min = a[i];
			count = 1;
		}	
		if(min == a[i]){
			count++;
		}
	}
	return count;
		
}
int main (void){
	
	int a[] = {2,5,2,4,2,7};
	CHECK(arr_min(a,6)==2);
	CHECK(arr_index_of_first_min(a,6)==0);
	CHECK(arr_index_of_last_min(a,6)==4);
	CHECK(arr_count_min(a,6)==3);
	
	return 0;
	
}