# Linux 

## Create flash USB-stick from .iso image

```
sudo dd bs=4M if=path/to/input.iso of=/dev/sd* conv=fdatasync  status=progress
```

## Extract from *.tgz

```
tar -xf ...
```
