# Installation/setup instructions

This is based on Yocto 6.0 Wrynose.

## Prepare build using bitbake-layers created files
You need to have bitbake available manually
```bash
cd ..
meta-kobo-clara-hd/setup-layers --destdir build
TEMPLATECONF=$PWD/meta-kobo-clara-hd/conf/templates/kobo-clara-hd-default build/setup-build
```
## Or prepare build v ia bitbake-setup
Install bitbake-setup via pip/pipx and then build it.
```bash
pipx install bitbake-setup
cd ..
bitbake-setup init $PWD/meta-kobo-clara-hd/kobo-clara-hd.conf.json
cd bitbake-builds/kobo-clara-hd2-kobo-clara-hd-systemd-xorg/build/
. ./init-build-env
```
## Haslate configuration (unmaintained)

### recipes-core/rauc

You will need to generate keys and certificates for RAUC. meta-rauc
provides [a script] to do that for you. Put the _ca.cert.pem_ and
_rauc.key.pem_ files in _recipes-core/rauc/files_.

### recipes-python/haslate

This recipe creates a user called `koboapp`. You should change the
default password in the `APPUSERHASH` variable and also add an
authorized_keys file for that user in _recipes-python/haslate/files_.


## Build generic image
`bitbake clara-image-core`

you need to add epdc.fw afterwards using extracted data from original system.
This one creates an image with *EMPTY ROOT PASSWORD*, usb network is accessible
via link-scoped address `fe80::4` so it can be reached with `ssh root@fe80::4%usb0`

## Build haslate image
`bitbake kobo-hass-image`
