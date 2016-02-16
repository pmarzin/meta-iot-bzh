inherit allarch

SUMMARY = "Provides the 'web-runtime' command"
DESCRIPTION = "The command 'web-runtime' is an abstraction that allows to "

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = "\
	file://web-runtime;md5sum=3a637f6d0f4a46539ef6e7aed0ee734f \
	file://web-runtime.qml;md5sum=5d6a379e9b7e5654319e5ba638824a58 \
	file://web-runtime-webkit.qml;md5sum=4daf9df39078634c27a7923d37e82e3d \
"

do_configure() {
    :
}

do_compile() {
    :
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/web-runtime ${D}${bindir}/web-runtime
    install -m 0644 ${WORKDIR}/web-runtime.qml ${D}${bindir}/web-runtime.qml
    install -m 0644 ${WORKDIR}/web-runtime-webkit.qml ${D}${bindir}/web-runtime-webkit.qml
}


