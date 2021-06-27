# Reizerfs and Reizer-4

* Reizer-4 https://sourceforge.net/projects/reiser4/
* Reizer (in web archive) https://web.archive.org/web/20071023172417/http://www.namesys.com/

## Install

```
sudo apt install reiserfsprogs
```

## Usage
```
Usage: mkfs.reiserfs [options]  device [block-count]

Options:

  -b | --block-size N              size of file-system block, in bytes
  -j | --journal-device FILE       path to separate device to hold journal
  -s | --journal-size N            size of the journal in blocks
  -o | --journal-offset N          offset of the journal from the start of
                                   the separate device, in blocks
  -t | --transaction-max-size N    maximal size of transaction, in blocks
  -B | --badblocks file            store all bad blocks given in file on the fs
  -h | --hash rupasov|tea|r5       hash function to use by default
  -u | --uuid UUID                 store UUID in the superblock
  -l | --label LABEL               store LABEL in the superblock
  --format 3.5|3.6                 old 3.5 format or newer 3.6
  -f | --force                     specified once, make mkreiserfs the whole
                                   disk, not block device or mounted partition;
                                   specified twice, do not ask for confirmation
  -q | --quiet                     quiet work without messages, progress and
                                   questions. Useful if run in a script. For use
                                   by end users only.
  -d | --debug                     print debugging information during mkreiser
  -V                               print version and exit

```

## See also

* reiserfsck
* debugreiserfs
* reiserfstune

