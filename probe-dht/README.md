# DHT investigation

## Commands

## Datagram Sockets


## Firewall


Potential firewall rule:
```
/ip firewall filter
 
add action=drop chain=forward in-interface=bridge-hs p2p=all-p2p

add action=drop chain=forward comment=\
  content=d1:ad2:id20: dst-port=\
  1025-65535 in-interface=bridge-hs \
  packet-size=95-190 \
  protocol=udp
```
