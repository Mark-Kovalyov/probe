#!/bin/bash -v

ffmpeg -i input.mkv -ss 00:00:00.000 -vframes 288 out%08d.png

