# Installation/setup instructions

This is based on [Embedded Linux for i.MX], release 5.10.9_1.0.0 with Linux kernel 5.10.9 and Yocto 3.2 Gatesgarth.

## Install Yocto/NXP BSP

Based on instructions from [user guide].

### Install the repo tool

See the instructions in [user guide] section 3.2.

### Install NXP's Yocto

```bash
mkdir imx-linux-5.10.9_1.0.0
cd imx-linux-5.10.9_1.0.0
repo init -u https://source.codeaurora.org/external/imx/imx-manifest -b imx-linux-gatesgarth -m imx-5.10.9-1.0.0.xml
```

### Install meta-kobo-clara-hd

```bash
DISTRO=kobo-clara-hd MACHINE=kobo-clara-hd source imx-setup-release.sh -b bld-kobo-clara-hd
```

Accept the NXP EULA.

(After this you can just use `source setup-environment bld-kobo-clara-hd`)

Add the meta-kobo-clara-hd layer:
```bash
bitbake-layers add-layer ../sources/meta-kobo-clara-hd/
```

### Install meta-rauc

Assuming your current directory is bld-kobo-clara-hd:
```bash
cd ../sources
git clone https://github.com/rauc/meta-rauc.git
cd meta-rauc
git checkout gatesgarth
cd ../../bld-kobo-clara-hd
bitbake-layers add-layer ../sources/meta-rauc/
```

### Check the layer configuration

`bitbake-layers show-layers` should give you something like:
```
layer                 path                                      priority
==========================================================================
meta                  /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/poky/meta  5
meta-poky             /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/poky/meta-poky  5
meta-oe               /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/meta-openembedded/meta-oe  6
meta-multimedia       /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/meta-openembedded/meta-multimedia  6
meta-python           /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/meta-openembedded/meta-python  7
meta-freescale        /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/meta-freescale  5
meta-freescale-3rdparty  /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/meta-freescale-3rdparty  4
meta-freescale-distro  /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/meta-freescale-distro  4
meta-bsp              /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/meta-imx/meta-bsp  8
meta-sdk              /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/meta-imx/meta-sdk  8
meta-ml               /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/meta-imx/meta-ml  8
meta-nxp-demo-experience  /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/meta-nxp-demo-experience  7
meta-browser          /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/meta-browser  7
meta-rust             /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/meta-rust  7
meta-clang            /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/meta-clang  7
meta-gnome            /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/meta-openembedded/meta-gnome  7
meta-networking       /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/meta-openembedded/meta-networking  5
meta-filesystems      /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/meta-openembedded/meta-filesystems  6
meta-qt5              /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/meta-qt5  7
meta-python2          /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/meta-python2  7
meta-kobo-clara-hd    /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/meta-kobo-clara-hd  91
meta-rauc             /home/kevin/reps/imx-linux-5.10.9_1.0.0/sources/meta-rauc  6
```

### Fix local.conf

In bld-kobo-clara-hd/conf/local.conf you might want to comment out
`EXTRA_IMAGE_FEATURES ?= "debug-tweaks"`, otherwise passwordless root
login will be enabled.

## Configuration

### recipes-core/rauc

You will need to generate keys and certificates for RAUC. meta-rauc
provides [a script] to do that for you. Put the _ca.cert.pem_ and
_rauc.key.pem_ files in _recipes-core/rauc/files_.

### recipes-python/haslate

This recipe creates a user called `koboapp`. You should change the
default password in the `APPUSERHASH` variable and also add an
authorized_keys file for that user in _recipes-python/haslate/files_.


[Embedded Linux for i.MX]: https://www.nxp.com/design/software/embedded-software/i-mx-software/embedded-linux-for-i-mx-applications-processors:IMXLINUX
[user guide]: https://www.nxp.com/docs/en/user-guide/IMX_YOCTO_PROJECT_USERS_GUIDE.pdf
[a script]: https://github.com/rauc/meta-rauc/blob/master/scripts/README

## Build

`bitbake kobo-hass-image`
