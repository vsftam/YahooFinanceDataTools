#!/bin/bash

ant -buildfile build_manual.xml run -Dstart $1 -Dend $2 -Dticker $3
