import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './auth/login/login.component';
import { RegistrationComponent } from './auth/registration/registration.component'; 
import { AccountComponent } from './account/account.component';
import { UserEventsComponent } from './user-events/user-events.component';
import { PageNotFoundComponent } from './errors/page-not-found/page-not-found.component';

const routes: Routes = [
  { path: '', title: 'Taste Buddies', component: HomeComponent },
  { path: 'login', title: 'Login', component: LoginComponent },
  { path: 'signup', title: 'Register', component: RegistrationComponent },
  { path: 'account', title: 'Account', component: AccountComponent },
  { path: 'event', title: 'Events', component: UserEventsComponent },
  { path: '**', title: 'Page not found', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
