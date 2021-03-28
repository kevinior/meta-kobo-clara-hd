FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

# Remove the non-root patch
SRC_URI_remove = "file://Allow-xinput_calibrator_pointercal.sh-to-be-run-as-n.patch"

# Patch to avoid missing xinput output mode
SRC_URI_append = "file://use_xorg_conf_d.patch"

do_install_append() {
    install -d ${D}${sysconfdir}/X11/xorg.conf.d/
}
