#!/usr/bin/env bash

cat acync-logger.log | \
 grep -i -F "[info][class,load]" | \
 grep -v -i -F "[info][class,load] java." | \
 grep -v -i -F "[info][class,load] javax." | \
 grep -v -i -F "[info][class,load] com.sun." | \
 grep -v -i -F "[info][class,load] jdk." | \
 grep -v -i -F "[info][class,load] sun." > acync-logger-class-loader.log
