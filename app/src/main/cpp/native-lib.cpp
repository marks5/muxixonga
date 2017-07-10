#include <jni.h>
#include <signal.h>
#include <sys/time.h>
#include <stdio.h>


static JavaVM *jvm = NULL;
static jobject callback = NULL;

void handler(int s) {
    if(jvm == NULL)
        return ;
    if(callback == NULL)
        return ;

    JNIEnv *env = NULL;
    jint res;
    res = (*jvm).AttachCurrentThread((JNIEnv **) (void **)&env, NULL);
    if(res < 0)
    {
        fprintf(stderr, "Attach VM Thread failed\n");
        return ;
    }

    jclass cls = (*env).GetObjectClass(callback);
    jmethodID mid = (*env).GetMethodID(cls, "run", "()V");
    (*env).CallVoidMethod(callback, mid);
    (*jvm).DetachCurrentThread();
}

JNIEXPORT void JNICALL
Java_br_com_gabriel_muxixi_NativeConvert_asyncConverteToReal(JNIEnv *env, jobject instance,
                                                             jfloat valueToConvert) {
    struct sigaction sa;
    float a = valueToConvert;

    sa.sa_flags = SA_RESTART;
    sigemptyset (&sa.sa_mask);

    /* find the current jvm */
    (*env).GetJavaVM(&jvm);
    /* upgrade callback to global ref */
    callback = (*env).NewGlobalRef(instance);

    sa.sa_handler = handler;
}
