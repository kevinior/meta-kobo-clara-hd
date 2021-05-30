# Based on:
# linux-yocto-custom.bb:
#
#   An example kernel recipe that uses the linux-yocto and oe-core
#   kernel classes to apply a subset of yocto kernel management to git
#   managed kernel repositories.
#
#   To use linux-yocto-custom in your layer, copy this recipe (optionally
#   rename it as well) and modify it appropriately for your machine. i.e.:
#
#     COMPATIBLE_MACHINE_yourmachine = "yourmachine"
#
#   You must also provide a Linux kernel configuration. The most direct
#   method is to copy your .config to files/defconfig in your layer,
#   in the same directory as the copy (and rename) of this recipe and
#   add file://defconfig to your SRC_URI.
#
#   To use the yocto kernel tooling to generate a BSP configuration
#   using modular configuration fragments, see the yocto-bsp and
#   yocto-kernel tools documentation.
#
# Warning:
#
#   Building this example without providing a defconfig or BSP
#   configuration will result in build or boot errors. This is not a
#   bug.
#
#
# Notes:
#
#   patches: patches can be merged into to the source git tree itself,
#            added via the SRC_URI, or controlled via a BSP
#            configuration.
#
#   defconfig: When a defconfig is provided, the linux-yocto configuration
#              uses the filename as a trigger to use a 'allnoconfig' baseline
#              before merging the defconfig into the build.
#
#              If the defconfig file was created with make_savedefconfig,
#              not all options are specified, and should be restored with their
#              defaults, not set to 'n'. To properly expand a defconfig like
#              this, specify: KCONFIG_MODE="--alldefconfig" in the kernel
#              recipe.
#
#   example configuration addition:
#            SRC_URI += "file://smp.cfg"
#   example patch addition (for kernel v4.x only):
#            SRC_URI += "file://0001-linux-version-tweak.patch"
#   example feature addition (for kernel v4.x only):
#            SRC_URI += "file://feature.scc"
#

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

# Override SRC_URI in a copy of this recipe to point at a different source
# tree if you do not want to build from Linus' tree.
SRC_URI = "git://github.com/akemnade/linux.git;protocol=https;nocheckout=1;branch=kobo/merged-5.10;name=machine"

# defconfig copied from:
# https://github.com/akemnade/linux/blob/kobo/merged-5.10/arch/arm/configs/kobo_defconfig
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += " file://defconfig "

LINUX_VERSION ?= "5.10"
LINUX_VERSION_EXTENSION_append = "-akemnade"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

# Modify SRCREV to a different commit hash in a copy of this recipe to
# build a different release of the Linux kernel.
# tag: v4.2 64291f7db5bd8150a74ad2036f1037e6a0428df2
SRCREV_machine="7d2632dc809f70583e164fa863acaa5af5bf688a"

PV = "${LINUX_VERSION}+git${SRCPV}"

# Override COMPATIBLE_MACHINE to include your machine in a copy of this recipe
# file. Leaving it empty here ensures an early explicit build failure.
COMPATIBLE_MACHINE_kobo-clara-hd = "kobo-clara-hd"

# See the comment at the top of this recipe.
KCONFIG_MODE="--alldefconfig"

# For some reason this is needed
DEPENDS += " lzop-native"


#
# This is a bit of a hack to do roughly what linux-imx-headers does for
# linux-imx, from: https://patchwork.openembedded.org/patch/131375/
#
IMX_UAPI_HEADERS = "mxc_asrc.h mxc_dcic.h mxcfb.h mxc_mlb.h mxc_sim_interface.h \
                    mxc_v4l2.h ipu.h videodev2.h pxp_device.h pxp_dma.h isl29023.h"

do_install_append () {
   # Install i.MX specific uapi headers
   oe_runmake headers_install INSTALL_HDR_PATH=${B}${exec_prefix}
   install -d ${D}${exec_prefix}/include/linux
   for UAPI_HDR in ${IMX_UAPI_HEADERS}; do
       find ${B}${exec_prefix}/include -name ${UAPI_HDR} -exec cp {} ${D}${exec_prefix}/include/linux \;
       ls ${D}${exec_prefix}/include/linux
       echo "copy ${UAPI_HDR} done"
   done
}

sysroot_stage_all_append () {
    # FIXME: Remove videodev2.h as conflict with linux-libc-headers
    find ${D}${exec_prefix}/include -name videodev2.h -exec mv {} ${B} \;
    # Install SOC related uapi headers to sysroot
    sysroot_stage_dir ${D}${exec_prefix}/include ${SYSROOT_DESTDIR}${exec_prefix}/include
    # FIXME: Restore videodev2 back
    if [ -e ${B}/videodev2.h ]; then
        mv ${B}/videodev2.h ${D}${exec_prefix}/include/linux/
    fi
}

PACKAGES += "${PN}-soc-headers"
FILES_${PN}-soc-headers = "${exec_prefix}/include"
