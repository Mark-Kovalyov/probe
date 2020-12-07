```
[ERROR] Failed to execute goal org.codehaus.mojo:aspectj-maven-plugin:1.11:compile (default) on project probe-aspectj: AJC compiler errors:
[ERROR] error at pointcut callWithDraw(int amount, Account acc) :
[ERROR]                                   ^^
[ERROR] ~/git/probe/probe-aspectj/src/main/java/mayton/AccountAspect.aj:5:0::0 Account cannot be resolved to a type
[ERROR] error at before(int amount, Account acc) : callWithDraw(amount, acc) {
[ERROR]                    ^^
[ERROR] ~/git/probe/probe-aspectj/src/main/java/mayton/AccountAspect.aj:8:0::0 Account cannot be resolved to a type
[ERROR] error at boolean around(int amount, Account acc) :
[ERROR]                            ^^
[ERROR] ~/git/probe/probe-aspectj/src/main/java/mayton/AccountAspect.aj:11:0::0 Account cannot be resolved to a type
[ERROR] error at after(int amount, Account balance) : callWithDraw(amount, balance) {
[ERROR]                   ^^
[ERROR] ~/git/probe/probe-aspectj/src/main/java/mayton/AccountAspect.aj:19:0::0 Account cannot be resolved to a type
[ERROR]
```