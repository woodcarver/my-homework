#include "NativeMethods.h"
#include <stdio.h>

JNIEXPORT void JNICALL Java_NativeMethods_greeting(JNIEnv *env, jclass cl)
{
    printf("Hello Native world!\n");
}
