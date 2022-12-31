#!/bin/ksh

SRC_DIR=$HOME/projects/nbsadapter/hl7-data-files/ELR-ExampleMessages/Elr-ExampleMessages-1.9.2-012216
DST_DIR=$HOME/projects/nbsadapter/hl7-data-files/report-stream-generated-files
SLEEP_INTERVAL=10

echo "This script copies hl7 test files every $SLEEP_INTERVAL seconds to target directory"

for ii in `ls $SRC_DIR`
do
  echo "Copying $ii to $DST_DIR"
  cp $SRC_DIR/$ii $DST_DIR
  sleep $SLEEP_INTERVAL
done

