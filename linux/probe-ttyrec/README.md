# TTYrec

## Video-codecs

* MP4
* H.264
* WebM
* Ogg Theora

## H.264 (AVC, MPEG-4 Part 10)

Download src from https://sourceforge.net/projects/ffx264/

## WebM (.webm, video/webm) VP8,VP9

```
ffmpeg -i input.flv -vcodec libvpx -acodec libvorbis output.webm
```

## Ogg Theora (.ogv, .ogg, video/ogg)

```
sudo apt install ffmpeg2theora
```

```
$ ffmpeg2theora
ffmpeg2theora 0.29

    Xiph.Org libtheora 1.1 20090822 (Thusnelda)
    Xiph.Org libVorbis 1.3.5
    FFmpeg	 libavcodec 57.107.100
    FFmpeg	 libavformat 57.83.100


  Usage: ffmpeg2theora [options] input

General output options:
  -o, --output           alternative output filename
      --no-skeleton      disables ogg skeleton metadata output
      --skeleton-3       outputs Skeleton Version 3, without keyframe indexes
  -s, --starttime        start encoding at this time (in sec.)
  -e, --endtime          end encoding at this time (in sec.)
  -p, --preset           encode file with preset.
                          Right now there is preview, pro and videobin. Run
                          'ffmpeg2theora -p info' for more informations
```