import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

/** Este interceptor recoge todas las llamadas y en caso de que tengamos token en el sessionStorage si la llamada es al back
 * lo adjunta
 */
@Injectable()
export class AllHttpRequestsInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    req = req.clone({
      headers: new HttpHeaders({
        // 'X-Frame-Options': 'DENY',
        'Access-Control-Allow-Origin': '*'
      })
    });
    return next.handle(req);
  }
}