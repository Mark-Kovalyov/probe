# Deduplication

## rdfnd
```
$ sudo apt-get install rdfind     [On Debian/Ubuntu]
```

Features
* dryrun
* make hard links
* deleteduplicates

## Fdupes

```
$ sudo apt-get install fdupes     [On Debian/Ubuntu]
```

## Experimental data

```
fdupes -R --immediate --noempty --delete --noprompt .
```
|parameter|before     |after|diff|
|---------|-----------|-----|----|
|bytes    |88 401 542 187|79 209 713 128|9 191 829 059|
|files    |246436     |194742|51694|

Features
* optionally uses md5 signatures

## dupeGuru

```
$ sudo add-apt-repository ppa:dupeguru/ppa
$ sudo apt-get update
$ sudo apt-get install dupeguru
```

Features
* UI only?



