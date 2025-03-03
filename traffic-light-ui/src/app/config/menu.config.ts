export const menuItems = {
  devices: {
    label: 'Cleware',
    urlSegment: 'cleware',
    isNavigable: false,
    subItems: {
      trafficLight: {
        label: 'Traffic Light',
        urlSegment: 'traffic-light',
        isNavigable: true,
      },
    },
  },
} as const;
