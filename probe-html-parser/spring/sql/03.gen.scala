scala -e "for(i <- 0 until 260_000 by 10) printf(\"insert into tasks(member_start,member_end,state) values (\" + i + \", \" + ( i + 10 ) + \", 'READY');\n\")" > 05.insert_tasks.sql

psql -d dht -a -f $file
