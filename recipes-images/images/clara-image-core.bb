
DESCRIPTION = "This is the basic core image for Kobo Clara HD"

inherit core-image

IMAGE_FEATURES += " \
    splash \
    ssh-server-openssh \
    hwcodecs \
    x11-base \
"
SDKIMAGE_FEATURES_append = " \
    staticdev-pkgs \
"

IMAGE_INSTALL_append = " \
    clara-hd-udev-rules \
    u-boot-kobo-fw-utils \
    sudo \
    networkmanager \
    tzdata \
    screen \
    xev \
    rauc \
"

IMAGE_INSTALL_remove += " virtual/perf"

inherit extrausers
EXTRA_USERS_PARAMS = "usermod -L root"