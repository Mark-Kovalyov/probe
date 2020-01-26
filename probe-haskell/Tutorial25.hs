-- Errors and Undefined functions

Prelude> error "ABC"
*** Exception: ABC
CallStack (from HasCallStack):
  error, called at <interactive>:31:1 in interactive:Ghci13

Prelude> undefined "ABC"
*** Exception: Prelude.undefined
CallStack (from HasCallStack):
  error, called at libraries/base/GHC/Err.hs:79:14 in base:GHC.Err
  undefined, called at <interactive>:32:1 in interactive:Ghci13

-- With error in factorial
factorial'' 0 = 1
factorial'' 1 = 1
factorial'' n = if n < 0 then error "Argument negative!" else n * factorial'' (n - 1)

-- With undefined

