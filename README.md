# meta-kobo-clara-hd

The meta-kobo-clara-hd layer provides a working Yocto Linux system for
the Kobo Clara HD e-reader.

More specifically it provides the basis for my [HAslate] Home Assistant
dashboard application.

The `clara-image-core` image provides a Linux system with X Windows.
The `kobo-hass-image` provides the HAslate application on top of that.
The `update-bundle` recipe will build a [RAUC] update bundle based
on `kobo-hass-image`.

The kernel is akemnade's [kernel patched for Kobo Clara HD].

## Dependencies

You will also need the [meta-rauc] layer, which is used to handle updating
the image on the device.

## Installation and setup

See [install.md](install.md).

## Contributing

Pull requests are welcome. For major changes please open an issue first
to discuss what you want to change.

## To do

The application is currently in a state where it has the minimal
functions I need.

- [x] Write some documentation of how to set things up
- [ ] Upgrade to a newer kernel
- [ ] Try to use a standard Yocto version instead of NXP's
- [x] Use a newer Yocto release

## License

[MIT](https://spdx.org/licenses/MIT.html)

[Embedded Linux for i.MX]: https://www.nxp.com/design/software/embedded-software/i-mx-software/embedded-linux-for-i-mx-applications-processors:IMXLINUX
[HAslate]: https://github.com/kevinior/haslate
[RAUC]: https://rauc.readthedocs.io/en/latest/index.html
[kernel patched for Kobo Clara HD]: https://github.com/akemnade/linux
[meta-rauc]: https://github.com/rauc/meta-rauc
