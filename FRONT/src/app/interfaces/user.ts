import { Project } from './project';

export interface User {
  accessToken: string;
  email: string;
  id: number;
  projects: Array<Project>;
  roles: Array<string>;
  name: string;
  surname: string;
  tokenType: string;
  username: string;
}