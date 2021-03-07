for(i <- 0 until 260_000 by 100) printf(s"insert into tasks(member_start,member_end,state) values ($i, ${i+100}, 'READY');\n")
