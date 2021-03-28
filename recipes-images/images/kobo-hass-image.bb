DESCRIPTION = "Image for the Kobo Clara HD running a Home Assistant UI"

require clara-image-core.bb

IMAGE_INSTALL_append = " \
    haslate \
"