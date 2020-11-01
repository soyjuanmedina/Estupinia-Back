import { AccountingNote } from './accountingNote';
import { EsteemedCustomers } from './esteemedCustomers';

export interface Project {
  id?: string;
  type: string;
  name: string;
  esteemedCustomers: EsteemedCustomers;
  incomes: Array<AccountingNote>;
  costs: {
    fixedcosts: Array<AccountingNote>;
    variablescosts: Array<AccountingNote>;
  };
}