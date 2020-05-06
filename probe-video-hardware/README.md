# Video hardware

## LSHV
```
lshw -C display
```

## For ATI/AMD GPUs
```
aticonfig --odgc
```

## For NVIDIA GPUs
```
nvclock
```

## OpenGL information
```
glxinfo 
glxinfo | egrep -i 'device|memory'
```

## LSPCI
```
lspci | grep ' VGA ' | cut -d" " -f 1 | xargs -i lspci -v -s {}
```

(nvidia users)
```
nvidia-smi
```

## Example

### Gigabyte Radeon RX570 Gaming 4G (GV-RX570GAMING-4GD)

* Chipset: RX 570
* Memory : 4 √¡
* Freq : 1255 Ã√ˆ
* Bus : 256 ·ËÚ
* Memory type : GDDR5
* Output DVI : Dual Link DVI-D (HDCP), 3ıDisplayPort, HDMI
* Interface : PCI Express 3.0
