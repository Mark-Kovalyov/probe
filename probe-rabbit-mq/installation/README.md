# RabbitMQ 

## Ports

|Port |Protocol|
|-----|--------|
|4369 |epmd, a peer discovery service used by RabbitMQ nodes and CLI tools|
|5672, 5671| used by AMQP 0-9-1 and 1.0 clients without and with TLS|
|25672| used for inter-node and CLI tools communication (Erlang distribution server port) and is allocated from a dynamic range (limited to a single port by default, computed as AMQP port + 20000). Unless external connections on these ports are really necessary (e.g. the cluster uses federation or CLI tools are used on machines outside the subnet), these ports should not be publicly exposed. See networking guide for details.|
|35672-35682| used by CLI tools (Erlang distribution client ports) for communication with nodes and is allocated from a dynamic range (computed as server distribution port + 10000 through server distribution port + 10010). See networking guide for details.|
|15672| HTTP API clients, management UI and rabbitmqadmin (only if the management plugin is enabled)|
|61613, 61614| STOMP clients without and with TLS (only if the STOMP plugin is enabled)|
|1883, 8883| (MQTT clients without and with TLS, if the MQTT plugin is enabled|
|15674| STOMP-over-WebSockets clients (only if the Web STOMP plugin is enabled)|
|15675| MQTT-over-WebSockets clients (only if the Web MQTT plugin is enabled)|
|15692| Prometheus metrics (only if the Prometheus plugin is enabled)|

## Ufw

```
ufw allow from 192.168.1.0/24 to any port 5671 comment "RabbitMQ-AMQP(5671)"
```


## Installation

```
apt install apt-transport-https
apt install rabbitmq-server
apt install erlang
apt install amqp-tools
```

apt show rabbitmq-server
```
Package: rabbitmq-server
Version: 3.6.10-1ubuntu0.1
Priority: extra
Section: net
Origin: Ubuntu
Maintainer: Ubuntu Developers <ubuntu-devel-discuss@lists.ubuntu.com>
Original-Maintainer: PKG OpenStack <openstack-devel@lists.alioth.debian.org>
Bugs: https://bugs.launchpad.net/ubuntu/+filebug
Installed-Size: 5,678 kB
Depends: adduser, erlang-nox (>= 1:13.b.3) | esl-erlang, lsb-base, logrotate, python:any
Homepage: http://www.rabbitmq.com/
Supported: 5y
Download-Size: 4,651 kB
APT-Manual-Installed: yes
APT-Sources: http://38.archive.ubuntu.com/ubuntu bionic-updates/main amd64 Packages
Description: AMQP server written in Erlang
 RabbitMQ is an implementation of AMQP, the emerging standard for high
 performance enterprise messaging. The RabbitMQ server is a robust and
 scalable implementation of an AMQP broker.
```

apt show erlang
```
Package: erlang
Version: 1:20.2.2+dfsg-1ubuntu2
Priority: optional
Section: universe/interpreters
Origin: Ubuntu
Maintainer: Ubuntu Developers <ubuntu-devel-discuss@lists.ubuntu.com>
Original-Maintainer: Debian Erlang Packagers <pkg-erlang-devel@lists.alioth.debian.org>
Bugs: https://bugs.launchpad.net/ubuntu/+filebug
Installed-Size: 51.2 kB
Depends: erlang-base | erlang-base-hipe, erlang-dev, erlang-asn1, erlang-common-test, erlang-corba, erlang-crypto, erlang-debugger, erlang-dialyzer, erlang-diameter, erlang-edoc, erlang-eldap, erlang-erl-docgen, erlang-et, erlang-eunit, erlang-ic, erlang-inets, erlang-megaco, erlang-mnesia, erlang-observer, erlang-odbc, erlang-os-mon, erlang-parsetools, erlang-public-key, erlang-reltool, erlang-runtime-tools, erlang-snmp, erlang-ssh, erlang-ssl, erlang-syntax-tools, erlang-tools, erlang-wx, erlang-xmerl
Recommends: erlang-jinterface, erlang-ic-java, erlang-mode, erlang-src, erlang-examples
Suggests: erlang-manpages, erlang-doc
Homepage: http://www.erlang.org/
Download-Size: 13.7 kB
APT-Manual-Installed: yes
APT-Sources: http://38.archive.ubuntu.com/ubuntu bionic/universe amd64 Packages
Description: Concurrent, real-time, distributed functional language
 Open Source Erlang is a functional programming language designed at
 the Ericsson Computer Science Laboratory.
```

## Status

```
$ systemctl status rabbitmq-server
● rabbitmq-server.service - RabbitMQ Messaging Server
   Loaded: loaded (/lib/systemd/system/rabbitmq-server.service; enabled; vendor preset: enabled)
   Active: active (running) since Sun 2020-05-10 15:32:26 EEST; 7min ago
 Main PID: 9267 (rabbitmq-server)
    Tasks: 236 (limit: 4915)
   CGroup: /system.slice/rabbitmq-server.service
           ├─9267 /bin/sh /usr/sbin/rabbitmq-server
           ├─9276 /bin/sh /usr/lib/rabbitmq/bin/rabbitmq-server
           ├─9458 /usr/lib/erlang/erts-9.2/bin/epmd -daemon
           ├─9605 /usr/lib/erlang/erts-9.2/bin/beam.smp -W w -A 192 -P 1048576 -t 5000000 
                      -stbt db -zdbbl 32000 -K true -B i -- -root /usr/lib/erlang -progname erl -- 
                      -home /var/lib/rabbitmq -- -pa /usr/lib/rabb
           ├─9862 erl_child_setup 65536
           ├─9967 inet_gethost 4
           └─9968 inet_gethost 4
```

Start

```
systemctl start rabbitmq-server
```

Enable auto-start
```
systemctl enable rabbitmq-server
```

## Add user

```

```