## from meta-agl-demo:9609b802dfb4b01b8f0dde3474c443a1b69c98f3 {
# add QtAGLExtras
RDEPENDS_${PN} += " \
    ${@bb.utils.contains("DISTRO_FEATURES", "agl-hmi-framework", " qtaglextras-dev qtaglextras-mkspecs", "",d)} \
"
## }
