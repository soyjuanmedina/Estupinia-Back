import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

/** Este interceptor recoge todas las llamadas y en caso de que tengamos token en el sessionStorage si la llamada es al back
 * lo adjunta
 */
@Injectable()
export class AllHttpRequestsInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (sessionStorage.getItem('auth-token')) {
      req = req.clone({
        headers: new HttpHeaders({
          // 'X-Frame-Options': 'DENY',
          'Authorization': 'Bearer ' + sessionStorage.getItem('auth-token')
        })
      });
    }
    return next.handle(req);
  }
}