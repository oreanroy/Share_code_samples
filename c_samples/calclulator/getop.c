#include <stdio.h>
#include <ctype.h>
#include "calc.h"
#include <stdbool.h>


/* getop: get the next operator or number operand*/
int getop(char s[]) {
    int i, c;

    while ((s[0] = c = getch()) == ' ' || c == '\t')
    ;

    s[1] = '\0';
    if (!isdigit(c) && c !='.')
        return c;
    i=0;
    if (isdigit(c))
        while (isdigit(s[++i] = c = getch()))
            ;
    s[i] = '\0';
    if (c!=EOF)
        ungetch(c);
    return NUMBER;
    
}


int getopNew(char s[]) {
    int i, c;
    static bool getNew = true;

    while ((s[0] = c = getchar()) == ' ' || c == '\t')
    ;

    s[1] = '\0';
    if (!isdigit(c) && c !='.')
        return c;
    i=0;
    if (isdigit(c))
        while (isdigit(s[++i] = c = getchar()))
            ;
    s[i] = '\0';
    if (c!=EOF)
        ungetch(c);
    return NUMBER;
    
}

