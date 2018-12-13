FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

RDEPENDS_${PN} = "bash"

SRC_URI += " \
	file://4a-hal-setup \
	file://hal-4a-greenbox.json \
"

do_install_append () {
	# redo install (revert effect of fd78578b11990bc885651c52690dbcf9169bd7ac / SPEC-2011)
	cmake_do_install

	# move all HALS from etc to etc.reference
	mkdir -p $PKGDIR/etc.reference/
	mv $PKGDIR/etc/* $PKGDIR/etc.reference/ 2>/dev/null || true
	
	# add greenbox hal
	cp ${WORKDIR}/hal-4a-greenbox.json $PKGDIR/etc.reference/

	# install setupscript that will enable the proper HAL(s)
	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/4a-hal-setup ${D}${bindir}

	# generate default config file with platform hals
	cat <<EOF >$PKGDIR/default_hals.conf
HAL_FALLBACK="${4A_RUNTIME_HAL_FALLBACK}"
HAL_DEFAULT="${4A_RUNTIME_HAL}"
HAL_AUTODETECT="${4A_RUNTIME_HAL_AUTODETECT}"
EOF

	# generate a fragment to patch 4a service file on boot
    install -d ${D}${sysconfdir}/agl-postinsts
	cat <<'EOF' >${D}/${sysconfdir}/agl-postinsts/A0_4a-hal-generic_svcpatch.sh
N=600
svcfile="/usr/local/lib/systemd/*/afm-service-agl-service-audio-4a*.service"
set -x
while ! ls $svcfile > /dev/null; do
	if [ $N = 0 ]; then echo "-- TMP 4A service not fixed for HAL detection"; exit 0; fi
	echo .
	sleep 0.2
	N=$(expr $N - 1)
done
EOF
	cat <<EOF >>${D}/${sysconfdir}/agl-postinsts/A0_4a-hal-generic_svcpatch.sh
sed -i '/ExecStartPre=/ a ExecStartPre=${bindir}/4a-hal-setup ${INSTALL_PREFIX}/4a-hal/default_hals.conf' \$svcfile;
EOF
	chmod a+x ${D}/${sysconfdir}/agl-postinsts/A0_4a-hal-generic_svcpatch.sh
}

RDEPENDS_${PN}_remove = "virtual/4a-default-hal"
