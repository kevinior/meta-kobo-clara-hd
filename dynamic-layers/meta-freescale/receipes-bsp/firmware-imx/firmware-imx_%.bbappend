SRC_URI += "file://epdc_PENG060D.fw;subdir=firmware-imx-${PV}-${IMX_SRCREV_ABBREV}/firmware/epdc/"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

do_install:append() {
    ln -s epdc_PENG060D.fw ${D}${nonarch_base_libdir}/firmware/imx/epdc/epdc.fw
}
