# Haskell

## GHCI

Multiline input (1)
```
:{
main = do
  putStrLn "Hello"
:}
```

Multiline input (2)
```
Prelude> :set +m
Prelude> main = do
Prelude|     putStrLn "Hello"
Prelude|
Prelude>
```

## Stack

Upgrade
```
$ stack upgrade
Current Stack version: 2.3.3, available download version: 2.5.1
Newer version detected, downloading
....

$ stack --version
Version 2.5.1, Git revision d6ab861544918185236cf826cb2028abb266d6d5 x86_64 hpack-0.33.0
```
Create application
```
stack new helloworld new-template
```
Build/test/run
```
stack build
stack test
stack run
```

### Install package

```
stack install statistics
...
statistics             > copy/register
statistics             > Installing library in /home/$USER/.stack/snapshots/x86_64-linux-tinfo6/........../8.8.4/lib/x86_64-linux-ghc-8.8.4/statistics-0.15.2.0-6QNtjXG6OAf9o6A0kgqLEV
statistics             > Registering library for statistics-0.15.2.0..
Completed 12 action(s).   
  
```

# Links

* Haskell
https://www.youtube.com/channel/UCi6tS_5UEmIkMWNVNbI-tAQ

* CSC
https://www.youtube.com/watch?v=4fmE7UmlDEs

* Monads
https://www.youtube.com/watch?v=4SKaTslDqbY


