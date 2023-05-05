import { HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { StorageService } from "src/services/storage.service";

// This intercepter appends a couple headers to outgoing http requests
// that are needed for authentication. 

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {

    constructor(private storageService: StorageService) {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        
        const idToken = this.storageService.getJwt();

        // apparently requests can't be modified so you clone and add stuff at the same time
        const clone1 = req.clone({

            // a header telling angular to send session cookie for CORS purposes
            withCredentials: true
        });

        // add the auth token if it exists.
        if (idToken) {
            const clone2 = clone1.clone({
                headers: clone1.headers.set("Authorization", "Bearer " + idToken),
            });

            return next.handle(clone2)
        } 

        return next.handle(clone1);
    }

}

export const httpInterceptorProviders = [
    { 
        provide: HTTP_INTERCEPTORS, 
        useClass: HttpRequestInterceptor, 
        multi: true
    },
]