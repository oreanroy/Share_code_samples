#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include "calc.h"
#define MAXOP 100
#define NUMBER '0'

// int getop(char []);
// void push (double);
// double pop(void);

/*atof: convert string to double*/
// double atof(char s[]){

//     double val, power;
//     int i, sign;

//     for (i=0; isspace(s[i]); i++)
//         ;
//     sign = (s[i] == '-') ? -1:1;
//     if (s[i] == '+' || s[i] == '-')
//         i++;
//     for (val =0.0; isdigit(s[i]); i++)
//         val = 10.0*val + (s[i]-'0');
//     if (s[i] == '.')
//         i++;
//     for (power =1.0; isdigit(s[i]); i++){
//         val = 10.0*val+(s[i]-'0');
//         power *= 10;
//     }
//     return sign * val/power;
// }



/*reverse polish calculator*/
main() {
    
    int type;
    double op2;
    char s[MAXOP];

    while ((type = getop(s)) != EOF){
        switch(type){
            case NUMBER:
                push(atof(s));
                break;
            case '+':
                push(pop() + pop());
                break;
            case '*':
                push(pop() * pop());
                break;
            case '-':
                op2 = pop();
                push(pop()-op2);
                break;
            case '/':
                op2 = pop();
                if (op2 != 0.0)
                    push(pop()/op2);
                else
                    printf("errro: zero divisor\n");
                break;
            case '\n':
                printf("\t%.8g\n", pop());
                break;
            default:
                printf("error: unkown command %s\n", s);
                break;
        }
    }
    return 0;

}