SUMMARY = "RTL8189FS wifi module"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://os_dep/linux/os_intfs.c;beginline=1;endline=14;md5=d1415c223f48d77507154f01e7fa002f"

inherit module

SRC_URI = "git://github.com/jwrdegoede/rtl8189ES_linux.git;protocol=https;branch=rtl8189fs \
           file://0001-Fix-build-problems-in-Yocto.patch \
           file://0001-Makefile-change-defaults-to-have-less-debug-noise-an.patch \
           "
SRCREV = "a5ad16ed1d64fe1facce95bbcc2360c8c846a681"


# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

RPROVIDES:${PN} += "kernel-module-8189fs"

# This module's makefile doesn't follow the usual conventions.
MODULES_INSTALL_TARGET = "install"

do_install:prepend() {
    install -d ${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}/kernel/drivers/net/wireless
}
