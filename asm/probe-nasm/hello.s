global _start

section .text

_start:

  mov rax, 1        ; write(
  mov rdi, 1        ;   STDOUT_FILENO,
  mov rsi, msg      ;   "Hello, world!\n",
  mov rdx, msglen   ;   sizeof("Hello, world!\n")
  syscall           ; );

  mov rax, 60       ; exit(
  mov rdi, 0        ;   EXIT_SUCCESS
  syscall           ; );

section .rodata

  msg: db "Start", 0Ah
  msglen: equ $ - msg

  msg2: db "Finish", 0Ah
  msglen2: equ $ - msg2
