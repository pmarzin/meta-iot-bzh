SUMMARY = "Sample client for AFM to install/start/stop/remove applications"
DESCRIPTION = "afm-client is a sample AngularJS/HTML5 application using \
Application Framework Manager to install, start, stop, or remove \
applications provided as .wgt widget packages."
HOMEPAGE = "http://www.iot.bzh"

inherit systemd

LICENSE = "GPLv3+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6cb04bdb88e11107e3af4d8e3f301be5"

#DEPENDS = "nodejs-native"
RDEPENDS_${PN} = "af-main af-binder af-main-afbplugin af-binder-plugin-demopost af-binder-plugin-authlogin"

SRC_URI = "git://github.com/iotbzh/afm-client;protocol=https;branch=master \
           file://afm-client \
           file://afm-client.service \
          "
SRCREV = "5baf6cab7a132ce9f601e888f680d973bc4cc710"
S = "${WORKDIR}/git"

do_install () {
  mkdir -p ${D}/${datadir}/agl/afm-client
  cp -ra ${S}/dist.prod/* ${D}/${datadir}/agl/afm-client/

  mkdir -p ${D}/${bindir}
  install -m 0755 ${WORKDIR}/afm-client ${D}/${bindir}/afm-client

  if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
    install -d ${D}${systemd_user_unitdir}
    install -d ${D}${sysconfdir}/systemd/user/default.target.wants
    install -m 0644 ${WORKDIR}/afm-client.service ${D}/${systemd_user_unitdir}/afm-client.service
    ln -sf ${systemd_user_unitdir}/afm-client.service ${D}${sysconfdir}/systemd/user/default.target.wants
  fi
}

FILES_${PN} += "${datadir} ${systemd_user_unitdir}"