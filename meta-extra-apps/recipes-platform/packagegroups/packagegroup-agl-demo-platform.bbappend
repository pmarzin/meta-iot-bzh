RDEPENDS_${PN} += " \
    agl-webbrowser \
    qtlocation \
    qtlocation-qmlplugins \
    qtlocation-plugins \
    "

PACKAGECONFIG_append_pn-qtlocation = " geoclue"

AGL_APPS += " \
    agl-service-generic-controller \
    agl-service-controller-sample \
"

