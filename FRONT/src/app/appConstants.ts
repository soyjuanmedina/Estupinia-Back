// Variables de colores en app/sass/_variables.scss

import { trigger, state, style, animate, transition, keyframes, query, stagger } from '@angular/animations';

export class AppConstants {
  public static STATUS = {
    OK: "0",
    ALERT: "1",
    ERROR: "2",
    RESENDACTIVATION: "31",
    PAPID_REMOVED: "32",
    PAPID_CREATED: "33"
  }
  public static CERO = "0";

  public static ANIMATIONS = [
    trigger('listAnimation', [
      state('in', style({ transform: 'translateX(0)' })),
      transition('* => *', [
        query(':enter', style({ opacity: 0 }), { optional: true }),
        query(':enter', stagger('300ms', [
          animate('0.6s ease-in', keyframes([
            style({ opacity: 0, transform: 'translateX(-100%)', offset: 0 }),
            style({ opacity: 1, transform: 'translateX(15px)', offset: 0.3 }),
            style({ opacity: 1, transform: 'translateX(0)', offset: 1.0 })
          ]))]), { optional: true }),
        query(':leave', stagger('300ms', [
          animate('0.6s ease-in', keyframes([
            style({ opacity: 1, transform: 'translateX(0)', offset: 0 }),
            style({ opacity: 0.5, transform: 'translateX(-15px)', offset: 0.7 }),
            style({ opacity: 0, transform: 'translateX(100%)', offset: 1.0 })
          ]))]), { optional: true })
      ]),
    ])
  ]

}