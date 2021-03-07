#define IPHDRLEN(firstbyte) ((ip[firstbyte]&0xf)<<2)
#define GRESTART IPHDRLEN(0)
/* Check that is GREv1 with seq num and proto set per RFC 2637 */
#define VALID_PPTP_GRE ((ip[GRESTART:4] & 0xff7fffff) = 0x3001880b)
/* ACK is optional 4 bytes to previous 12 */
#define GRE_DATA_START (GRESTART + ((ip[GRESTART+1] & 0x80) >> 5) + 12)
/* Actual IP byte values to find in the UDP payload of inner IP datagram */
#define IS_TORRENT_UTP(udp_hdr_start)   (ip[(udp_hdr_start+20):4]=0x7fffffff)
/* Check inner IP has UDP payload (proto 17) then calculate offset and pass it to UTP macro */
#define INNER_IS_UDP(ppp_hdr_len)       (ip[GRE_DATA_START+ppp_hdr_len+9]=17)
#define INNER_UDP_OFFSET(ppp_hdr_len)   (GRE_DATA_START+ppp_hdr_len+IPHDRLEN(GRE_DATA_START+ppp_hdr_len))
#define INNER_IS_UTP(ppp_hdr_len)       (INNER_IS_UDP(ppp_hdr_len) and IS_TORRENT_UTP(INNER_UDP_OFFSET(ppp_hdr_len)))

/*
 * Finally, expression: sort by most frequent pattern first.
 * We check four possible PPP headers corresponding to IP, then
 * pass length of matched PPP header to checking macros.
 */
proto gre and VALID_PPTP_GRE and (
        (
                (ip[GRE_DATA_START]=0x21) and INNER_IS_UTP(1)
        ) or (
                (ip[GRE_DATA_START:2]=0xff03) and (ip[GRE_DATA_START+2]=0x21) and INNER_IS_UTP(3)
        ) or (
                (ip[GRE_DATA_START:4]=0xff030021) and INNER_IS_UTP(4)
        ) or (
                (ip[GRE_DATA_START:2]=0x0021) and INNER_IS_UTP(2)
        )
)