# SCTP protocol

## Check (sctp_test)

server:
```
sctp_test -H local_addr -P local_port -l
```      
client: 
```
sctp_test -H local_addr -P local_port -h remote_addr -p remote-port -s
```

## Linux API

```
int sctp_bindx(int sd, struct sockaddr * addrs, int addrcnt, int flags);
int sctp_connectx(int sd, struct sockaddr * addrs, int addrcnt, sctp_assoc_t * id);
int sctp_send(int sd, const void * msg, size_t len, const struct sctp_sndrcvinfo *sinfo, uint32_t flags);
int sctp_recvmsg(int sd, void * msg, size_t len, struct sockaddr * from, socklen_t * fromlen,  struct sctp_sndrcvinfo * sinfo, int * msg_flags);
int sctp_peeloff(int sd, sctp_assoc_t assoc_id);
int sctp_getladdrs(int sd, sctp_assoc_t assoc_id,  struct sockaddr **addrs);
void sctp_freeladdrs(struct sockaddr *addrs);
int sctp_getpaddrs(int sd, sctp_assoc_t assoc_id,  struct sockaddr **addrs);
void sctp_freepaddrs(struct sockaddr *addrs);
int sctp_opt_info(int sd, sctp_assoc_t id, int opt,  void * arg, socklen_t * size);
```

```
apt install lksctp-tools
```

## Links

https://docs.oracle.com/cd/E19253-01/817-4415/6mjum5sq5/index.html
https://linux.die.net/man/7/sctp
