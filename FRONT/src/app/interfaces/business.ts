import { AccountingNote } from './accountingNote';

export interface Business {
  type: string;
  name: string;
  customers: {
    esteemed: number;
    commentary: string;
    averageTicket: number;
    monthly: boolean;
  }
  incomes: Array<AccountingNote>;
  costs: {
    fixed: Array<AccountingNote>;
    variables: Array<AccountingNote>;
  };
}