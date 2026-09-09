#!/bin/sh
set -e
modprobe usb_f_ncm || true
cd /sys/kernel/config/usb_gadget
mkdir etherinst || true
cd etherinst
cd functions
mkdir ncm.0
mkdir mass_storage.0
echo 0 >mass_storage.0/stall
#echo 1 >mass_storage.0/lun.0/ro
#echo /home/andi/debian-11.2.0-amd64-netinst.iso >mass_storage.0/lun.0/file
cd ../configs
mkdir c.1
cd c.1
for name in ../../functions/*
do
  ln -s $name .
done
cd ../..
(cd /sys/class/udc ; echo * ) >UDC || true
sleep 2
ifup usb0 || true
ip -6 addr add fe80::4/64 scope link dev usb0
ip link set usb0 up
