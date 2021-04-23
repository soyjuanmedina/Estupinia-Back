import { Injectable } from '@angular/core';

@Injectable()
export class DialogService {
  confirm(message?: string): boolean {
    const confirmation = window.confirm(message || 'Are you sure?');
    return confirmation;
  };
}