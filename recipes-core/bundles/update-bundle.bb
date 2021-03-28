inherit bundle
RAUC_BUNDLE_COMPATIBLE = "Kobo Clara HD"
RAUC_BUNDLE_SLOTS = "rootfs"
RAUC_SLOT_rootfs = "kobo-hass-image"
RAUC_SLOT_rootfs[fstype] = "ext4"
RAUC_KEY_FILE = "${COREBASE}/../meta-kobo-clara-hd/recipes-core/rauc/files/rauc.key.pem"
RAUC_CERT_FILE = "${COREBASE}/../meta-kobo-clara-hd/recipes-core/rauc/files/ca.cert.pem"
