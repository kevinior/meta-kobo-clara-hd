# Copyright (C) 2013-2016 Freescale Semiconductor
# Copyright 2017-2020 NXP

DESCRIPTION = "i.MX U-Boot suppporting Kobo boards."
require recipes-bsp/u-boot/u-boot-common.inc
require recipes-bsp/u-boot/u-boot.inc
inherit python3native 

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

PROVIDES += "u-boot"
DEPENDS:append = " dtc-native"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

UBOOT_SRC ?= "git://github.com/akemnade/u-boot-fslc.git;protocol=https"
SRCBRANCH = "kobo-2023-10"
SRC_URI = "${UBOOT_SRC};branch=${SRCBRANCH} \
           "
SRCREV = "1f55e1ef7b1ac1e0cdb1375e787f287c33599220"

LOCALVERSION ?= "-kobo"

BOOT_TOOLS = "imx-boot-tools"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(mx6|mx7|mx8)"

UBOOT_NAME_mx6 = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
UBOOT_NAME_mx7 = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
UBOOT_NAME_mx8 = "u-boot-${MACHINE}.bin-${UBOOT_CONFIG}"
