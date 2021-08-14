
SUMMARY = "E-ink Home Assistant Dashboard"
AUTHOR = "Kevin O'Rourke <misc1-claradev@caboose.org.uk>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=df115800ba1a50be5111abfce7d9ceb0"

PV = "1.0.2+git${SRCPV}"
SRCREV = "1b97619cce3d399787d75994c4dc324e42f76b74"
SRC_URI = " \
    git://github.com/kevinior/haslate.git;protocol=https;branch=main \
    file://haslate.service.in \
    file://authorized_keys \
    file://01_sudo_group \
    file://02_haslate_commands.in \
    file://Xsession \
"

SRC_URI[md5sum] = "6353c0eb2367f70b8c8d07d3decc1e46"
SRC_URI[sha256sum] = "8b1e7b63f47aafcdd8849933b206778747ef1802bd3d526aca45ed77141e4001"

S = "${WORKDIR}/git"

DEPENDS += "python3-pygame python3-ruamel-yaml python3-websockets "
RDEPENDS_${PN} = "python3-pygame python3-ruamel-yaml python3-websockets python3-modules networkmanager-nmcli wireless-tools"

# The non-root username to create and run under.
APPUSER = "koboapp"

# The hashed password of the non-root user.
APPUSERHASH = "eqfawmAyTKDxs"

FILES_${PN} += "/home/${APPUSER} /home/root/.Xsession"

# Start the application automatically using systemd
inherit systemd

SYSTEMD_SERVICE_${PN} = "haslate.service"

# Create a non-root user to run the application as.
# Note that you'll need to add your own authorized_keys file.
inherit useradd

USERADD_PACKAGES = "${PN}"

USERADD_PARAM_${PN} = "-p ${APPUSERHASH} -G sudo ${APPUSER}"

# There's a high chance of the UID being the same as the user running
# bitbake and causing lots of 'host-user-contaminated' errors, so
# we have to ignore those.
INSANE_SKIP_${PN} += "host-user-contaminated"


do_install() {
    install -m 0700 -o ${APPUSER} -g ${APPUSER} -d ${D}/home/${APPUSER}/.ssh/
    install -m 0644 -o ${APPUSER} -g ${APPUSER} ${WORKDIR}/authorized_keys ${D}/home/${APPUSER}/.ssh/authorized_keys
    install -m 0750 -d ${D}${sysconfdir}/sudoers.d/
    install -m 0750 ${WORKDIR}/01_sudo_group ${D}${sysconfdir}/sudoers.d/01_sudo_group
    install -m 0750 ${WORKDIR}/02_haslate_commands.in ${D}${sysconfdir}/sudoers.d/02_haslate_commands
    sed -i "s:@USER@:${APPUSER}:" ${D}${sysconfdir}/sudoers.d/02_haslate_commands

    install -d ${D}/home/${APPUSER}/haslate
    oe_runmake install DESTDIR=${D}/home/${APPUSER}/haslate
    chown -R ${APPUSER}:${APPUSER} ${D}/home/${APPUSER}/haslate

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/haslate.service.in ${D}${systemd_unitdir}/system/haslate.service
    sed -i "s:@USER@:${APPUSER}:" ${D}${systemd_unitdir}/system/haslate.service

    install -d ${D}/home/root
    install ${WORKDIR}/Xsession ${D}/home/root/.Xsession
}