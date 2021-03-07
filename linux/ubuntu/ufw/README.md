# UFW

```
ufw_rule ::= "ufw" ( "allow" | "deny" ) ((symbolic_port | number) | ("proto" protocol) ) ? ( "from" | "in on" ) from_expr dest_expr ? comment_expr ? 

protocol ::= ( "tcp" | "udp" | "icmp" )

symbolic_port ::= ( "ssh" | "http" )

number ::= ('0'|'1'|'2'|'3'|'4'|'5'|'6'|'7'|'8'|'9') +

from_expr ::= number '.' number '.' number '.' number

comment_expr ::= ( "comment" comment_text )

dest_expr ::= ( "to" "any" "port" portnumber )

ip_mask ::= ip "/" mask

ufw_delete_rule ::= "ufw delete" number
```

## Ex:
```
/* ufw allow ssh */
/* ufw allow http */
/* ufw allow 80 */
/* ufw allow proto tcp from any to any port 80,443 */
/* ufw deny in on eth0 from 15.15.15.51 */
/* ufw deny from 15.15.15.51 */
```
