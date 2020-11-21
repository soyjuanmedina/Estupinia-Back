import { AccountingNote } from './accountingNote';
import { EsteemedCustomers } from './esteemedCustomers';

export interface Project {
  id?: string;
  isMine?: boolean;
  isShared?: boolean;
  uuid?: string;
  type: string;
  name: string;
  esteemedCustomers: EsteemedCustomers;
  incomes: Array<AccountingNote>;
  costs: {
    id?: number,
    fixedcosts: Array<AccountingNote>;
    variablescosts: Array<AccountingNote>;
  };
}