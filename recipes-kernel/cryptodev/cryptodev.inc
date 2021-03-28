HOMEPAGE = "http://cryptodev-linux.org/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/cryptodev-linux/cryptodev-linux \
           "
# SRC_URI = "git://github.com/cryptodev-linux/cryptodev-linux \
#            file://0001-Fix-module-loading-with-Linux-v5.0-rc5.patch \
#            "
SRCREV = "2299d39475e91392abd050ea09f2d20feecb4adf"

S = "${WORKDIR}/git"

CLEANBROKEN = "1"
