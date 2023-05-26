import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'
import { HttpClientModule } from '@angular/common/http';
import { GoogleMapsModule } from '@angular/google-maps';
import { Loader } from '@googlemaps/js-api-loader';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrationFormComponent } from './auth/registration-form/registration-form.component';
import { LoginFormComponent } from './auth/login-form/login-form.component';

import { httpInterceptorProviders } from 'src/helpers/http.intercepter';
import { AuthWalkthroughComponent } from './auth/auth-walkthrough/auth-walkthrough.component';
import { HomeComponent } from './home/home.component';
import { NavBarComponent } from './common/nav-bar/nav-bar.component';
import { FooterComponent } from './common/footer/footer.component';
import { LoginComponent } from './auth/login/login.component';
import { RegistrationComponent } from './auth/registration/registration.component';
import { HeaderComponent } from './common/header/header.component';
import { NavBarButtonComponent } from './common/nav-bar/nav-bar-button/nav-bar-button.component';
import { EventComponent } from './event/event.component';
import { AccountComponent } from './account/account.component';
import { UserEventsComponent } from './user-events/user-events.component';
import { PageNotFoundComponent } from './errors/page-not-found/page-not-found.component';
import { ViewRestaurantsComponent } from './view-restaurants/view-restaurants.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationFormComponent,
    LoginFormComponent,
    AuthWalkthroughComponent,
    HomeComponent,
    NavBarComponent,
    FooterComponent,
    LoginComponent,
    RegistrationComponent,
    HeaderComponent,
    NavBarButtonComponent,
    EventComponent,
    AccountComponent,
    UserEventsComponent,
    PageNotFoundComponent,
    ViewRestaurantsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    GoogleMapsModule,
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
