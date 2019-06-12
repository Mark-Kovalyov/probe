#!/usr/bin/env bash

curl 'http://snowtooth.herokuapp.com/'
  -H 'Content-Type: application/json'
  --data '{"query":"{ allLifts { name }}"}'