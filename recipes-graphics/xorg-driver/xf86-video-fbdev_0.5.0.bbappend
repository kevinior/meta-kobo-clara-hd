SRC_URI = "git://github.com/akemnade/xf86-video-fbdev.git;protocol=https;branch=kobo-epd-0.5.0"
SRCREV = "ac1f4a9cbcf5bbd92d90fa0c429f8990ffaccd3c"
S = "${WORKDIR}/git"

DEPENDS += "linux-yocto-akemnade"
