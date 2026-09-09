# Use our own fstab
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

do_install:append() {
    install -m 0755 -d ${D}/media/data
}
