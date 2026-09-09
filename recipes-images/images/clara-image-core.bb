
DESCRIPTION = "This is the basic core image for Kobo Clara HD"

inherit core-image

IMAGE_FEATURES += " \
    splash \
    ssh-server-openssh \
    hwcodecs \
    x11-base \
"
SDKIMAGE_FEATURES:append = " \
    staticdev-pkgs \
"

IMAGE_INSTALL:append = " \
    sudo \
    networkmanager \
    tzdata \
    screen \
    xev \
    kernel-modules \
    gadgetconf \
    u-boot-kobo-extlinux \
"

IMAGE_INSTALL:remove = " virtual/perf"
IMAGE_FSTYPES:append = " wic.zst"

inherit extrausers
#EXTRA_USERS_PARAMS = "usermod -L root"
