DESCRIPTION = "Image for the Kobo Clara HD running a Home Assistant UI"

require clara-image-core.bb

IMAGE_INSTALL:append = " \
    rauc \
    haslate \
"

inherit extrausers
EXTRA_USERS_PARAMS = "usermod -L root"

