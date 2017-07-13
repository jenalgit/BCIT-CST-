#include <stdio.h>
#include <ctype.h>
#define CHECK(PRED) printf("%s ... %s\n", (PRED) ? "passed" : "FAILED", #PRED)


int main(void)
{
    int c;
    size_t nlines = 1;
    size_t nspace = 0;

    while ((c = getchar()) != EOF)
    {
        if (c == '\t')
            c = ' ';
        if (c == ' ')
        {
            if (nspace < 1)
            {
                putchar(c);
                nspace++;
                nlines = 0;
            }   
        }
		else if ( c == '\r') 
			continue;
        else if (c == '\n')
        {
           nlines++;
			if (nlines < 3)
			{
				putchar(c); 
			}
        }
        else
        {
            putchar(c);
            nspace = 0;
            nlines = 0;
        }
    }
    
    return 0;
}