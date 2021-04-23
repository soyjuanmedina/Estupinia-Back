import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

/** Este interceptor recoge todas las llamadas y en caso de que tengamos token en el sessionStorage si la llamada es al back
 * lo adjunta
 */
@Injectable()
export class AllHttpRequestsInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (sessionStorage.token && req.url.includes(environment.baseUrl)) {
      req = req.clone({
        headers: new HttpHeaders({
          // 'X-Frame-Options': 'DENY',
          'Authorization': sessionStorage.token
        })
      });
    }
    return next.handle(req);
  }
}