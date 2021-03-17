# NFS

## Firewall rules

```
ufw allow 111/tcp comment "NFS:111/tcp"
ufw allow 111/udp comment "NFS:111/udp"
ufw allow 2049/tcp comment "NFS:2049/tcp"
ufw allow 2049/udp comment "NFS:2049/udp"

```

## Install/Status

```
apt-get install nfs-kernel-server nfs-common
...

service nfs-kernel-server status
- nfs-server.service - NFS server and services
     Loaded: loaded (/lib/systemd/system/nfs-server.service; enabled; vendor preset: enabled)
     Active: active (exited) since Mon 2021-03-08 22:15:06 EET; 16s ago
   Main PID: 35873 (code=exited, status=0/SUCCESS)
      Tasks: 0 (limit: 19059)
     Memory: 0B
     CGroup: /system.slice/nfs-server.service
```

## Configure

```
$ nano /etc/exports

+add

/data 192.168.1.0/24(rw,insecure,nohide,all_squash,anonuid=1000,anongid=1000,no_subtree_check)
```

## Restart
```
/etc/init.d/nfs-kernel-server restart
```

Ports
=====

```
111(TCP)
111(UDP)
2049(TCP)
2049(UDP)
```


## Troubleshooting (serverside)

```
showmount -e
showmount -a
rpcinfo -p
```

If the timeout is happened during mount then

```
/etc/sysconfig/nfs 
+SECURE_NFS=no.
```


## Mounting NFS4

Check ports is opened
```
nc -zv 192.168.1.200 111
Connection to 192.168.1.200 111 port [tcp/sunrpc] succeeded!
 
nc -zv 192.168.1.200 2049
Connection to 192.168.1.200 2049 port [tcp/nfs] succeeded!
```

Mount 
```
mount 192.168.1.200:/nfsshare /mnt/nfsshare
```

Mount NFS4
```
mount -t nfs4 <host>:/ <mountpoint>
```

