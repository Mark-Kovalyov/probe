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

lshv
```
  *-display                 
       description: VGA compatible controller
       product: Ellesmere [Radeon RX 470/480/570/570X/580/580X]
       vendor: Advanced Micro Devices, Inc. [AMD/ATI]
       physical id: 0
       bus info: pci@0000:09:00.0
       version: ef
       width: 64 bits
       clock: 33MHz
       capabilities: pm pciexpress msi vga_controller bus_master cap_list rom
       configuration: driver=amdgpu latency=0
       resources: irq:75 memory:e0000000-efffffff memory:f0000000-f01fffff ioport:f000(size=256) memory:fe800000-fe83ffff memory:c0000-dffff
```
glxinfo
```
glxinfo | egrep -i 'device|memory'
    Device: Radeon RX 570 Series (POLARIS10, DRM 3.33.0, 5.3.0-51-generic, LLVM 9.0.0) (0x67df)
    Video memory: 4096MB
    Unified memory: no
Memory info (GL_ATI_meminfo):
    VBO free memory - total: 3658 MB, largest block: 3658 MB
    VBO free aux. memory - total: 4025 MB, largest block: 4025 MB
    Texture free memory - total: 3658 MB, largest block: 3658 MB
    Texture free aux. memory - total: 4025 MB, largest block: 4025 MB
    Renderbuffer free memory - total: 3658 MB, largest block: 3658 MB
    Renderbuffer free aux. memory - total: 4025 MB, largest block: 4025 MB
Memory info (GL_NVX_gpu_memory_info):
    Dedicated video memory: 4096 MB
    Total available memory: 8192 MB
    Currently available dedicated video memory: 3658 MB
    GL_AMD_performance_monitor, GL_AMD_pinned_memory, 
    GL_EXT_framebuffer_object, GL_EXT_framebuffer_sRGB, GL_EXT_memory_object, 
    GL_EXT_memory_object_fd, GL_EXT_packed_depth_stencil, GL_EXT_packed_float, 
    GL_NVX_gpu_memory_info, GL_NV_conditional_render, GL_NV_depth_clamp, 
    GL_AMD_pinned_memory, GL_AMD_query_buffer_object, 
    GL_EXT_gpu_shader4, GL_EXT_memory_object, GL_EXT_memory_object_fd, 
    GL_MESA_window_pos, GL_NVX_gpu_memory_info, GL_NV_blend_square, 
    GL_EXT_memory_object, GL_EXT_memory_object_fd, GL_EXT_multi_draw_arrays, 
```
