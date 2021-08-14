SUMMARY = "RTL8189FS wifi module"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://Makefile;beginline=1;endline=1;md5=daad6f7f7a0a286391cd7773ccf79340"

inherit module

SRC_URI = "git://github.com/jwrdegoede/rtl8189ES_linux.git;protocol=https;branch=rtl8189fs \
           file://0001-Fix-build-problems-in-Yocto.patch \
           "
SRCREV = "62c31d577c385316bb99107f60e63169dacc37db"

S = "${WORKDIR}/git"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES_${PN} += "kernel-module-8189fs"

# This module's makefile doesn't follow the usual conventions.
MODULES_INSTALL_TARGET = "install"

do_install_prepend() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
}