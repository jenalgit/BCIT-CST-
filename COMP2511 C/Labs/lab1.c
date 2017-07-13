#include <stdio.h>

#define CHECK(EXPR) printf("%s ...%s\n", (EXPR) ? "passed" : "FAILED", #EXPR)

int array_max(const int a[],size_t n);
size_t array_index_of_first_max(const int a[], size_t n);
size_t array_index_of_last_max(const int a[], size_t n);
int array_equal(const int a[], const int b[], size_t n); 

int main(void){
	
	int a[] = {1,2,3,4,5};
	int b[] = {1,2,3,3};
	int c[] = {2,2,1,5,5};
	/*int d[] = {1,2,3,4,5};*/
	

	
	CHECK(array_max(a,5)==5);
	CHECK(array_index_of_first_max(b,3)==2);
	CHECK(array_index_of_last_max(c,5)==4);
	CHECK(array_equal(a,c,5)==0);
	return 0;
}
int array_max(const int a[],size_t n){
	
	int max = a[0];
	
	size_t i;
	
	for(i = 0; i<n ;i++){
		if(a[i]>max)
			max = a[i];
		
	}
	return max;
}
size_t array_index_of_first_max(const int a[], size_t n){
	int max = a[0];
	size_t i;
	int first_max = 0;
	
	for(i = 0; i < n ;i++){
		if(a[i]>max){
			max = a[i];
			first_max = i;
		}
	
	}
    return first_max;
	
}
size_t array_index_of_last_max(const int a[], size_t n){
	int max = a[0];
	size_t i;
	int last_max = 0;
	
	for(i = 0; i < n ;i++){
		if(a[i]>max){
			max = a[i];
			last_max = i;
		}
		else if(max == a[i]){
			last_max = i;
		}
			
	}
    return last_max;
	
} 
int array_equal(const int a[], const int b[], size_t n){
	
	size_t i;
	int no = 1;
	for(i = 0; i <n; i++)
		if(a[i]!=b[i])
			no = 0;
	return no;
	
}

