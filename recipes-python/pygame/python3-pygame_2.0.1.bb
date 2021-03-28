
SUMMARY = "Python Game Development"
HOMEPAGE = "https://www.pygame.org"
AUTHOR = "A community project. <pygame@pygame.org>"
LICENSE = "LGPL-2.0"
LIC_FILES_CHKSUM = "file://setup.py;md5=044701b7c1170de8bfe6dc769fc6300b"

SRC_URI = "https://files.pythonhosted.org/packages/c7/b8/06e02c7cca7aec915839927a9aa19f749ac17a3d2bb2610b945d2de0aa96/pygame-2.0.1.tar.gz \
           file://0001-Fix-dependency-searching.patch \
           "
SRC_URI[md5sum] = "6353c0eb2367f70b8c8d07d3decc1e46"
SRC_URI[sha256sum] = "8b1e7b63f47aafcdd8849933b206778747ef1802bd3d526aca45ed77141e4001"

S = "${WORKDIR}/pygame-2.0.1"

DEPENDS += "virtual/libsdl2 libsdl2-image libsdl2-ttf freetype"
RDEPENDS_${PN} = ""

inherit setuptools3
