require u-boot-fw-utils_2019.07.bb

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRCBRANCH = "e60k02"
SRC_URI = "git://git.goldelico.com/letux-uboot.git;protocol=https;branch=${SRCBRANCH} \
           file://0001-Fix-U-boot-issue-with-newer-libfdt.patch \
           file://0002-Add-Kobo-Clara-HD-board.patch \
           file://0003-Fix-broken-HOSTCC-when-building-tools.patch \
           file://fw_env.config \
           "
SRCREV = "29d26e12b578e6f46dace7a0e82432d06ff42366"
LIC_FILES_CHKSUM = "file://Licenses/gpl-2.0.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit fsl-u-boot-localversion

LOCALVERSION ?= "-5.4.24-2.1.0"

PROVIDES += "u-boot-fw-utils"
RPROVIDES_${PN} += "u-boot-fw-utils"

# The envtools target is just called env in older U-boot
do_compile () {
	oe_runmake ${UBOOT_MACHINE}
	oe_runmake env
}

# Install our fw_env.config rather than the default.
do_install_append () {
    install -m 0644 ${WORKDIR}/fw_env.config ${D}${sysconfdir}/fw_env.config
}