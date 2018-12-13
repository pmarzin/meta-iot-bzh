SRC_URI = "gitsm://github.com/tjahnk/4a-hal-unicens;protocol=https;nobranch=1"
SRCREV = "8be9b941f6364ba8f251ef6acb95350a8e289ce3"

do_install_append () {
	# redo install (revert effect of fd78578b11990bc885651c52690dbcf9169bd7ac / SPEC-2011)
	cmake_do_install

	# move all HALS from etc to etc.reference
	mkdir -p $PKGDIR/etc.reference/
	mv $PKGDIR/etc/* $PKGDIR/etc.reference/ 2>/dev/null || true

	# do as in original recipe
	rm -rf $PKGDIR/etc
}
