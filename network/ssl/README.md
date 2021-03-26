# SSL

## Check certificate

```
keytool -printcert -sslserver google.com:443 | head
```

Yet another approach:
```
openssl s_client -showcerts -connect google.com:443
```
