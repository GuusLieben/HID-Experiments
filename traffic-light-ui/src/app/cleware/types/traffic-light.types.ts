export type ClewareTrafficLightColor = 'RED' | 'YELLOW' | 'GREEN';

export type ClewareTrafficLightDeviceState = {
  enabledColors: ClewareTrafficLightColor[],
}
