SUMMARY = "USB gadget config"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI = " \
    file://55-gadget.rules \
    file://config-gadget.sh \
    file://79-usb-def.network \
"

S = "${UNPACKDIR}"

FILES:${PN} += " /usr/lib/systemd/network/79-usb-def.network "

do_install() {
    install -D -m 755 ${WORKDIR}/sources/55-gadget.rules ${D}/etc/udev/rules.d/55-gadget.rules
    install -D -m 755 ${WORKDIR}/sources/config-gadget.sh ${D}/usr/bin/config-gadget.sh
    install -D -m 644 ${WORKDIR}/sources/79-usb-def.network ${D}/usr/lib/systemd/network/79-usb-def.network
}
