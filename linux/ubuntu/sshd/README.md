# SSHD

## Install
```
$ apt install sshd
$ apt install ssh
```
## Status/Start
```
systemctl status ssh.service
● ssh.service - OpenBSD Secure Shell server
     Loaded: loaded (/lib/systemd/system/ssh.service; enabled; vendor preset: enabled)
     Active: active (running) since Mon 2021-03-08 15:03:42 EET; 4h 50min ago
```
## Allow firewall rules for SSH
```
$ ufw allow ssh
$ Rule added
$ Rule added (v6)
```

