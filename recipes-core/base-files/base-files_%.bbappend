# Use our own fstab
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

do_install_append() {
    install -m 0755 -d ${D}/media/data
}
