#!/bin/bash -v

gradle temporalsmooth --args='/home/mayton/sql.ru/alibek/c1_29 "^.+\.jpg$" 200 /home/mayton/sql.ru/alibek/c1_29/out'

#ffmpeg \
# -r 1/5 \
# -i /storage/video/outimages/out%08d.png \
# -c:v libx264 \
# -vf fps=25 \
# -pix_fmt yuv420p \
#  /storage/video/out/out.mp4
