#!/bin/bash

# MaxGCPauseMillis
# ---------------
# Maximum garbage collection pause time: The maximum pause time
# goal is specified with the command-line option -XX:MaxGCPauseMillis=<N>.
# This is interpreted as a hint that pause times of <N> milliseconds or
# less are desired; by default, no maximum pause- time goal. If a pause-time goal
# is specified, the heap size and other parameters related to garbage collection
# are adjusted in an attempt to keep garbage collection pauses shorter than the
# specified value; however, the desired pause-time goal may not always be met.
# These adjustments may cause the garbage collector to reduce the overall
# throughput of the application.

# GCTimeRatio
# -----------
# Throughput: The throughput goal is measured in terms of the time spent doing
# garbage collection versus the time spent outside of garbage collection, referred to as application time. The goal is specified by the command-line option -XX:GCTimeRatio=<N>, which sets the ratio of garbage collection time to application time to 1 / (1 + <N>).
# For example, -XX:GCTimeRatio=19 sets a goal of 1/20 or 5% of the total time in garbage collection. The default value is 99, resulting in a goal of 1% of the time in garbage collection.


java \
 -XX:+UseParallelGC \
 -XX:MaxGCPauseMillis=10000 \
 -XX:GCTimeRatio=19 \
 -jar