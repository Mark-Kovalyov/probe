#!/bin/bash

GO=$(which go)

test -x $GO || exit 0

$GO build -o gilbert.exe gilbert.go
