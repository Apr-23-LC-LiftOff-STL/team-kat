import { HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { StorageService } from "src/services/storage.service";


@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {

    constructor(private storageService: StorageService) {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        
        const idToken = this.storageService.getJwt();

        if (idToken) {
            req = req.clone({
                headers: req.headers.set("Authorization", "Bearer " + idToken),
                withCredentials: true,
            });
        } 
        
        req = req.clone({
            withCredentials: true,
        });

        return next.handle(req);
    }

    

}

export const httpInterceptorProviders = [
    { 
        provide: HTTP_INTERCEPTORS, 
        useClass: HttpRequestInterceptor, 
        multi: true
    },
]