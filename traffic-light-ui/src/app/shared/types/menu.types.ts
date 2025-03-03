export type MenuItem = {
  label: string;
  urlSegment: string;
  isNavigable: boolean;
  subItems?: MenuItem[];
  requiredRoles?: string[] | null;
};
