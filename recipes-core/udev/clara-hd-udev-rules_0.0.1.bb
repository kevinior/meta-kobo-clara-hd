SUMMARY = "Extra udev rules for the Kobo Clara HD"
AUTHOR = "Kevin O'Rourke <misc1-claradev@caboose.org.uk>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI = "file://99-kobo-clara-hd.rules"

do_install () {
	install -d ${D}${sysconfdir}/udev/rules.d
	install -m 0644 ${WORKDIR}/99-kobo-clara-hd.rules ${D}${sysconfdir}/udev/rules.d/
}
