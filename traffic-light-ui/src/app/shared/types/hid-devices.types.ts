export type HidDeviceInformation = {
  vendorId: number,
  productId: number,
  serialNumber: string,
  manufacturer: string,
  product: string,
  path: string,
}

export type HidDeviceConfiguration = {
  usagePage: number,
  usage: number,
  interfaceNumber: number,
}

export type HidDevice = {
  information: HidDeviceInformation,
  configuration: HidDeviceConfiguration,
}
