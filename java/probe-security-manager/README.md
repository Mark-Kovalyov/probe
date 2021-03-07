# Security manager

All allowed policy
```
grant {      
      permission java.security.AllPermission;
};
```

Trivial policy
```
grant {
      permission java.io.FilePermission "*", "read,write,delete";
      permission java.util.PropertyPermission "user.dir", "read";
      permission java.util.PropertyPermission "java.runtime.name", "read";
};
```

Debug
```
java \
 -Djava.security.manager \
 -Djava.security.policy=......policy \
 -Djava.security.debug \
 -jar ......jar
```




