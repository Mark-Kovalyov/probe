# OpenCL / CUDA e.t.c

OpenCL
* https://www.khronos.org/opencl/

Heterogenous compute acceleration for
* CPU
* CPU
* FPGA
* DSP
* AI / Tensor HW
* Custom

## C++ for OpenCL

```
clang -cl-std=clc++ test.cl
```

```
OpenCL C       =>
                     Clang => LLVM => SPIR-V LLVM IR Translator => SPIR => OpenCL
C++ for OpenCL =>

```

## Install OPenCL

apt install ocl-icd-opencl-dev
