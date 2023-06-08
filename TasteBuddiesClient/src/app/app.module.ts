import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms'
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrationFormComponent } from './auth/registration-form/registration-form.component';
import { LoginFormComponent } from './auth/login-form/login-form.component';

import { httpInterceptorProviders } from 'src/helpers/http.intercepter';
import { HomeComponent } from './home/home.component';
import { NavBarComponent } from './common/nav-bar/nav-bar.component';
import { FooterComponent } from './common/footer/footer.component';
import { LoginComponent } from './auth/login/login.component';
import { RegistrationComponent } from './auth/registration/registration.component';
import { HeaderComponent } from './common/header/header.component';
import { NavBarButtonComponent } from './common/nav-bar/nav-bar-button/nav-bar-button.component';
import { EventComponent } from './events/event.component';
import { AccountComponent } from './account/account.component';
import { UserEventsComponent } from './events/user-events/user-events.component';
import { PageNotFoundComponent } from './errors/page-not-found/page-not-found.component';
import { EventFormComponent } from './events/event-form/event-form.component';
import { EventJoinComponent } from './events/event-join/event-join.component';
import { EventPageComponent } from './events/event-page/event-page.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationFormComponent,
    LoginFormComponent,
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
    EventFormComponent,
    EventJoinComponent,
    EventPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
