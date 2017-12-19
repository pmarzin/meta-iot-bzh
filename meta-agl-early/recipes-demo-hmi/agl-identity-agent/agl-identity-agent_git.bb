DESCRIPTION = "AGL identity agent binding"
HOMEPAGE = "https://gitlab.com/iotbzh/aia-binding"
SECTION = "base"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://../LICENSE.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "gitsm://gerrit.automotivelinux.org/gerrit/apps/agl-service-identity-agent;protocol=https;branch=${AGL_BRANCH}"
SRCREV  = "${AUTOREV}"
PV = "1.0+git${SRCPV}"

inherit cmake aglwgt pkgconfig

S = "${WORKDIR}/git/agl-identity-service"

DEPENDS = "curl af-binder json-c systemd"

do_install() {
	install -d ${D}/etc/agl
	install -m 0644 ${S}/etc/config.json ${D}/etc/agl/identity-agent-config.json
}