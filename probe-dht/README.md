# DHT investigation

# Clients, Ports, Protocols

    // Port(s) 	Protocol 	Service 	 Details 	Source
    // =======================================================================
    // 1900     udp         aMule
    // 52263    udp         aMule
    // 4665     udp         aMule        eDonkey2000  eDonkey2000 Server Messaging Default Port,
    //                                   Container Client Message Service, AudioReQuest
    // 4672     udp         aMule        eMule - often used (unofficial)
    //                                   Extended eMule protocol, Queue Rating, File Reask Ping,
    //                                   Kad. Kad will be 'firewalled' if NAT (Network Address Translation)
    //                                   remaps this port number.
    // 4662     tcp         aMule        Client-to-client transfers.
    //
    // 3246 	udp 	    kademlia 	 Kademlia P2P (mlnet)
    //
    // 4661 TCP (outgoing):              Port on which a server listens for connection (defined by server).
    //
    //
    // 5351 	tcp,udp 		         NAT Port Mapping Protocol -
    //                                   client-requested configuration
    //                                   for inbound connections through
    //                                   network address translators (official)
    //

    // 23705
    // 51413 	tcp,udp 	p2p 	     Commonly used by Transmission BitTorrent Client.
    //
    //

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
