#!/bin/bash -v

ffmpeg \
 -i /storage/video/in/input.mkv \
 -ss 00:00:00.000 \
 -vframes 288 \
 /storage/video/outimages/out%08d.png

gradle temporal-smooth --args='/storage/video/in /storage/video/outimages'

ffmpeg \
 -r 1/5 \
 -i /storage/video/outimages/out%08d.png \
 -c:v libx264 \
 -vf fps=25 \
 -pix_fmt yuv420p \
  /storage/video/out/out.mp4
